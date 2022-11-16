package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
      int numberOfSellingStocks = takeIntegerInput("Please enter the number of stocks you "
              + "want to sell");
      for (Map.Entry<Date, StocksImpl>
              entry : validDatesList.entrySet()) {
        if ((numberOfSellingStocks == 0) || entry.getKey().compareTo(newDate) > 0) {
          break;
        }
        if (numberOfSellingStocks >= entry.getValue().getShares()) {
          entry.getValue().setShares(0);
          numberOfSellingStocks -= entry.getValue().getShares();
        } else {
          entry.getValue().setShares(entry.getValue().getShares() - numberOfSellingStocks);
          numberOfSellingStocks = 0;
        }
      }
      if (numberOfSellingStocks != 0) {
        append("Your portfolio lacks" + numberOfSellingStocks + "they are not sold!");
      }
      viewDatesByCompany(checklist, tickerValue);
    }
    // TODO handle invalid ticker symbol.
    else {
    }
    return model;
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
