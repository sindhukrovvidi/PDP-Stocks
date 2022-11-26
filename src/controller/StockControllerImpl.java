package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import model.Stocks;
import model.StocksImpl;
import view.StockView;
import view.StockViewImpl;

import static model.Input.takeFloatInput;
import static model.Input.takeIntegerInput;
import static model.Input.takeStringInput;
import static model.Output.append;

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
          afterStocksDisplay(model);
      }
    } catch (Exception e) {
      append("Entered invalid input, try again.");
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
      int value =
          takeIntegerInput(
              "Enter the number of shares you want to invest in "
                  + currModel.getCompany());
      if (value <= 0) {
        append("The number of stocks to be invested should be greater"
            + " than 0.\n");
        return null;
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

//  @Override
//  public StocksImpl isBulkStockAddition() throws IOException, ParseException {
//    StocksImpl stocksImpl = null;
//    int input = takeIntegerInput("Choose from the below options.\n"
//        + "1. Add a single stock to the portfolio.\n"
//        + "2. Add multiple stocks at once.\n");
//    switch (input) {
//      case 1:
//        stocksImpl = getTickerValue();
//        break;
//      case 2:
//        investMultipleStocksAtOnce();
//        break;
//      default:
//        System.exit(0);
//    }
//    if (stocksImpl == null) {
//      isBulkStockAddition();
//    } else {
////      this.model = stocksImpl;
////      getInitialController(5);
//    }
//    return stocksImpl;
//  }
//
//  public void investMultipleStocksAtOnce() throws IOException, ParseException {
//    String stocksInput = takeStringInput("Enter the list of stocks with coma (,) separated.");
//    float valueInvested = takeFloatInput("Enter the amount to be invested.");
//    String weightage = takeStringInput("Enter the weightage for each stock. Keep in mind that it "
//        + "should add up to 100!!!");
//    int fee = takeIntegerInput("Enter the commission fee");
//    String lowerDate = takeStringInput("Enter the lower limit of the timerange in yyyy-mm-dd");
//    String upperDate = takeStringInput("Enter the upper limit of the timerange in yyyy-mm-dd");
//    int frequency = takeIntegerInput("Enter the frequency in days.");
//    String[] tickerValuesList = stocksInput.split(",");
//    for (String s : tickerValuesList) {
//      updateListOfStocks(s.trim().toUpperCase());
//      HashMap map = getStockList().getLStocksMap();
//      ArrayList values = (ArrayList) map.get(s.trim().toUpperCase());
//      if(values == null) {
//        investMultipleStocksAtOnce();
////        return null;
//      }
//    }
//
//    boolean areValid = model.validateInputForMultiStocks(valueInvested, weightage, fee, lowerDate,
//        upperDate, frequency);
//    if(!areValid) {
//      investMultipleStocksAtOnce();
//    } else {
//      // has to be in model
//      model.addMultipleStocksInPortfolio(getStockList().getLStocksMap(), lowerDate, upperDate,
//          frequency);
//    }
//  }

}
