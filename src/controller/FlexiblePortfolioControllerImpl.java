package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;


import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;

import model.FileAccessorsImpl;
import model.Portfolio;
import model.PortfolioImpl;
import model.StocksImpl;
import view.PortfolioViewImpl;

import static model.Input.takeFloatInput;
import static model.Input.takeIntegerInput;
import static model.Input.takeStringInput;
import static model.Output.append;
import static model.Output.appendNewLine;

public class FlexiblePortfolioControllerImpl extends PortfolioControllerImpl {

  public FlexiblePortfolioControllerImpl(StocksImpl stocksImpl, Portfolio portfolioImpl,
                                         PortfolioViewImpl
                                                 portfolioViewImpl) throws IOException {
    super(stocksImpl, portfolioImpl, portfolioViewImpl);
  }

  public FlexiblePortfolioControllerImpl(PortfolioImpl portfolioImpl, PortfolioViewImpl
          portfolioViewImpl) throws IOException {
    super(portfolioImpl, portfolioViewImpl);
  }

  @Override
  public Portfolio viewSpeculate(String input) throws IOException {
    try {
      FileAccessorsImpl fileAccessorsImpl = new FileAccessorsImpl();
      if (!fileAccessorsImpl.isFileExists(input, "portfolios/flexible")) {
        throw new FileNotFoundException(input);
      }

      HashMap<String, TreeMap<Date, StocksImpl>> portfolios = fileAccessorsImpl.viewFile(input, "portfolios" +
              "/flexible");
      for (String s : portfolios.keySet()) {
        updateListOfStocks(s);
      }
      model.setPortfolio(portfolios);
      model.setPortfolioName(input);
      controllerToViewHelper(portfolios);
      String currInput = takeStringInput("Would you like to speculate your " +
              "portfolio?(YES/NO)");
      if (currInput.equals("YES")) {
        model = speculateMenu();
      }

    } catch (FileNotFoundException e) {
      append("Portfolio doesn't exists");
      appendNewLine();
    } catch (IllegalArgumentException e) {
      append(e.getMessage());
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
    return model;
  }

  public Portfolio speculateMenu() throws IOException, ParseException {
    int input = takeIntegerInput("Choose from the below options:\n 1.Buy stocks.\n " +
            "2.Sell the stocks.\n 3.Get the total cost basis for the portfolio.\n 4.Get the " +
            "composition of the portfolio.\n 5.Get the portfolio performance over time.\n 6.Exit");
    switch (input) {
      case 1:
        model.setBuy(true);
        break;
      case 2:
        sellingHelper();
        break;
      case 3:
        break;
      case 4:
        break;
      case 5:

        break;
    }
    return model;
  }

  private Portfolio sellingHelper() throws IOException, ParseException {
    String tickerValue = takeStringInput("Enter the ticker value:\n");
    HashMap<String, TreeMap<Date, StocksImpl>> checklist = model.getPortfolio();
    if (checklist.containsKey(tickerValue)) {
      SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
      TreeMap<Date, StocksImpl> validDatesList = checklist.get(tickerValue);
      viewDatesByCompany(checklist, tickerValue);
      String date = takeStringInput("Please enter a valid date as per the above list");
      Date newDate = sdformat.parse(date);
      //TODO while invalid date
      validatingSellStocks(validDatesList, newDate);
      viewDatesByCompany(checklist, tickerValue);
    } else {
      append("The ticker symbol entered is invalid.\n");
      sellingHelper();
    }
    return model;
  }

  private void validatingSellStocks(TreeMap<Date, StocksImpl> validDatesList, Date newDate) throws IOException {
    int numberOfSellingStocks = takeIntegerInput("Please enter the number of stocks you "
            + "want to sell");
    float fee = takeFloatInput("Enter the commission fee and it has to greater than zero");

    if (numberOfSellingStocks <= 0 || fee <= 0) {
      append("The entered values (shares & fee) should be greater"
              + " than 0.\n");
      validatingSellStocks(validDatesList, newDate);
    }
    for (Map.Entry<Date, StocksImpl>
            entry : validDatesList.entrySet()) {
      if ((numberOfSellingStocks == 0) || entry.getKey().compareTo(newDate) > 0) {
        break;
      }
      if (numberOfSellingStocks >= entry.getValue().getShares()) {
        numberOfSellingStocks -= entry.getValue().getShares();
        entry.getValue().updateCommisionValue(fee + entry.getValue().getCommisionFee());
        entry.getValue().setShares(0);
      } else {
        entry.getValue().setShares(entry.getValue().getShares() - numberOfSellingStocks);
        entry.getValue().updateCommisionValue(fee + entry.getValue().getCommisionFee());
        numberOfSellingStocks = 0;
      }
    }
    if (numberOfSellingStocks != 0) {
      append("Your portfolio lacks " + numberOfSellingStocks + " they are not sold!\n");
    }
  }

  private void performanceOverTime() throws IOException, ParseException {
    String date1 = takeStringInput("Enter the lower limit of the time range in the format of");
    String date2 = takeStringInput("Enter the upper limit of the time range");
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
    Date newDate1 = sdformat.parse(date1);
    Date newDate2 = sdformat.parse(date2);
    LocalDate dateLower = newDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate dateUpper = newDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    long t = ChronoUnit.DAYS.between(dateLower, dateUpper);
    HashMap<String, TreeMap<Date, StocksImpl>> entries = model.getPortfolio();
    if (t < 5) {
      append("Enter the time range which has at least 5 days");
      performanceOverTime();
    } else if (t >= 5 && t <= 30) {
      TreeMap<Date, Float> barData = new TreeMap<Date, Float>();
      entries.forEach((k, v) -> {
        v.forEach((dateKey, stocks) -> {
          if (dateKey.compareTo(newDate1) >= 0 && dateKey.compareTo(newDate2) <= 0) {
            if (barData.containsKey(dateKey)) {
              barData.put(dateKey, (stocks.getClose() * stocks.getShares()) + barData.get(dateKey));
            } else {
              barData.put(dateKey, stocks.getClose() * stocks.getShares());
            }
          }
        });
      });
    } else if (31 <= t && t < 150) {
    } else if (150 <= t && t <= 900) {
    } else if (900 <= t && t <= 1825) {
    } else if (1825 <= t && t <= 10950) {
    } else {
      append("The given dates are either invalid or exceed the limit of 30 years span!!!");
    }
  }

  private void viewDatesByCompany(HashMap<String, TreeMap<Date, StocksImpl>> portfolioEntries,
                                  String company) {
    AtomicBoolean displayHeaders = new AtomicBoolean(true);
    TreeMap<Date, StocksImpl> v = portfolioEntries.get(company);
    v.forEach((key, value) -> {
      try {
        view.displayPortfolio(displayHeaders.get(), value.getCompany(),
                value.getDate(),
                value.getOpen(), value.getHigh(), value.getLow(),
                value.getClose(), value.getVolume(), value.getShares(),
                value.getCommisionFee());
        displayHeaders.set(false);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }
}
