package view;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;

public class StockViewImpl implements StockView {

  private Readable in;
  private Appendable out;

  public StockViewImpl() {
    this.in = new InputStreamReader(System.in);
    this.out = System.out;
  }

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


