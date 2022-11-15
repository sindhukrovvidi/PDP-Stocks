package controller;

import java.util.HashMap;

import model.ListOfStocksImpl;
import model.PortfolioImpl;

import java.io.IOException;

import model.StocksImpl;

/**
 * This is an interface which contains all the methods for the portfolio controller.
 */
public interface PortfolioController {

  /**
   * Method that adds a stock into a portfolio.
   *
   * @return Portfolio after adding a stock.
   * @throws IOException if the stock is invalid or if the amount to be added is invalid.
   */
  PortfolioImpl addStock() throws IOException;

  /**
   * Method used to write into portfolio after adding a stock.
   *
   * @param model takes the model into which the stock is entered.
   * @return portfolio after saving the stock into it.
   * @throws IOException if the input parameter is invalid.
   */
  PortfolioImpl afterAddingStock(PortfolioImpl model) throws IOException;

  /**
   * Method used to view speculate of a portfolio.
   *
   * @param input file name.
   * @throws IOException if the input name is invalid.
   */
  void viewSpeculate(String input) throws IOException;

  /**
   * Method that is used as helper for speculation of a portfolio.
   *
   * @param name             file to be speculated.
   * @param listOfStocksImpl stocks in the portfolio in the form of a list.
   * @return true if the file is valid else false.
   * @throws IOException invalid input.
   */
  boolean viewSpeculateHelper(String name, ListOfStocksImpl listOfStocksImpl) throws IOException;

  /**
   * Fetches the data from the model for each argument and passes it to view.
   *
   * @param portfolioEntries has the list of portfolios.
   */
  void controllerToViewHelper(HashMap<String, StocksImpl> portfolioEntries);

}
