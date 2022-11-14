package view;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;

/**
 * This class implements all the methods in the portfolioview interface.
 */
public class PortfolioViewImpl implements PortfolioView {

  private final Appendable out;

  /**
   * constructor to initialize the in and out.
   */
  public PortfolioViewImpl() {
    Readable in = new InputStreamReader(System.in);
    this.out = System.out;
  }

  /**
   * Method used to display a formatted portfolio.
   *
   * @param displayHeaders headers.
   * @param company        name of the company.
   * @param date           date.
   * @param open           opening price.
   * @param high           high price.
   * @param low            low price.
   * @param close          closing price.
   * @param volume         volume of stocks.
   * @param shares         shares brought.
   * @throws IOException invalid input.
   */
  @Override
  public void displayPortfolio(boolean displayHeaders, String company, String date,
                               float open, float high,
                               float low, float close, float volume, int shares) throws IOException {

    Formatter fmt = new Formatter();
    if (displayHeaders) {
      fmt.format("%15s %15s %15s %15s %15s %15s %15s %15s %15s\n", "Company", "Date", "Open",
              "High", "Low",
              "Close", "Volume", "Shares Invested", "Total Value"
                      + "\n");
    }
    fmt.format("%15s %15s %15s %15s %15s %15s %15s %15s %15s\n", company, date, open, high, low,
            close, volume, shares, (shares * close));
    this.out.append(fmt.toString());
  }
}
