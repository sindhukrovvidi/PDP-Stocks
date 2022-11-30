package view;

import controller.MainController;
import java.io.IOException;
import java.text.ParseException;

/**
 * The interface contains the methods to display the list of stock trends.
 */
public interface StockView {

  /**
   * Method used to display the trends to aid the user to select.
   *
   * @param displayHeaders headers.
   * @param company        company name.
   * @param date           date.
   * @param open           opening price.
   * @param high           high price.
   * @param low            low price.
   * @param close          closing price.
   * @param volume         volume of stocks.
   * @throws IOException invalid input.
   */
  void displayListOfDates(boolean displayHeaders, String company, String date,
      float open, float high,
      float low, float close, float volume) throws IOException;

  /**
   * Renders the starter menu of the program.
   *
   * @param main main controller.
   * @throws IOException with invalid input/output.
   */
  void displayStarterMenu(MainController main) throws IOException;

  /**
   * Calls the respective function in controller.
   *
   * @param features main controller object
   * @throws IOException    with invalid input/output.
   * @throws ParseException with invalid date.
   */
  void addFeature(MainController features) throws IOException, ParseException;

  /**
   * Renders the portfolio
   *
   * @param displayHeaders boolean to display headers or not.
   * @param company        company name.
   * @param date           date invested.
   * @param open           open value.
   * @param high           high value.
   * @param low            low value.
   * @param close          close value.
   * @param volume         volume.
   * @param shares         shares invested.
   * @param fee            commission fee.
   * @throws IOException with invalid input/output.
   */
  void displayPortfolio(boolean displayHeaders, String company, String date,
      float open, float high,
      float low, float close, float volume, float shares, float fee) throws IOException;

  /**
   * Renders the composition of the stock.
   *
   * @param displayHeaders    boolean to display headers or not.
   * @param company           company name.
   * @param date              date invested.
   * @param open              open value.
   * @param close             close value.
   * @param shares            shares invested.
   * @param fee               commission fee.
   * @param total             total value of the stock.
   * @param dateOfComposition composition on that date.
   * @throws IOException with invalid input/output.
   */
  void viewCompositionOfPortfolio(boolean displayHeaders, String company, String date,
      float open,
      float close,
      float shares, float fee, float total, String dateOfComposition) throws IOException;

  /**
   * Render the performance of the portfolio for a giben time.
   *
   * @param result string to be rendered.
   * @throws IOException with invalid input/output.
   */
  void displayThePerformance(String result) throws IOException;

  /**
   * Displays flexible portfolio menu.
   *
   * @throws IOException with invalid input/output.
   */
  void displayFlexibleViewMenu() throws IOException;

  /**
   * Displays text to enter ticker value.
   *
   * @throws IOException with invalid input/output.
   */
  void enterTheTickerValue() throws IOException;

  /**
   * Displays the text to enter the number of stocks to sell.
   *
   * @throws IOException with invalid input/output.
   */
  void enterTheStocksToSell() throws IOException;

  /**
   * Displays text to enter commission fee.
   *
   * @throws IOException with invalid input/output.
   */
  void enterTheCommissionFee() throws IOException;

  /**
   * Prints to enter the date.
   *
   * @throws IOException with invalid input/output.
   */
  void enterTheDate() throws IOException;

  /**
   * Displays error message while selling stock.
   *
   * @throws IOException with invalid input/output.
   */
  void printSellErrorMessage() throws IOException;

  /**
   * Displays text when there are no sufficient stocks to sell.
   *
   * @param stocks no of stocks lack.
   * @throws IOException with invalid input/output.
   */
  void printLackStocks(float stocks) throws IOException;

  /**
   * error message for invalid ticker.
   *
   * @throws IOException with invalid input/output.
   */
  void printInvalidTicker() throws IOException;

  /**
   * Displays text to enter the lower limit of the date.
   *
   * @throws IOException with invalid input/output.
   */
  void enterLowerLimitDate() throws IOException;

  /**
   * Displays text to enter the upper limit of the date.
   *
   * @throws IOException with invalid input/output.
   */
  void enterUpperLimitDate() throws IOException;

  /**
   * Displays an error message while calculating the error meessage/
   *
   * @throws IOException with invalid input/output.
   */
  void performanceDateErrorMessage() throws IOException;

  /**
   * Asks whether to speculate the portfolio or not.
   *
   * @throws IOException with invalid input/output.
   */
  void isSpeculateMenu() throws IOException;

  /**
   * Displays text to enter the date for calculating the portfolio.
   *
   * @throws IOException with invalid input/output.
   */
  void enterDateforComposition() throws IOException;

  /**
   * Displays a message when the entered date is greater than current date.
   *
   * @throws IOException with invalid input/output.
   */
  void greaterDateMessage() throws IOException;

  /**
   * Displays message when the composition of the portfolio is 0.
   *
   * @throws IOException with invalid input/output.
   */
  void zeroComposition() throws IOException;

  /**
   * Displays error message for invalid date.
   *
   * @throws IOException with invalid input/output.
   */
  void inValidDateMessage() throws IOException;

  /**
   * Displays no stock date message.
   *
   * @throws IOException with invalid input/output.
   */
  void noStockDataForDate() throws IOException;

  /**
   * Prints text to enter the timerange.
   *
   * @param date1 lower limit of the timerange.
   * @param date2 upper limit of the timerange.
   * @throws IOException with invalid input/output.
   */
  void displayDatesTimerange(String date1, String date2) throws IOException;

  /**
   * prints text to enter the number of shares.
   *
   * @throws IOException with invalid input/output.
   */
  void enterSharesToInvest() throws IOException;

  /**
   * Dsiplays the total price of the portfolio.
   *
   * @param price price of the portfolio.
   * @throws IOException with invalid input/output.
   */
  void totalPortfolioPrice(float price) throws IOException;

  /**
   * Prints the text to enter the portfolio name.
   *
   * @throws IOException with invalid input/output.
   */
  void enterPortfolioName() throws IOException;

  /**
   * Prints a text to when a portfolio already exists.
   *
   * @throws IOException with invalid input/output.
   */
  void portfolioExistsMessage() throws IOException;

  /**
   * Prints text to enter the existing portfolio names.
   *
   * @param list list of portfolio names.
   * @throws IOException with invalid input/output.
   */
  void enterExistingPortfolioName(String list) throws IOException;

  /**
   * Displays the menu after adding stocks to the portfolio.
   *
   * @throws IOException with invalid input/output.
   */
  void afterAddingStocksMenu() throws IOException;

  /**
   * Prints a message for invalid input.
   *
   * @throws IOException with invalid input/output.
   */
  void inValidInput() throws IOException;

  /**
   * Displays the menu to add multiple stocks at once.
   *
   * @throws IOException with invalid input/output.
   */
  void displayBulkAdditionMenu() throws IOException;

  /**
   * Prints text to enter the list of Stocks.
   *
   * @throws IOException with invalid input/output.
   */
  void enterListOfStocks() throws IOException;

  /**
   * Prints text to enter the amount ot be invested.
   *
   * @throws IOException with invalid input/output.
   */
  void enterAmountToInvest() throws IOException;

  /**
   * Print the text to enter the percentages to be invested.
   *
   * @throws IOException with invalid input/output.
   */
  void enterTheWeightage() throws IOException;

  /**
   * Print the text to enter the frequency.
   *
   * @throws IOException with invalid input/output.
   */
  void enterFrequency() throws IOException;

  /**
   * Displays the menu after adding a stock.
   *
   * @throws IOException with invalid input/output.
   */
  void stocksAfterStocksDisplay() throws IOException;
}
