package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.StocksImpl;

/**
 * Interface that has all the methods responsible for controlling the stocks.
 */
public interface StockController {

  /**
   * Method used to display the options after selecting a stock.
   *
   * @param model input model.
   * @return implemented stock.
   * @throws IOException invalid input.
   */
  StocksImpl afterStocksDisplay(Object model) throws IOException;

  /**
   * Method used to take a ticker value.
   *
   * @return displays all the 10 days data model of the selected ticker value.
   * @throws IOException invalid input.
   */
  StocksImpl getTickerValue() throws IOException;

  /**
   * Method used to take the quantity of stocks to be added to the portfolio.
   *
   * @param models model to which the stocks to be added.
   * @return model with updated quantity.
   * @throws IOException invalid model or input.
   */
  StocksImpl addStockToPortfolio(Object models) throws IOException;

  /**
   * Method used to display the stocks trend for a selected ticker symbol.
   *
   * @param companyName company for which we want the stock trend.
   * @param values      stock trends in the form of a list.
   */
  void controllerToViewHelperForStocks(String companyName,
                                       ArrayList<StocksImpl> values);


}
