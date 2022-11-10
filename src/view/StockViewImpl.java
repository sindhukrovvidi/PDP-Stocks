package view;

import java.io.IOException;
import java.io.InputStreamReader;
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
}


