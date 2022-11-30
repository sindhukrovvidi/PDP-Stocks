package view;

import java.io.IOException;

/**
 * This interface contains all the methods to display a portfolio.
 */
public interface PortfolioView {

  /**
   * Method used to display a formatted portfolio.
   *
   * @param displayHeaders headers.
   * @param company        name of the company.
   * @param date           date.
   * @param open           opening price.
   * @param high           high price.
   * @param low            low price.
   * @param close          closing price.
   * @param volume         volume of stocks.
   * @param shares         shares brought.
   * @throws IOException invalid input.
   */
  void displayPortfolio(boolean displayHeaders, String company, String date,
      float open, float high,
      float low, float close, float volume, int shares, float fee)
      throws IOException;

  void viewCompositionOfPortfolio(boolean displayHeaders, String company, String date,
      float open,
      float close,
      int shares, float fee, float total, String dateOfComposition)
      throws IOException;

  void displayThePerformance(String result) throws IOException;

}
