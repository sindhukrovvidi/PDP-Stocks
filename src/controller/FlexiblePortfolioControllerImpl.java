package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import model.FileAccessorsImpl;
import model.HTTPRequests;
import model.HTTPRequestsImpl;
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

      HashMap<String, TreeMap<Date, StocksImpl>> portfolios = fileAccessorsImpl.viewFile(input,
          "portfolios" +
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
        model.setIsCostBasis(true);
        getCompositionOfPortfolio();
        break;
      case 4:
        model.setIsCostBasis(false);
        getCompositionOfPortfolio();
        break;
      case 5:
//        String date1 = takeStringInput("Enter the lower limit of the time range in the format of");
//        String date2 = takeStringInput("Enter the upper limit of the time range");
//
//        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
//        Date newDate1 = sdformat.parse(date1);
//        Date newDate2 = sdformat.parse(date2);
        performanceOverTime();
        break;
      case 6:
        System.exit(0);
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

  private void validatingSellStocks(TreeMap<Date, StocksImpl> validDatesList, Date newDate)
      throws IOException {
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
    HashMap<String, Integer> companyShares = model.getCompanyWiseShares();

    HTTPRequests requests = new HTTPRequestsImpl();
    TreeMap<String, Integer> barData = new TreeMap();

    if (t < 5) {
      append("Enter the time range which has at least 5 days");
      performanceOverTime();
    } else if (t >= 5 && t <= 30) {

      barData = model.getDaysWiseData(entries, newDate1, newDate2);

    } else if (31 <= t && t < 150) { // weekly data
      HashMap<String, StringBuilder> weeklyStocksData = new HashMap();

      for (Entry<String, Integer> entry : companyShares.entrySet()) {
        String comp = entry.getKey();
        StringBuilder data = requests.getWeeklyData(comp);
        weeklyStocksData.put(comp, data);
      }

      barData =  model.getWeekWiseData(weeklyStocksData, newDate1, newDate2);

    } else if (150 <= t && t <= 900) { // month
      HashMap<String, StringBuilder> monthlyStocksData = new HashMap();

      for (Entry<String, Integer> entry : companyShares.entrySet()) {
        String comp = entry.getKey();
        StringBuilder data = requests.getMonthlyData(comp);
        monthlyStocksData.put(comp, data);
      }

      barData =  model.getMonthWiseData(monthlyStocksData, newDate1, newDate2);

    } else if (900 <= t && t <= 1825) {
    } else if (1825 <= t && t <= 10950) {
    } else {
      append("The given dates are either invalid or exceed the limit of 30 years span!!!");
    }
  }
//
//  private List getDatesBetweenTwoDates(LocalDate dateLower, LocalDate dateUpper) {
//    LocalDate startFridayDate = dateLower.with(TemporalAdjusters.next((DayOfWeek.FRIDAY)));
//    LocalDate endFridayDate = dateUpper.with(TemporalAdjusters.next((DayOfWeek.FRIDAY)));
//    List<LocalDate> listOfDates = new ArrayList<LocalDate>();
//    listOfDates.add(startFridayDate);
//    LocalDate startDate = startFridayDate;
//    LocalDate endDate;
//    do {
//      endDate = startDate.plusDays(7);
//      if (endDate.compareTo(endFridayDate) <= 0) {
//        listOfDates.add(endDate);
//      }
//
//      startDate = endDate;
//    } while (startDate.compareTo(endFridayDate) <= 0);
//    return listOfDates;
//  }
//
//  private HashMap<String, StocksImpl> readTheData(StringBuilder data) {
//    HashMap<String, StocksImpl> currListStocks = new HashMap();
//    String line; // Reading header, Ignoring;
//    String[] entries = data.toString().split("\r\n");
//    for (int i = 1; i < entries.length; i++) {
//
//      String[] fields = entries[i].split(",");
//      StocksImpl newStock = new StocksImpl(
//          fields[0],
//          Float.parseFloat(fields[1]),
//          Float.parseFloat(fields[2]),
//          Float.parseFloat(fields[3]),
//          Float.parseFloat(fields[4]),
//          Float.parseFloat(fields[6])
////            Integer.parseInt(fields[6])
//      );
//      currListStocks.put(fields[0], newStock);
//    }
//    return currListStocks;
//  }

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

  public void getCompositionOfPortfolio() throws IOException, ParseException {
    String input = takeStringInput(
        "Enter the date in YYYY-MM-DD to view the composition on that particular "
            + "date.");
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
    Date formattedDate = sdformat.parse(input);
    Date todayDate = new Date();
//    todayDate = sdformat.parse(todayDate);

    HashMap currMap = getStockList().getLStocksMap();
    HashMap<String, TreeMap<Date, StocksImpl>> currentPortfolio = model.getPortfolio();
    AtomicReference<Float> total_composition = new AtomicReference<>((float) 0);
    AtomicReference<Float> total_comission_fee = new AtomicReference<>((float) 0);
    ArrayList<StocksImpl> inDateList = new ArrayList();
    AtomicBoolean foundDate = new AtomicBoolean(false);

    currentPortfolio.forEach((comp, stocks) -> {
      stocks.forEach((date, stockVal) -> {
        if (date.compareTo(formattedDate) <= 0) {
          ArrayList<StocksImpl> values = (ArrayList<StocksImpl>) currMap.get(comp);
          for (int i = 0; i < values.size(); i++) {
            StocksImpl currStock = (StocksImpl) values.get(i);

            Date formattedcurrStockDate = null;
            try {
              formattedcurrStockDate = sdformat.parse(currStock.getDate());
              if (formattedcurrStockDate.compareTo(formattedDate) == 0) {
//                foundDate.set(true);
                total_composition.set(
                    total_composition.get() + (currStock.getClose() * stockVal.getShares()));
                total_comission_fee.set(total_comission_fee.get() + stockVal.getCommisionFee());
                inDateList.add(stockVal);
              }
            } catch (ParseException e) {
              throw new RuntimeException(e);
            }

          }
        }
      });
    });

    AtomicBoolean displayHeaders = new AtomicBoolean(true);
    float final_total_value = model.getIsCostBasis() ?
        total_composition.get() + total_comission_fee.get() : total_composition.get();
    //todo handle incorrect date
    if (formattedDate.compareTo(todayDate) > 0) {
      append("The entered date is greater than current date, so can't evaluate your portfolio"
          + ".\n");
    } else if (inDateList.size() == 0) {
      append("Total composition of the portfolio is 0 as you do not have any stocks purchased by "
          + "then.\n");
    } else {
      inDateList.forEach(val -> {
        try {
          view.viewCompositionOfPortfolio(displayHeaders.get(), val.getCompany(), val.getDate(),
              val.getOpen(),
              val.getClose(), val.getShares(), val.getCommisionFee(), final_total_value, input);
          displayHeaders.set(false);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }

      });
    }

  }
}
