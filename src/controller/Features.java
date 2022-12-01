package controller;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import model.StocksImpl;

/**
 * The interface that contains all the methods for the required features.
 */
public interface Features {

  /**
   * Creates a portfolio with the given name.
   *
   * @param filename name of the file.
   * @return true if successfully created.
   * @throws IOException throws I/O error.
   */
  boolean createPortfolio(String filename) throws IOException;

  /**
   * Validates the stocks data entered.
   *
   * @param ticker ticker symbol.
   * @param date   date.
   * @param stocks number of stocks.
   * @param fee    commission fee.
   * @return true if all inputs are equal.
   * @throws IOException error if not able read file.
   */
  boolean validateSingleStocksData(String ticker, String date, int stocks, float fee)
      throws IOException;

  /**
   * Adds the stock to the portfolio.
   *
   * @param tickerName ticker name.
   * @param date       date.
   * @param stocks     number of the stocks.
   * @param fee        commission fee.
   * @throws ParseException for invalid date.
   */
  void addStockToPortfolio(String tickerName, String date, int stocks, float fee)
      throws ParseException;

  /**
   * Saves the current portfolio.
   */
  void saveCurrentPortfolio();

  /**
   * Fetches the current portfolio.
   *
   * @return portfolio entries.
   */
  HashMap<String, TreeMap<Date, StocksImpl>> getPortfolio();

  /**
   * Validates input for multi stocks.
   *
   * @param stocksInput    list of stocks.
   * @param investedAmount amount invetsed.
   * @param weightage      percentage of each stock.
   * @param fee            commission fee.
   * @param lowerDate      start date of portfolio.
   * @param upperDate      end date of portfolio.
   * @param frequency      number of days.
   * @return true if all are valid inputs.
   * @throws ParseException if invalid date.
   * @throws IOException    if not able read the stocks.
   */
  boolean isValidateInputForMultiStocks(String stocksInput, float investedAmount, String weightage,
      float fee,
      String lowerDate, String upperDate, int frequency)
      throws ParseException, IOException;

  /**
   * Adds the portfolio through dollar cost averaging.
   *
   * @param lowerDate        start date of the portfolio.
   * @param upperDate        end date of the portfolio.
   * @param frequency        number of days.
   * @param tickerValuesList list of stocks to be invested.
   * @param valueInvested    amount invested.
   * @param weightage        percentages for each stock.
   * @param fee              commission fee.
   * @throws ParseException if invalid date.
   */
  void addDollarCostAveragingStocks(String lowerDate, String upperDate, int frequency,
      String tickerValuesList, float valueInvested, String weightage,
      float fee)
      throws ParseException;

  /**
   * Fetches the list of portfolio.
   *
   * @return
   */
  String[] getPortfolioNames();

  /**
   * initialises the portfolio model, updates the required fields and renders the selected
   * portfolio.
   *
   * @param fileName name of the portfolio.
   * @return portfolio data.
   * @throws IOException    while reading a file.
   * @throws ParseException for invalid date.
   */
  HashMap<String, TreeMap<Date, StocksImpl>> renderTheSelectedPortfolio(String fileName)
      throws IOException, ParseException;

  /**
   * Calculates the composition of the portfolio.
   *
   * @param fileName    file name.
   * @param date        date on whihc the composition has to be calculated.
   * @param isCostBasis true if cost basis has to be calculated.
   * @return hashmap with required data.
   * @throws ParseException for invalid date.
   * @throws IOException    invalid input.
   */
  HashMap getCompositionOfThePortfolio(String fileName, String date, boolean isCostBasis)
      throws ParseException, IOException;


  /**
   * Sell the specified stocks.
   *
   * @param validDatesList        list of stocks.
   * @param newDate               date on which the stocks has to be sold.
   * @param numberOfSellingStocks number of stocks to sell.
   * @param fee                   commission fee.
   * @return amount of stocks left.
   * @throws ParseException for invalid date.
   */
  float sellTheStocks(TreeMap<Date, StocksImpl> validDatesList, String newDate,
      float numberOfSellingStocks, float fee) throws ParseException;

  /**
   * Validate the input to sell stocks.
   *
   * @param date   date.
   * @param shares number of shares.
   * @param fee    commission fee.
   * @return an integer based on the condition.
   */
  int validateSellStocks(String date, float shares, float fee);

  /**
   * Checks for valid date.
   *
   * @param date as string
   * @return true if valid date.
   */
  boolean isValidDate(String date);

  /**
   * Calculates the performance of the portfolio.
   *
   * @param date1 start date.
   * @param date2 end date.
   * @return price on each date for the stocks invested.
   * @throws IOException    invalid date.
   * @throws ParseException invalid date.
   */
  TreeMap<LocalDate, Integer> getPerformanceData(String date1, String date2)
      throws IOException, ParseException;

  /**
   * Checks valid inputs for performance.
   *
   * @param date1 start date.
   * @param date2 end date.
   * @return true if valid inputs
   */
  boolean areValidPerformanceDate(String date1, String date2);
}
