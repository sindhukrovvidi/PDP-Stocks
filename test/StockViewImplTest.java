import java.io.IOException;
import java.util.Formatter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import view.StockViewImpl;

/**
 * Testing stock view.
 */
public class StockViewImplTest {

  @Test
  public void testDisplayWithHeaders() throws IOException {
    StringBuffer out = new StringBuffer();
    StockViewImpl stockView = new StockViewImpl();
    stockView.displayListOfDates(true, "IBM", "2022-11-02",
        1.23f, 1.34f,
        1.567f, 9.77f, 9.00f);
    Formatter fmt = new Formatter();

    fmt.format("Following are the stock details of the company " + "IBM" + ".\n");
    fmt.format("%15s %15s %15s %15s %15s %15s\n", "Date", "Open", "High", "Low",
        "close", "Volume");

    fmt.format("%15s %15s %15s %15s %15s %15s\n", "2022-11-02",
        1.23f, 1.34f,
        1.567f, 9.77f, 9.00f);
    String result = fmt.toString();
    assertEquals(result, (out.append(fmt)).toString());
  }

  @Test
  public void testDisplayWithoutHeaders() throws IOException {
    StringBuffer out = new StringBuffer();
    StockViewImpl stockView = new StockViewImpl();
    stockView.displayListOfDates(false, "IBM", "2022-11-02",
        1.23f, 1.34f,
        1.567f, 9.77f, 9.00f);
    Formatter fmt = new Formatter();

    fmt.format("%15s %15s %15s %15s %15s %15s\n", "2022-11-02",
        1.23f, 1.34f,
        1.567f, 9.77f, 9.00f);
    String result = fmt.toString();
    assertEquals(result, (out.append(fmt)).toString());
  }

}
