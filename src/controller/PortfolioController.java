package controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import java.util.TreeMap;

import model.Portfolio;
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
  Portfolio addStock() throws IOException, ParseException;

  /**
   * Method used to write into portfolio after adding a stock.
   *
   * @param model takes the model into which the stock is entered.
   * @return portfolio after saving the stock into it.
   * @throws IOException if the input parameter is invalid.
   */
  Portfolio afterAddingStock(Portfolio model) throws IOException;

  /**
   * Method used to view speculate of a portfolio.
   *
   * @param input file name.
   * @throws IOException if the input name is invalid.
   */
  Portfolio viewSpeculate(String input) throws IOException;

  /**
   * Fetches the data from the model for each argument and passes it to view.
   *
   * @param portfolioEntries has the list of portfolios.
   */
  void controllerToViewHelper(HashMap<String, TreeMap<Date, StocksImpl>> portfolioEntries);

}
