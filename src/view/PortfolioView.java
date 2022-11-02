package view;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import model.Stocks;

public class PortfolioView {
  private Readable in;
  private Appendable out;

  public PortfolioView() {
    this.in = new InputStreamReader(System.in);
    this.out = System.out;
  }

  public void displayCurrentPortfolio(HashMap<String, Stocks> portfolio) throws IOException {
    this.out.append("Draft Portfolio").append('\n');
//    while(portfolio.hasNext())
    portfolio.forEach((k, v) -> {
      Stocks currentStock = (Stocks) v;
      try {
        this.out.append(currentStock.getCompany());
        this.out.append(currentStock.getDate());
        this.out.append((char) currentStock.getShares());
        this.out.append((char) currentStock.getHigh());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }
}
