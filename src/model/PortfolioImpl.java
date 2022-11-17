package model;

import static model.Input.takeStringInput;
import static model.Output.append;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.time.format.DateTimeFormatter;

/**
 * Class that implements the portfolio interface and is responsible for portfolio actions.
 */
public class PortfolioImpl implements Portfolio {
// TODO add a new function to handle selling the stocks.
  // TODO incorporate broker fees.

//  HashMap<String, StocksImpl> entriesInPortfolio = new HashMap<>();

  HashMap<String, TreeMap<Date, StocksImpl>> entriesInPortfolio = new HashMap<>();

  boolean saved = false;
  private String portfolioName;
  boolean isFlexible;

  private final FileAccessorsImpl fileAccessor = new FileAccessorsImpl();
  private boolean buy;

  private boolean isCostBasis;

  /**
   * Method used to add stock to the portfolio.
   *
   * @param data data to be added.
   */

  @Override
  public void addStockInPortfolio(StocksImpl data) throws ParseException {
    String company = data.getCompany();
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
    TreeMap<Date, StocksImpl> currStockData = new TreeMap<>();
    if (data.getDate() == "") {

    }
    Date newDate = sdformat.parse(data.getDate());

    if (entriesInPortfolio.containsKey(company)) {
      currStockData = entriesInPortfolio.get(company);

      if (currStockData.containsKey(newDate)) {
        StocksImpl currStock = (entriesInPortfolio.get(company)).get(newDate);
        data.updateStockValues(currStock.getShares() + data.getShares());
        data.updateCommisionValue(currStock.getCommisionFee() + data.getCommisionFee());
        currStockData.put(newDate, data);
      } else {
        (entriesInPortfolio.get(company)).put(newDate, data);
      }
    } else {
      currStockData.put(newDate, data);
      entriesInPortfolio.put(data.getCompany(), currStockData);
    }
  }

  @Override
  public HashMap<String, Integer> getCompanyWiseShares() {
    HashMap<String, Integer> companyShares = new HashMap();
    entriesInPortfolio.forEach((comp, stocks) -> {
      stocks.forEach((date, stock) -> {
        if (companyShares.containsKey(comp)) {
          companyShares.put(comp, companyShares.get(comp) + stock.getShares());
        } else {
          companyShares.put(comp, stock.getShares());
        }
      });
    });
    return companyShares;
  }

  /**
   * Method used to get portfolio.
   *
   * @return portfolio.
   */
  @Override
  public HashMap<String, TreeMap<Date, StocksImpl>> getPortfolio() {
    return entriesInPortfolio;
  }

  /**
   * Method to create a portfolio.
   *
   * @param portfolio mapping of the portfolio.
   */
  @Override
  public void setPortfolio(HashMap<String, TreeMap<Date, StocksImpl>> portfolio) {
    this.entriesInPortfolio = portfolio;

  }

  /**
   * Method used to get company name.
   *
   * @return the list of company names.
   */
  @Override
  public TreeMap getCompanyNames() {
    return (TreeMap) entriesInPortfolio.values();
//    return new ArrayList<StocksImpl>(entriesInPortfolio.values());
  }

  /**
   * Method used to check if file is saved or not.
   *
   * @return checks whether the file is saved or not.
   */
  @Override
  public boolean isSaved() {
    return saved;
  }

  /**
   * Readers and saves the portfolio in file.
   */
  @Override
  public void save(String path) {
    fileAccessor.writeIntoCSVFile(portfolioName, entriesInPortfolio, path);
    this.saved = true;
  }

  /**
   * Method used to check if the file is already created.
   *
   * @param name sets the name of the portfolio.
   * @throws FileAlreadyExistsException file doesn't exist.
   */
  @Override
  public void setPortfolioName(String name) throws FileAlreadyExistsException {
    portfolioName = name;
  }

  @Override
  public void setIsFlexible(boolean isFlexible) {
    this.isFlexible = isFlexible;
  }

  @Override
  public boolean getIsFlexible() {
    return isFlexible;
  }

  @Override
  public void setBuy(boolean buy) {
    this.buy = buy;
  }

  @Override
  public boolean getBuy() {
    return buy;
  }

  @Override
  public void setIsCostBasis(boolean isCostBasis) {
    this.isCostBasis = isCostBasis;
  }

  @Override
  public boolean getIsCostBasis() {
    return isCostBasis;
  }

  @Override
  public TreeMap<String, Integer> getDaysWiseData(
      HashMap<String, TreeMap<Date, StocksImpl>> entries,
      Date newDate1, Date newDate2) {
    TreeMap<String, Integer> barData = new TreeMap<String, Integer>();
    entries.forEach((k, v) -> {
      v.forEach((dateKey, stocks) -> {
        if (dateKey.compareTo(newDate1) >= 0 && dateKey.compareTo(newDate2) <= 0) {
          if (barData.containsKey(stocks.getDate())) {
            barData.put(stocks.getDate(),
                Math.round(stocks.getClose() * stocks.getShares()) + barData.get(dateKey));
          } else {
            barData.put(stocks.getDate(), Math.round(stocks.getClose() * stocks.getShares()));
          }
        }
      });
    });
    return barData;
  }

  @Override
  public TreeMap<String, Integer> getWeekWiseData(HashMap<String, StringBuilder> entries,
      Date newDate1,
      Date newDate2) {
    LocalDate dateLower = newDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate dateUpper = newDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    HashMap<String, Integer> companyShares = this.getCompanyWiseShares();
    TreeMap<String, Integer> performanceData = new TreeMap<String, Integer>();
    entries.forEach((k, v) -> {
      HashMap<String, StocksImpl> currentData = readTheData(v);
      List<LocalDate> listOfDates = getWeeksBetweenTwoDates(dateLower, dateUpper);
      listOfDates.forEach((date) -> {
        StocksImpl currStock = currentData.get(date.toString());
        float close = currStock.getClose();
        String currDate = currStock.getDate();
        int shares = companyShares.get(k);
        if (performanceData.containsKey(currDate)) {
          performanceData.put(currDate, Math.round(shares * close + performanceData.get(currDate)));
        } else {
          performanceData.put(currDate, Math.round(shares * close));
        }
      });
    });

    return performanceData;
  }

  @Override
  public TreeMap<String, Integer> getMonthWiseData(HashMap<String, StringBuilder> entries,
      Date newDate1, Date newDate2) {
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
    System.out.println(newDate1.toString());
    String newDateString1 = (sdformat.format(newDate1)).substring(0,7);
    String newDateString2 = (sdformat.format(newDate2)).substring(0,7);




    return null;
  }

  private List getWeeksBetweenTwoDates(LocalDate dateLower, LocalDate dateUpper) {
    LocalDate startFridayDate = dateLower.with(TemporalAdjusters.next((DayOfWeek.FRIDAY)));
    LocalDate endFridayDate = dateUpper.with(TemporalAdjusters.next((DayOfWeek.FRIDAY)));
    List<LocalDate> listOfDates = new ArrayList<LocalDate>();
    listOfDates.add(startFridayDate);
    LocalDate startDate = startFridayDate;
    LocalDate endDate;
    do {
      endDate = startDate.plusDays(7);
      if (endDate.compareTo(endFridayDate) <= 0) {
        listOfDates.add(endDate);
      }

      startDate = endDate;
    } while (startDate.compareTo(endFridayDate) <= 0);
    return listOfDates;
  }

//  private List getMonthsBetweenTwoDates(LocalDate dateLower, LocalDate dateUpper) {
//    LocalDate lastMonthDate = dateLower.with(TemporalAdjusters.firstDayOfMonth());
//    LocalDate startFridayDate = dateLower.with(TemporalAdjusters.next(()));
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

  private HashMap<String, StocksImpl> readTheData(StringBuilder data) {
    HashMap<String, StocksImpl> currListStocks = new HashMap();
    String line; // Reading header, Ignoring;
    String[] entries = data.toString().split("\r\n");
    for (int i = 1; i < entries.length; i++) {

      String[] fields = entries[i].split(",");
      StocksImpl newStock = new StocksImpl(
          fields[0],
          Float.parseFloat(fields[1]),
          Float.parseFloat(fields[2]),
          Float.parseFloat(fields[3]),
          Float.parseFloat(fields[4]),
          Float.parseFloat(fields[6])
//            Integer.parseInt(fields[6])
      );
      currListStocks.put(fields[0], newStock);
    }
    return currListStocks;
  }

}
