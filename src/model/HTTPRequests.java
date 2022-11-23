package model;

import java.io.IOException;

/**
 * The interface that contains HTTP requests to get the daily, weekly and monthly data.
 */
public interface HTTPRequests {

  /**
   * Method that is used to fetch the data from the API based on the given ticker symbol.
   *
   * @param tickerSymbol the company symbol for which we need the data.
   * @return a string builder with required data.
   * @throws IOException invalid data.
   */
  StringBuilder getData(String tickerSymbol) throws IOException;

  /**
   * Method that is used to fetch the data from the API based on the given ticker symbol weekly.
   *
   * @param tickerSymbol the company symbol for which we need the data.
   * @return a string builder with required data.
   * @throws IOException invalid data.
   */
  StringBuilder getWeeklyData(String tickerSymbol) throws IOException;

  /**
   * Method that is used to fetch the data from the API based on the given ticker symbol monthly.
   *
   * @param tickerSymbol the company symbol for which we need the data.
   * @return a string builder with required data.
   * @throws IOException invalid data.
   */
  StringBuilder getMonthlyData(String tickerSymbol) throws IOException;

  /**
   * Method that is used to fetch the data from the API based on the given ticker symbol weekly.
   *
   * @param tickerSymbol the company symbol for which we need the data.
   * @return a string builder with required data.
   * @throws IOException invalid data.
   */
  StringBuilder getDailyData(String tickerSymbol) throws IOException;

}
