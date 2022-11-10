import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Formatter;
import org.junit.Test;
import view.PortfolioViewImpl;

/**
 * Testing view.
 */
public class PortfolioViewImplTest {

  @Test
  public void testDisplayPortfolioWithHeader() throws IOException {
    StringBuffer out = new StringBuffer();
    PortfolioViewImpl portfolio = new PortfolioViewImpl();
    portfolio.displayPortfolio(true, "GOOG", "2022-10-26",
        1.45f, 2.45f,
        4.56f, 1.9f, 5.6f, 10);
    Formatter fmt = new Formatter();

    fmt.format("%15s %15s %15s %15s %15s %15s %15s %15s %15s\n", "Company", "Date", "Open",
        "High", "Low",
        "Close", "Volume", "Shares Invested", "Total Value"
            + "\n");

    fmt.format("%15s %15s %15s %15s %15s %15s %15s %15s %15s\n", "GOOG", "2022-10-26", 1.45f, 2.45f,
        4.56f,
        1.9f, 5.6f, 10, 19);
    String result = fmt.toString();
    assertEquals(result, (out.append(fmt)).toString());
  }

  @Test
  public void testDisplayPortfolioWithoutHeader() throws IOException {
    StringBuffer out = new StringBuffer();
    PortfolioViewImpl portfolio = new PortfolioViewImpl();
    portfolio.displayPortfolio(true, "GOOG", "2022-10-26",
        1.45f, 2.45f,
        4.56f, 1.9f, 5.6f, 10);
    Formatter fmt = new Formatter();
    fmt.format("%15s %15s %15s %15s %15s %15s %15s %15s %15s\n", "GOOG", "2022-10-26", 1.45f, 2.45f,
        4.56f,
        1.9f, 5.6f, 10, 19);
    String result = fmt.toString();
    assertEquals(result, (out.append(fmt)).toString());
  }

}
