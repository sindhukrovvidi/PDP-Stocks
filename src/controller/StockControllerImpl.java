package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import model.Stocks;
import model.StocksImpl;
import view.StockView;

import static model.Input.takeIntegerInput;

/**
 * Class that controls all the stock functions and implements the stock controller.
 */
abstract public class StockControllerImpl extends Controller implements StockController {

  protected StocksImpl model;
  protected StockView view;

  /**
   * Constructor that takes stocks model and view as parameters and initializes them.
   *
   * @param stocksImpl    model of stockImpl type.
   * @param stockViewImpl view of stockViewImpl type.
   */
  public StockControllerImpl(Stocks stocksImpl, StockView stockViewImpl) {
    this.model = (StocksImpl) stocksImpl;
    this.view = stockViewImpl;
  }

  /**
   * Method used to display the options after selecting a stock.
   *
   * @param model input model.
   * @return model.
   * @throws IOException invalid input.
   */
  @Override
  public StocksImpl afterStocksDisplay(Object model) throws IOException {
    StocksImpl currModel = (StocksImpl) model;
    try {
      view.stocksAfterStocksDisplay();
      Integer input = takeIntegerInput();
      switch (input) {
        case 1:
          currModel = this.addStockToPortfolio(currModel);
          break;
        case 2:
          currModel = this.getTickerValue();
          break;
        case 3:
          currModel = null;
          break;
        case 4:
          System.exit(0);
          break;
        default:
          view.inValidInput();
          afterStocksDisplay(model);
      }
    } catch (Exception e) {
      view.inValidInput();
      afterStocksDisplay(model);
    }
    return currModel;
  }

  /**
   * Method used to take the quantity of stocks to be added to the portfolio.
   *
   * @param models model to which the stocks to be added.
   * @return model with updated quantity.
   * @throws IOException invalid model or input.
   */
  @Override
  public StocksImpl addStockToPortfolio(Object models) throws IOException {
    StocksImpl currModel = (StocksImpl) models;
    try {
      view.enterSharesToInvest();
      int value = takeIntegerInput();

      if (value <= 0) {
        view.printSellErrorMessage();
        return null;
      } else {
        currModel.updateStockValues(value);
        return currModel;
      }

    } catch (Exception e) {
      view.inValidInput();
      return null;
    }
  }

  /**
   * Method used to display the stocks trend for a selected ticker symbol.
   *
   * @param companyName company for which we want the stock trend.
   * @param values      stock trends in the form of a list.
   */
  @Override
  public void controllerToViewHelperForStocks(String companyName,
                                              ArrayList<StocksImpl> values) {

    AtomicBoolean displayHeaders = new AtomicBoolean(true);
    values.forEach((v) -> {
      try {
        view.displayListOfDates(displayHeaders.get(), companyName,
                v.getDate(),
                v.getOpen(), v.getHigh(), v.getLow(),
                v.getClose(), v.getVolume());
        displayHeaders.set(false);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }

}
