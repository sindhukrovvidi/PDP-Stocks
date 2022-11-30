package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Class that implements all the methods in the HTTPRequests.
 */
public class HTTPRequestsImpl implements HTTPRequests {

  private final String apiKey = "CAAPHOM3N94LAFJL";

  /**
   * Method that is used to fetch the data from the API based on the given ticker symbol.
   *
   * @param tickerSymbol the company symbol for which we need the data.
   * @return a string builder with required data.
   * @throws IOException invalid data.
   */
  @Override
  public StringBuilder getData(String tickerSymbol) throws IOException {
    System.out.println("Calliong data for...." + tickerSymbol);
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

  /**
   * Method that is used to fetch the data from the API based on the given ticker symbol weekly.
   *
   * @param tickerSymbol the company symbol for which we need the data.
   * @return a string builder with required data.
   * @throws IOException invalid data.
   */
  public StringBuilder getWeeklyData(String tickerSymbol) throws IOException {
    URL url = null;
    url = new URL("https://www.alphavantage"
            + ".co/query?function=TIME_SERIES_WEEKLY_ADJUSTED"
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

  /**
   * Method that is used to fetch the data from the API based on the given ticker symbol monthly.
   *
   * @param tickerSymbol the company symbol for which we need the data.
   * @return a string builder with required data.
   * @throws IOException invlaid data.
   */
  @Override
  public StringBuilder getMonthlyData(String tickerSymbol) throws IOException {
    URL url = null;
    url = new URL("https://www.alphavantage"
            + ".co/query?function=TIME_SERIES_MONTHLY_ADJUSTED"
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

  /**
   * Method that is used to fetch the data from the API based on the given ticker symbol daily.
   *
   * @param tickerSymbol the company symbol for which we need the data.
   * @return a string builder with required data.
   * @throws IOException invalid data.
   */
  @Override
  public StringBuilder getDailyData(String tickerSymbol) throws IOException {
    URL url = null;
    url = new URL("https://www.alphavantage"
            + ".co/query?function=TIME_SERIES_DAILY"
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
