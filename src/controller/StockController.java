package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.concurrent.atomic.AtomicBoolean;
import model.StocksImpl;
import model.ListOfStocksImpl;
import view.StockView;

import static model.Input.takeIntegerInput;
import static model.Input.takeStringInput;
import static model.Output.append;

/**
 *
 */
public class StockController {

  private StocksImpl model;
  private StockView view;

  private ListOfStocksImpl listOfStocksImpl;

  /**
   *
   * @param stocksImpl
   * @param stockView
   */
  public StockController(StocksImpl stocksImpl, StockView stockView) {
    this.model = stocksImpl;
    this.view = stockView;
  }

  protected StocksImpl afterStocksDisplay(Object model) throws IOException {
    StocksImpl currModel = (StocksImpl) model;
    try {
      Integer input = takeIntegerInput("Select from the following options.\n1. Add this to "
          + "portfolio.\n2. Do not add and search stocks for new company.\n3. Go back. "
          + "\n 4. Exit\n");
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
          System.out.println("Invalid input");
          System.exit(0);
      }
    } catch (Exception e) {
      append("Entered invalid input, try again.");
      afterStocksDisplay(model);
    }
    return currModel;
  }

  protected void setStocksList(ListOfStocksImpl list) {
    listOfStocksImpl = list;
  }

  protected StocksImpl getTickerValue() throws IOException {
    String tickerValue = takeStringInput("Enter the ticker value.");
    HashMap map = listOfStocksImpl.getLStocksMap();
    ArrayList values = (ArrayList) map.get(tickerValue);
    if (values == null) {
      append("You entered an invalid ticker symbol. Please try again");
      return null;
    } else {
      StocksImpl currentStock = (StocksImpl) values.get(0);
      model.setCurrentStock(tickerValue, currentStock.getDate(), currentStock.getOpen(),
          currentStock.getHigh(), currentStock.getLow(), currentStock.getClose(),
          currentStock.getVolume(), 0);
      controllerToViewHelperForStocks(tickerValue, values);
//      view.displayListOfDates(tickerValue, values);
      return afterStocksDisplay(model);
    }
  }

  protected StocksImpl addStockToPortfolio(Object models) throws IOException {
    StocksImpl currModel = (StocksImpl) models;
    try {
      int value =
          takeIntegerInput(
              "Enter the number of shares you want to invest in " + currModel.getCompany());
      currModel.updateStockValues(value);
    } catch (Exception e) {
      append("Please enter a valid number.");
    }
    return currModel;
  }

  /**
   *
   * @param companyName
   * @param values
   */
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
