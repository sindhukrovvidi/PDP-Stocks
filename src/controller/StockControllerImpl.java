package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.concurrent.atomic.AtomicBoolean;
import model.StocksImpl;
import model.ListOfStocksImpl;
import view.StockViewImpl;

import static model.Input.takeIntegerInput;
import static model.Input.takeStringInput;
import static model.Output.append;

/**
 *
 */
public class StockControllerImpl implements StockController {

  private StocksImpl model;
  private StockViewImpl view;

  private ListOfStocksImpl listOfStocksImpl;

  /**
   * @param stocksImpl
   * @param stockViewImpl
   */
  public StockControllerImpl(StocksImpl stocksImpl, StockViewImpl stockViewImpl) {
    this.model = stocksImpl;
    this.view = stockViewImpl;
  }

  @Override
  public StocksImpl afterStocksDisplay(Object model) throws IOException {
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

  @Override
  public void setStocksList(ListOfStocksImpl list) {
    listOfStocksImpl = list;
  }

  @Override
  public StocksImpl getTickerValue() throws IOException {
    HashMap map = listOfStocksImpl.getLStocksMap();
    String tickerValue = takeStringInput("Enter the ticker value from the following set "
        + "of companies:\n" + map.keySet());
//    String listOfCompanies = String.valueOf(map.keySet());
//    append(listOfCompanies);

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
      return afterStocksDisplay(model);
    }
  }

  @Override
  public StocksImpl addStockToPortfolio(Object models) throws IOException {
    StocksImpl currModel = (StocksImpl) models;
    try {
      int value =
          takeIntegerInput(
              "Enter the number of shares you want to invest in " + currModel.getCompany());
      if (value <= 0) {
        throw new IllegalArgumentException("The number of stocks to be invested should be greater"
            + " than 0.\n");
      } else {
        currModel.updateStockValues(value);
        return currModel;
      }

    } catch (Exception e) {
      append("Please enter a valid number.");
      return null;
    }
  }

  /**
   * @param companyName
   * @param values
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
