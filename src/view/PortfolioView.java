package view;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;

public class PortfolioView {

  private Readable in;
  private final Appendable out;

  public PortfolioView() {
    this.in = new InputStreamReader(System.in);
    this.out = System.out;
  }

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
