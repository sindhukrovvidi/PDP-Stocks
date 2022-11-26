package view;

import controller.MainController;
import controller.MainControllerImpl;
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
  public void displayStarterMenu(MainController main) throws IOException {
    this.out.append("Choose from below options to proceed further. (Type the index number)."
        + " \n1. Create a "
        + "portfolio.\n2. View & speculate existing portfolio " +
        "\n3. Exit\n");
  }

  @Override
  public void addFeature(MainController main) throws IOException, ParseException {
    main.renderMainMenu();
  }

  @Override
  public void displayPortfolio(boolean displayHeaders, String company, String date,
      float open, float high,
      float low, float close, float volume, int shares, float fee) throws IOException {

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
      int shares, float fee, float total, String dateOfComposition) throws IOException {
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
   * @param result print.
   * @throws IOException if invalid.
   */
  @Override
  public void displayThePerformance(String result) throws IOException {
    this.out.append(result);

  }
}


