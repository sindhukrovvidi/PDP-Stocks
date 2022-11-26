package model;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Interface that contains methods to get and set the values in portfolio.
 */
public interface Portfolio {

  /**
   * Method used to add stock to the portfolio.
   *
   * @param data data to be added.
   */
  void addStockInPortfolio(StocksImpl data) throws ParseException;

  /**
   * Method used to get portfolio.
   *
   * @return portfolio.
   */
  HashMap<String, TreeMap<Date, StocksImpl>> getPortfolio();

  /**
   * Method to create a portfolio.
   *
   * @param portfolio mapping of the portfolio.
   */
  void setPortfolio(HashMap<String, TreeMap<Date, StocksImpl>> portfolio);


  /**
   * Method used to check if file is saved or not.
   *
   * @return true if saved else false.
   */
  boolean isSaved();

  /**
   * Method used to save a file.
   */
  void save(String path);


  /**
   * Method used to check if the file is already created.
   *
   * @param name name of the file.
   * @throws FileAlreadyExistsException if the file already exists.
   */
  void setPortfolioName(String name) throws FileAlreadyExistsException;

  /**
   * Method used to set if flexible or not.
   *
   * @param isFlexible boolean value.
   */
  void setIsFlexible(boolean isFlexible);

  /**
   * Method used to get if flexible or not.
   *
   * @return true if flexible else false.
   */
  boolean getIsFlexible();

  /**
   * Method used to set if buying or not.
   *
   * @param buy boolean.
   */
  void setBuy(boolean buy);

  /**
   * Method used to set if buying or not.
   *
   * @return true if buying.
   */
  boolean getBuy();

  /**
   * Method used to set the cost basis true or not.
   *
   * @param isCostBasis boolean.
   */
  void setIsCostBasis(boolean isCostBasis);

  /**
   * Method used to get the cost basis true or not.
   *
   * @return true if the cost basis is needed else false.
   */
  boolean getIsCostBasis();

  /**
   * Gets the company wise shares.
   *
   * @return returns hashmap.
   */

  HashMap<String, Integer> getCompanyWiseShares();

  /**
   * Fetches the day wise data for a given time range.
   *
   * @param entries  list of stocks.
   * @param newDate1 initial date.
   * @param newDate2 final date.
   * @return a treemap with dates and value on that day.
   */
  TreeMap<LocalDate, Integer> getDaysWiseData(HashMap<String, StringBuilder> entries,
      Date newDate1, Date newDate2);

  /**
   * Fetches the week wise data for a given time range.
   *
   * @param entries  list of stocks.
   * @param newDate1 initial date.
   * @param newDate2 final date.
   * @return a treemap with dates and value on that day.
   */
  TreeMap<LocalDate, Integer> getWeekWiseData(HashMap<String, StringBuilder> entries,
      Date newDate1, Date newDate2);

  /**
   * Fetches the month wise data for a given time range.
   *
   * @param entries  list of stocks.
   * @param newDate1 initial date.
   * @param newDate2 final date.
   * @return a treemap with dates and value on that day.
   */
  TreeMap<LocalDate, Integer> getMonthWiseData(HashMap<String, StringBuilder> entries,
      Date newDate1, Date newDate2);

  /**
   * Retrieves the composition of the portfolio.
   *
   * @param currMap map of stocks and their daily data.
   * @param input   date of the portfolio.
   * @return compostion stored in an hashmap.
   * @throws ParseException invalid date.
   */
  HashMap getCompostion(HashMap currMap, String input) throws ParseException;

  /**
   * Scales the performncae of the bar graph.
   * @param performanceData data after fetching the required data.
   * @param date1 initial date.
   * @param date2 end date.
   * @return String that has the performance data.
   */
  String getScaleValue(TreeMap<LocalDate, Integer> performanceData, String date1,
      String date2);

  String calculatePerformaceOverTime(String date1, String date2) throws IOException, ParseException;

  void addMultipleStocksInPortfolio(HashMap map, String lowerDate, String upperDate,
      int frequency, String[] stocksInput, float valueInvested, String weightage, float fee)
      throws ParseException;


  boolean validateInputForMultiStocks(float investedAmount, String weightage, int fee,
      String lowerDate, String upperDate, int frequency)
      throws ParseException;

  HashMap<String, TreeMap<Date, StocksImpl>> sellingHelper() throws IOException, ParseException;

  int sellTheStocks(TreeMap<Date, StocksImpl> validDatesList, Date newDate,
      int numberOfSellingStocks, float fee);
}
