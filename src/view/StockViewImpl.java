package view;

import controller.MainController;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Formatter;

/**
 * This class implements the stockview interface.
 */
public class StockViewImpl implements StockView {

  private Appendable out;

  /**
   * Constructor that initializes in and out.
   */
  public StockViewImpl() {
    Readable in = new InputStreamReader(System.in);
    this.out = System.out;
  }

  /**
   * Method used to format and display the list.
   *
   * @param displayHeaders headings.
   * @param company        company name.
   * @param date           date.
   * @param open           opening price.
   * @param high           high price.
   * @param low            low price.
   * @param close          closing price.
   * @param volume         volume.
   * @throws IOException invalid input.
   */
  @Override
  public void displayListOfDates(boolean displayHeaders, String company, String date,
                                 float open, float high,
                                 float low, float close, float volume) throws IOException {

    Formatter fmt = new Formatter();
    if (displayHeaders) {
      fmt.format("Following are the stock details of the company " + company + ".\n");
      fmt.format("%15s %15s %15s %15s %15s %15s\n", "Date", "Open", "High", "Low",
              "close", "Volume");
    }
    fmt.format("%15s %15s %15s %15s %15s %15s\n", date, open, high, low,
            close, volume);
    this.out.append(fmt.toString());
  }

  @Override
  public void displayPortfolio(boolean displayHeaders, String company, String date,
                               float open, float high,
                               float low, float close, float volume, float shares, float fee)
          throws IOException {

    Formatter fmt = new Formatter();
    if (displayHeaders) {
      fmt.format("%15s %15s %15s %15s %15s %15s %15s %15s %15s %15s\n", "Company", "Date", "Open",
              "High", "Low",
              "Close", "Volume", "Shares Invested", "Total Value", "Commission fee"
                      + "\n");
    }
    fmt.format("%15s %15s %15s %15s %15s %15s %15s %15s %15s %15s\n", company, date, open, high,
            low,
            close, volume, shares, (shares * close), fee);
    this.out.append(fmt.toString());
  }

  @Override
  public void viewCompositionOfPortfolio(boolean displayHeaders, String company, String date,
                                         float open,
                                         float close,
                                         float shares, float fee, float total,
                                         String dateOfComposition) throws IOException {
    Formatter fmt = new Formatter();
    if (displayHeaders) {
      this.out.append("Total value till " + dateOfComposition + " is: " + total + "\n");
      this.out.append(
              "Following is the list of stocks till date: " + dateOfComposition + "\n");
      fmt.format("%15s %15s %15s %15s %15s %15s\n", "Company", "Date", "Open",
              "Close", "Shares Invested", "Commission fee"
                      + "\n");
    }
    fmt.format("%15s %15s %15s %15s %15s %15s \n", company, date, open,
            close, shares, fee);
    this.out.append(fmt.toString());

  }

  /**
   * Pronts the performance bar.
   *
   * @param result print.
   * @throws IOException if invalid.
   */
  @Override
  public void displayThePerformance(String result) throws IOException {
    this.out.append(result);
  }

  @Override
  public void displayFlexibleViewMenu() throws IOException {
    this.out.append("Choose from the below options:\n 1.Buy stocks.\n "
            + "2.Sell the stocks.\n 3.Get the total cost basis for the portfolio.\n 4.Get the "
            + "composition of the portfolio.\n 5.Get the portfolio performance over time.\n " +
            "6.Exit");
  }

  @Override
  public void enterTheTickerValue() throws IOException {
    this.out.append("Enter the ticker symbol.");
  }

  @Override
  public void enterTheStocksToSell() throws IOException {
    this.out.append("Please enter the number of stocks you want to sell");
  }

  @Override
  public void enterTheCommissionFee() throws IOException {
    this.out.append("Enter the commission fee and it has to greater than zero");
  }

  @Override
  public void enterTheDate() throws IOException {
    this.out.append("Please enter a valid date as per the above list");
  }

  @Override
  public void printSellErrorMessage() throws IOException {
    this.out.append("The entered values (shares & fee) should be greater than 0.");
  }

  @Override
  public void printLackStocks(float stocksSold) throws IOException {
    this.out.append("Your portfolio lacks " + stocksSold + " they are not sold!");
  }

  @Override
  public void printInvalidTicker() throws IOException {
    this.out.append("The ticker symbol entered is invalid.");
  }

  @Override
  public void enterLowerLimitDate() throws IOException {
    this.out.append("Enter the lower limit of the time range in yyyy-mm-dd format");
  }

  @Override
  public void enterUpperLimitDate() throws IOException {
    this.out.append("Enter the upper limit of the time range in yyyy-mm-dd format");
  }

  @Override
  public void performanceDateErrorMessage() throws IOException {
    this.out.append("The given dates are either invalid or exceeded the limit!!!");
  }

  @Override
  public void isSpeculateMenu() throws IOException {
    this.out.append("Would you like to speculate your portfolio?(YES/NO)");
  }

  @Override
  public void enterDateforComposition() throws IOException {
    this.out.append(
            "Enter the date in YYYY-MM-DD to view the composition on that particular date.");
  }

  @Override
  public void greaterDateMessage() throws IOException {
    this.out.append("The entered date is greater than current date, so can't evaluate your "
            + "portfolio");
  }

  @Override
  public void zeroComposition() throws IOException {
    this.out.append(
            "Total composition of the portfolio is 0 as you do not have any stocks purchased by "
                    + "then.");
  }

  @Override
  public void displayStarterMenu(MainController main) throws IOException {
    this.out.append("Choose from below options to proceed further. (Type the index number).\n"
            + "1. Create a inflexible portfolio.\n"
            + "2. Create a flexible portfolio.\n"
            + "3. View inflexible portfolio.\n"
            + "4. View/Edit/Sell flexible portfolio.\n"
            + "5. Exit\n");
  }

  @Override
  public void addFeature(MainController main) throws IOException, ParseException {
  }

  @Override
  public void inValidDateMessage() throws IOException {
    this.out.append("Entered an invalid date, check!!");
  }

  @Override
  public void noStockDataForDate() throws IOException {
    this.out.append("There is not stock data for the entered date as it could be an holiday.");
  }

  @Override
  public void displayDatesTimerange(String date1, String date2) throws IOException {
    this.out.append("Enter the dates between " + date1 + " and " + date2);
  }

  @Override
  public void enterSharesToInvest() throws IOException {
    this.out.append("Enter the number of shares you want to invest");
  }

  @Override
  public void totalPortfolioPrice(float price) throws IOException {
    this.out.append("Total price of portfolio is " + price);
  }

  @Override
  public void enterPortfolioName() throws IOException {
    this.out.append("Enter the name for your portfolio.");
  }

  @Override
  public void portfolioExistsMessage() throws IOException {
    this.out.append("Invalid input or the file already exists. Please try again.");
  }

  @Override
  public void enterExistingPortfolioName(String list) throws IOException {
    this.out.append("Enter the name of the portfolio from the below list: (Just enter the "
            + "filename without the extension \n"
            + list);
  }

  @Override
  public void afterAddingStocksMenu() throws IOException {
    this.out.append("Choose from below options.");
    this.out.append("1. Add another stock.");
    this.out.append("2. Save this portfolio.");
    this.out.append("3. Back to main menu.");
    this.out.append("4. Exit.");
  }

  @Override
  public void inValidInput() throws IOException {
    this.out.append("Entered invalid input, try again.!!");
  }

  @Override
  public void displayBulkAdditionMenu() throws IOException {
    this.out.append("Choose from the below options.\n"
            + "1. Add a single stock to the portfolio.\n"
            + "2. Add multiple stocks at once.\n"
            + "3. Save the current portfolio.\n");
  }

  @Override
  public void enterListOfStocks() throws IOException {
    this.out.append("Enter the list of stocks with coma (,) separated.\n");
  }

  @Override
  public void enterAmountToInvest() throws IOException {
    this.out.append("Enter the amount to invest");
  }

  @Override
  public void enterTheWeightage() throws IOException {
    this.out.append("Enter the weightage for each stock. Keep in mind that it "
            + "should add up to 100!!!");
  }

  @Override
  public void enterFrequency() throws IOException {
    this.out.append("Enter the frequency in days");
  }

  @Override
  public void stocksAfterStocksDisplay() throws IOException {
    this.out.append("Select from the following options.\n1. Add this to "
            + "portfolio.\n2. Do not add and search stocks for new company.\n3. Go back. "
            + "\n 4. Exit\n");
  }
}


