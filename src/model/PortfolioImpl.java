package model;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
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
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * Class that implements the portfolio interface and is responsible for portfolio actions.
 */
public class PortfolioImpl implements Portfolio {

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
    if (data.getDate().equals("")) {

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

  /**
   * Gets the company wise shares.
   *
   * @return returns hashmap.
   */
  @Override
  public HashMap<String, Float> getCompanyWiseShares() {
    HashMap<String, Float> companyShares = new HashMap();
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
   * @param date
   * @return
   */
  @Override
  public boolean isValidDate(String date) {
    try {
      SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
      sdformat.parse(date);
      return true;
    } catch (ParseException e) {
      return false;
    }
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
    String name = portfolioName.contains(".csv") ? portfolioName.split(".csv")[0] : portfolioName;
    fileAccessor.writeIntoCSVFile(name, entriesInPortfolio, path);
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

  /**
   * Method used to set if flexible or not.
   *
   * @param isFlexible boolean.
   */
  @Override
  public void setIsFlexible(boolean isFlexible) {
    this.isFlexible = isFlexible;
  }


  /**
   * Method used to set if flexible or not.
   *
   * @return isFlexible boolean.
   */
  @Override
  public boolean getIsFlexible() {
    return isFlexible;
  }

  /**
   * Method used to set if buying or not.
   *
   * @param buy boolean.
   */
  @Override
  public void setBuy(boolean buy) {
    this.buy = buy;
  }

  /**
   * Method used to set if buying or not.
   *
   * @return true if buying.
   */
  @Override
  public boolean getBuy() {
    return buy;
  }

  /**
   * Method used to set the cost basis true or not.
   *
   * @param isCostBasis boolean.
   */
  @Override
  public void setIsCostBasis(boolean isCostBasis) {
    this.isCostBasis = isCostBasis;
  }

  /**
   * Method used to get the cost basis true or not.
   *
   * @return true if the cost basis is needed else false.
   */
  @Override
  public boolean getIsCostBasis() {
    return isCostBasis;
  }

  /**
   * Fetches the day wise data for a given time range.
   *
   * @param entries  list of stocks.
   * @param newDate1 initial date.
   * @param newDate2 final date.
   * @return a treemap with dates and value on that day.
   */
  @Override
  public TreeMap<LocalDate, Integer> getDaysWiseData(
      HashMap<String, StringBuilder> entries,
      Date newDate1, Date newDate2) {
    LocalDate dateLower = newDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate dateUpper = newDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    HashMap<String, Float> companyShares = this.getCompanyWiseShares();
    TreeMap<String, Integer> barData = new TreeMap<String, Integer>();

    List<LocalDate> listOfDates = getDaysBetweenTwoDates(dateLower, dateUpper);
    entries.forEach((k, v) -> {
      HashMap<String, StocksImpl> currentData = readTheData(v);
      listOfDates.forEach((date) -> {
        StocksImpl currStock = currentData.get(date.toString());
        float close = currStock != null ? currStock.getClose() : 0;
        String currDate = currStock != null ? currStock.getDate() : date.toString();
        float shares = companyShares.get(k);
        if (barData.containsKey(currDate)) {
          barData.put(currDate, Math.round(shares * close + barData.get(currDate)));
        } else {
          barData.put(currDate, Math.round(shares * close));
        }
      });
    });

    TreeMap<LocalDate, Integer> finalData = new TreeMap();
    barData.forEach((key, val) -> {
      SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
      Date newDate = null;
      try {
        newDate = sdformat.parse(key);
        LocalDate currDate = newDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        finalData.put(currDate, val);
      } catch (ParseException e) {
        throw new RuntimeException(e);
      }
    });
    return finalData;
  }


  /**
   * Fetches the week wise data for a given time range.
   *
   * @param entries  list of stocks.
   * @param newDate1 initial date.
   * @param newDate2 final date.
   * @return a treemap with dates and value on that day.
   */
  @Override
  public TreeMap<LocalDate, Integer> getWeekWiseData(HashMap<String, StringBuilder> entries,
      Date newDate1,
      Date newDate2) {
    LocalDate dateLower = newDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate dateUpper = newDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    HashMap<String, Float> companyShares = this.getCompanyWiseShares();
    TreeMap<String, Integer> performanceData = new TreeMap<String, Integer>();
    entries.forEach((k, v) -> {
      HashMap<String, StocksImpl> currentData = readTheData(v);
      List<LocalDate> listOfDates = getWeeksBetweenTwoDates(dateLower, dateUpper);
      listOfDates.forEach((date) -> {
        StocksImpl currStock = currentData.get(date.toString());
        float close = currStock.getClose();
        String currDate = currStock.getDate();
        float shares = companyShares.get(k);
        if (performanceData.containsKey(currDate)) {
          performanceData.put(currDate, Math.round(shares * close + performanceData.get(currDate)));
        } else {
          performanceData.put(currDate, Math.round(shares * close));
        }
      });
    });

    TreeMap<LocalDate, Integer> finalData = new TreeMap();
    performanceData.forEach((key, val) -> {
      SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
      Date newDate = null;
      try {
        newDate = sdformat.parse(key);
        LocalDate currDate = newDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        finalData.put(currDate, val);
      } catch (ParseException e) {
        throw new RuntimeException(e);
      }
    });
    return finalData;
  }

  /**
   * Fetches the month wise data for a given time range.
   *
   * @param entries  list of stocks.
   * @param newDate1 initial date.
   * @param newDate2 final date.
   * @return a treemap with dates and value on that day.
   */
  @Override
  public TreeMap<LocalDate, Integer> getMonthWiseData(HashMap<String, StringBuilder> entries,
      Date newDate1, Date newDate2) {
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
    System.out.println(newDate1.toString());
    String newDateString1 = (sdformat.format(newDate1)).substring(0, 7);
    String newDateString2 = (sdformat.format(newDate2)).substring(0, 7);

    HashMap<String, Float> companyShares = this.getCompanyWiseShares();
    TreeMap<Date, Integer> performanceData = new TreeMap<Date, Integer>();

    LocalDate dateLower = newDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate dateUpper = newDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    entries.forEach((k, v) -> {
      HashMap<String, StocksImpl> currentData = readTheData(v);
    });

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
    }
    while (startDate.compareTo(endFridayDate) <= 0);
    return listOfDates;
  }

  private List getDaysBetweenTwoDates(LocalDate dateLower, LocalDate dateUpper) {
    LocalDate startFridayDate = dateLower;
    LocalDate endFridayDate = dateUpper;
    List<LocalDate> listOfDates = new ArrayList<LocalDate>();
    listOfDates.add(startFridayDate);
    LocalDate startDate = startFridayDate;
    LocalDate endDate;
    do {
      endDate = startDate.plusDays(1);
      if (endDate.compareTo(endFridayDate) <= 0) {
        listOfDates.add(endDate);
      }

      startDate = endDate;
    }
    while (startDate.compareTo(endFridayDate) <= 0);
    return listOfDates;
  }

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
          Float.parseFloat(fields[5])
      );
      currListStocks.put(fields[0], newStock);
    }
    return currListStocks;
  }

  /**
   * Retrieves the composition of the portfolio.
   *
   * @param currMap map of stocks and their daily data.
   * @param input   date of the portfolio.
   * @return compostion stored in an hashmap.
   * @throws ParseException invalid date.
   */
  @Override
  public HashMap getCompostion(HashMap currMap, String input) throws ParseException {
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
    Date formattedDate = sdformat.parse(input);

    HashMap<String, TreeMap<Date, StocksImpl>> currentPortfolio = this.getPortfolio();
    AtomicReference<Float> total_composition = new AtomicReference<>((float) 0);
    AtomicReference<Float> total_comission_fee = new AtomicReference<>((float) 0);
    ArrayList<StocksImpl> inDateList = new ArrayList();

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

    float final_total_value = this.getIsCostBasis() ?
        total_composition.get() + total_comission_fee.get() : total_composition.get();

    HashMap returnObj = new HashMap();
    returnObj.put("inDateList", inDateList);
    returnObj.put("final_total_value", final_total_value);
    returnObj.put("total_comission_fee", total_comission_fee.get());

    Date currFormattedDate = sdformat.parse(input);
    Date todayDate = new Date();
    if (currFormattedDate.compareTo(todayDate) > 0) {
      return null;
    }
    return returnObj;
  }

  /**
   * Scales the performncae of the bar graph.
   *
   * @param performanceData data after fetching the required data.
   * @param date1           initial date.
   * @param date2           end date.
   * @return String that has the performance data.
   */
  @Override
  public String getScaleValue(TreeMap<LocalDate, Integer> performanceData, String date1,
      String date2) {
    int maxStartCount = 50;

    double min = performanceData.values().stream().mapToDouble(e -> e).min().orElse(0);
    double max = performanceData.values().stream().mapToDouble(e -> e).max().orElse(0);
    StringBuilder sb = new StringBuilder();
    sb.append("Performance of portfolio " + this.portfolioName + " from " + date1 + " to " +
        date2 + "\n\n");
    performanceData.forEach((k, v) -> {
      double norm = (v - min) / (max - min);
      String line = k + " " + "*".repeat((int) Math.ceil(norm * maxStartCount));
      sb.append(line);
      sb.append("\n");
    });
    sb.append("\n");
    sb.append(
        "Scale: Each * represents value " + Math.round((max - min) / 50) + "$ more than base " +
            "value of $" + Math.round(min));
    return sb.toString();
  }

  @Override
  public String calculatePerformaceOverTime(String date1, String date2)
      throws IOException, ParseException {
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
    Date newDate1 = sdformat.parse(date1);
    Date newDate2 = sdformat.parse(date2);
    LocalDate dateLower = newDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate dateUpper = newDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    long t = ChronoUnit.DAYS.between(dateLower, dateUpper);

    HashMap<String, Float> companyShares = this.getCompanyWiseShares();

    HTTPRequests requests = new HTTPRequestsImpl();
    TreeMap<LocalDate, Integer> barData = new TreeMap();

    if (t < 5) {
      return null;
    } else if (t >= 5 && t <= 30) {

      HashMap<String, StringBuilder> dailyStocksData = new HashMap();

      for (Entry<String, Float> entry : companyShares.entrySet()) {
        String comp = entry.getKey();
        StringBuilder data = requests.getDailyData(comp);
        dailyStocksData.put(comp, data);
      }

      barData = this.getDaysWiseData(dailyStocksData, newDate1, newDate2);
      String data = this.getScaleValue(barData, date1, date2);
      return data;


    } else if (31 <= t && t < 150) { // weekly data
      HashMap<String, StringBuilder> weeklyStocksData = new HashMap();

      for (Entry<String, Float> entry : companyShares.entrySet()) {
        String comp = entry.getKey();
        StringBuilder data = requests.getWeeklyData(comp);
        weeklyStocksData.put(comp, data);
      }

      barData = this.getWeekWiseData(weeklyStocksData, newDate1, newDate2);
      String data = this.getScaleValue(barData, date1, date2);
      return data;

    } else if (150 <= t && t <= 900) { // month
      HashMap<String, StringBuilder> monthlyStocksData = new HashMap();

      for (Entry<String, Float> entry : companyShares.entrySet()) {
        String comp = entry.getKey();
        StringBuilder data = requests.getMonthlyData(comp);
        monthlyStocksData.put(comp, data);
      }

      barData = this.getMonthWiseData(monthlyStocksData, newDate1, newDate2);
      String data = this.getScaleValue(barData, date1, date2);
      return data;

    } else {
      return null;
    }
  }

  @Override
  public void addMultipleStocksInPortfolio(HashMap map, String lowerDate, String upperDate,
      int frequency, String[] stocksInput, float valueInvested,
      String weightage, float fee)
      throws ParseException {
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");

    List<LocalDate> listOfDates = getDatesForFrequency(lowerDate, upperDate, frequency);
    String[] percentages = weightage.split(",");
    int index = 0;

    List<LocalDate> futureDates =
        listOfDates.stream().filter(ele -> ele.compareTo(LocalDate.now()) > 0).collect(
            Collectors.toList());

    for (String stock : stocksInput) {
      ArrayList<StocksImpl> currentStockData = (ArrayList<StocksImpl>) map.get(stock.toUpperCase());

      for (LocalDate futureDate : futureDates) {
        addStockInPortfolio(new StocksImpl(
            stock.toUpperCase(),
            futureDate.toString(), 0, 0, 0, 0, 0, 0, fee,
            valueInvested * (Float.parseFloat(percentages[0]) / 100), true
        ));
      }

      for (StocksImpl currentStockDatum : currentStockData) {
        String stockDate = currentStockDatum.getDate();
        Date newDate = sdformat.parse(stockDate);
        LocalDate formattedStockData =
            newDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (listOfDates.contains(formattedStockData)) {
          addStockInPortfolio(new StocksImpl(
              stock.toUpperCase(),
              currentStockDatum.getDate(),
              currentStockDatum.getOpen(),
              currentStockDatum.getHigh(),
              currentStockDatum.getLow(),
              currentStockDatum.getClose(),
              currentStockDatum.getVolume(),
              (valueInvested * (Float.parseFloat(percentages[0]) / 100)
                  / currentStockDatum.getClose()),
              fee,
              Float.parseFloat(percentages[0]) / 100,
              false
          ));
        }
      }

      index++;
    }
  }

  private List<LocalDate> getDatesForFrequency(String startDate, String endDate, int frequency)
      throws ParseException {
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
    Date newDate1 = sdformat.parse(startDate);
    Date newDate2 = sdformat.parse(endDate);
    LocalDate dateLower = newDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate dateUpper = newDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    List<LocalDate> listOfDates = new ArrayList<LocalDate>();
    listOfDates.add(dateLower);
    LocalDate firstDate = dateLower;
    LocalDate lastDate;
    do {
      lastDate = firstDate.plusDays(frequency);
      if (lastDate.compareTo(dateUpper) <= 0) {
        listOfDates.add(lastDate);
      }

      firstDate = lastDate;
    }
    while (firstDate.compareTo(dateUpper) <= 0);
    return listOfDates;
  }

  @Override
  public boolean validateInputForMultiStocks(float investedAmount, String weightage, float fee,
      String lowerDate, String upperDate, int frequency, String[] tickerValuesList)
      throws ParseException {
    boolean areValidInputs = true;

    String[] percentages = weightage.split(",");
    float totalWeightage = 0;
    for (String percentage : percentages) {
      totalWeightage += Float.parseFloat(percentage.trim());
    }

    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
    Date newDate1 = sdformat.parse(lowerDate);
    Date newDate2 = sdformat.parse(upperDate);
    LocalDate dateLower = newDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate dateUpper = newDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    if (investedAmount <= 0 || fee <= 0 || totalWeightage != 100 || (dateLower.compareTo(dateUpper)
        >= 0) || frequency < 0 || percentages.length != tickerValuesList.length) {
      areValidInputs = false;
    }
    return areValidInputs;
  }

  @Override
  public float sellTheStocks(TreeMap<Date, StocksImpl> validDatesList, Date newDate,
      float numberOfSellingStocks, float fee) {
//    if (validDatesList != null) {
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
    System.out.println("calling sellTheStocks...." + numberOfSellingStocks);
//    }
    return numberOfSellingStocks;
  }

  @Override
  public String[] getPortfolioNames() {
    FileAccessors fileAccessors = new FileAccessorsImpl();
    String[] files = fileAccessors.listOfPortfolioFiles("portfolios/flexible");
    return files;
  }

  /**
   * Method that is used to fetch the data of a selected portfolio.
   *
   * @param input    name of the file.
   * @param stockMap hashmap of the stocks in the file.
   * @return hashmap of the stocks that has the dates sorted in chronological order.
   * @throws FileAlreadyExistsException if the file is already present.
   */
  public HashMap<String, TreeMap<Date, StocksImpl>> fetchSelectedPortfolio(String input,
      HashMap stockMap)
      throws FileAlreadyExistsException {

    HashMap<String, TreeMap<Date, StocksImpl>> portfolios = entriesInPortfolio;
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");

    portfolios.forEach((tickerValue, data) -> {
      TreeMap<Date, StocksImpl> currData = data;
      data.forEach((date, stock) -> {
        boolean isFuture = stock.getIsFuture();
        String stockDate = stock.getDate();
        Date newDate1 = null;

        try {
          newDate1 = sdformat.parse(stockDate);
        } catch (ParseException e) {
          throw new RuntimeException(e);
        }

        LocalDate dateLower = newDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (isFuture && dateLower.compareTo(LocalDate.now()) <= 0) {
          ArrayList currentTickerData = (ArrayList) stockMap.get(tickerValue);
          for (Object currentTickerDatum : currentTickerData) {
            StocksImpl curStock = (StocksImpl) currentTickerDatum;
            String curStockDate = curStock.getDate();
            if (curStockDate == dateLower.toString()) {
              currData.put(newDate1, new StocksImpl(
                  tickerValue,
                  curStockDate,
                  curStock.getOpen(),
                  curStock.getHigh(),
                  curStock.getLow(),
                  curStock.getClose(),
                  curStock.getVolume(),
                  (stock.getPercentage() / curStock.getClose()),
                  curStock.getCommisionFee(),
                  stock.getPercentage(),
                  false
              ));
            }

          }
        }
      });
    });

    this.setPortfolio(portfolios);
    this.setPortfolioName(input);

    return this.entriesInPortfolio;
  }


  /**
   * @param date
   * @param shares
   * @param fee
   * @return
   */
  @Override
  public int validateSellInputs(String date, float shares, float fee) {
    if (!isValidDate(date)) {
      return 3;
    }
    if (shares <= 0) {
      return 1;
    }
    if (fee <= 0) {
      return 2;
    }
    return 4;
  }
}
