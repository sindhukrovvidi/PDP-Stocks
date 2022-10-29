import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.dnd.*;

public class AlphaVantageDemo {

  private String apiKey = "5VTE6APNI1ZMURXC";
//  private String stockSymbol = "GOOG";
  private URL url = null;

  public void fetchData(String stockSymbol) {
    try {
      url = new URL("https://www.alphavantage"
          + ".co/query?function=GLOBAL_QUOTE"
//          + "&outputsize=compact"
          + "&symbol"
          + "=" + "IBM" + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
          + "no longer works");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
    System.out.println("Return value: ");
    System.out.println(output.toString());
  }

  public void searchTickers(String stockSymbol) {
    fetchData(stockSymbol);
  }
}
