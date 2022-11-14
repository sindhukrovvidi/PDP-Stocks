package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.concurrent.atomic.AtomicBoolean;

import model.HTTPRequests;
import model.HTTPRequestsImpl;
import model.StocksImpl;
import model.ListOfStocksImpl;
import view.StockViewImpl;

import static model.Input.takeIntegerInput;
import static model.Input.takeStringInput;
import static model.Output.append;

/**
 * Class that controls all the stock funtions and implements the stock controller.
 */
public class StockControllerImpl extends Controller implements StockController {

  private StocksImpl model;
  private StockViewImpl view;
  private boolean isFlexible;

  /**
   * Constructor that takes stocks model and view as parameters and initializes them.
   *
   * @param stocksImpl    model of stockImpl type.
   * @param stockViewImpl view of stockViewImpl type.
   */
  public StockControllerImpl(StocksImpl stocksImpl, StockViewImpl stockViewImpl) {
    this.model = stocksImpl;
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

  public void setIsFlexible(boolean isFlexible) {
    this.isFlexible = isFlexible;
  }

  @Override
  public StocksImpl getTickerValue() throws IOException {
    String tickerValue = takeStringInput("Enter the ticker value:\n");
    updateListOfStocks(tickerValue);
    HashMap map = getStockList().getLStocksMap();
    ArrayList values = (ArrayList) map.get(tickerValue);
    if (values == null) {
      append("You entered an invalid ticker symbol. Please try again");
      return null;
    } else {
      if (isFlexible) {

        String input = takeStringInput("Enter a date for purchasing the stocks:");
        // TODO handle invalid date.
        // TODO give the date span.
        int n = values.size();
        for (int i = 0; i < n; i++) {
          StocksImpl currentStock = (StocksImpl) values.get(i);
          if (currentStock.getDate().equals(input)) {
            model.setCurrentStock(tickerValue, currentStock.getDate(), currentStock.getOpen(),
                    currentStock.getHigh(), currentStock.getLow(), currentStock.getClose(),
                    currentStock.getVolume(), 0);
            break;

          }
        }
      } else {
        StocksImpl currentStock = (StocksImpl) values.get(0);
        model.setCurrentStock(tickerValue, currentStock.getDate(), currentStock.getOpen(),
                currentStock.getHigh(), currentStock.getLow(), currentStock.getClose(),
                currentStock.getVolume(), 0);
      }
      controllerToViewHelperForStocks(tickerValue, values);
      return afterStocksDisplay(model);
    }
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
      int value =
              takeIntegerInput(
                      "Enter the number of shares you want to invest in "
                              + currModel.getCompany());
      if (value <= 0) {
        throw new IllegalArgumentException("The number of stocks to be invested should be greater"
                + " than 0.\n");
      } else {
        currModel.updateStockValues(value);
        return currModel;
      }

    } catch (Exception e) {
      append("Please enter a valid number.\n");
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
