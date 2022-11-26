package view;

import controller.MainController;
import java.io.IOException;
import java.text.ParseException;

/**
 * The interface contains the methods to display the list of stock trends.
 */
public interface StockView {

  /**
   * Method used to display the trends to aid the user to select.
   *
   * @param displayHeaders headers.
   * @param company        company name.
   * @param date           date.
   * @param open           opening price.
   * @param high           high price.
   * @param low            low price.
   * @param close          closing price.
   * @param volume         volume of stocks.
   * @throws IOException invalid input.
   */
  void displayListOfDates(boolean displayHeaders, String company, String date,
                          float open, float high,
                          float low, float close, float volume) throws IOException;

  void displayStarterMenu(MainController main) throws IOException;

  void addFeature(MainController features) throws IOException, ParseException;

  void displayPortfolio(boolean displayHeaders, String company, String date,
      float open, float high,
      float low, float close, float volume, int shares, float fee) throws IOException;

  void viewCompositionOfPortfolio(boolean displayHeaders, String company, String date,
      float open,
      float close,
      int shares, float fee, float total, String dateOfComposition) throws IOException;

  void displayThePerformance(String result) throws IOException;
}
