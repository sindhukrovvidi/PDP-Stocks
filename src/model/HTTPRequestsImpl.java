package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HTTPRequestsImpl implements HTTPRequests {

  private final String apiKey = "CAAPHOM3N94LAFJL";

  @Override
  public StringBuilder getData(String tickerSymbol) throws IOException {
    URL url = null;
    url = new URL("https://www.alphavantage"
        + ".co/query?function=TIME_SERIES_DAILY"
        + "&outputsize=full"
        + "&symbol"
        + "=" + tickerSymbol + "&apikey=" + apiKey + "&datatype=csv");
    InputStream in = null;
    StringBuilder output = new StringBuilder();
    in = url.openStream();
    int b;

    while ((b = in.read()) != -1) {
      output.append((char) b);
      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    }
    return output;
  }
}