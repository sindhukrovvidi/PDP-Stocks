package view;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Formatter;
import model.Stocks;

public class StockView {

  private Readable in;
  private Appendable out;

  public StockView() {
    this.in = new InputStreamReader(System.in);
    this.out = System.out;
  }

  public void displayListOfDates(String company, ArrayList values) throws IOException {
    this.out.append("Following are the stock details of the company ").append(company).append('\n');
    Formatter fmt = new Formatter();
    fmt.format("%15s %15s %15s %15s %15s %15s\n", "Date", "Open", "High", "Low", "Close", "Volume"
            + "\n");
    for (int i = 0; i < values.size(); i++) {
      Stocks currentStock = (Stocks) values.get(i);
      fmt.format("%15s %15s %15s %15s %15s %15s\n", currentStock.getDate(),
          currentStock.getOpen(), currentStock.getHigh(), currentStock.getLow(),
          currentStock.getClose(), currentStock.getVolume());
    }
    this.out.append(fmt.toString());
  }
}
