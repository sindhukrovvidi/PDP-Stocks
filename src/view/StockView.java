package view;

import java.io.IOException;

/**
 * The interface contains the methods to display the list of stock trends.
 */
public interface StockView {

  /**
   * Method used to display the trends to aid the user to select.
   *
   * @param displayHeaders headers.
   * @param company company name.
   * @param date date.
   * @param open opening price.
   * @param high high price.
   * @param low low price.
   * @param close closing price.
   * @param volume volume of stocks.
   * @throws IOException invalid input.
   */
  void displayListOfDates(boolean displayHeaders, String company, String date,
      float open, float high,
      float low, float close, float volume) throws IOException;

}
