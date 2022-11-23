package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

import model.StocksImpl;
import view.StockViewImpl;

import static model.Input.takeFloatInput;
import static model.Input.takeIntegerInput;
import static model.Input.takeStringInput;
import static model.Output.append;

/**
 * Class that contains all operations that are specific to the inflexible portfolio and extends the
 * abstract class StockControllerImpl.
 */
public class FlexibleStockControllerImpl extends StockControllerImpl {

  /**
   * Constructor that takes stocks model and view as parameters and initializes them.
   *
   * @param stocksImpl    model of stockImpl type.
   * @param stockViewImpl view of stockViewImpl type.
   */
  public FlexibleStockControllerImpl(StocksImpl stocksImpl, StockViewImpl stockViewImpl) {
    super(stocksImpl, stockViewImpl);
  }

  /**
   * Method used to obtain the stocks for a specific ticker value.
   *
   * @return the stocks of the desired ticker value.
   * @throws IOException invalid data.
   */
  public StocksImpl getTickerValue() throws IOException {
    String tickerValue = takeStringInput("Enter the ticker value:\n");
    tickerValue = tickerValue.toUpperCase();
    updateListOfStocks(tickerValue);
    HashMap map = getStockList().getLStocksMap();
    ArrayList values = (ArrayList) map.get(tickerValue);
    if (values == null) {
      append("You entered an invalid ticker symbol. Please try again");
      return null;
    } else {
      SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
      Date d1;
      Date d2;
      try {
        d1 = sdformat.parse(((StocksImpl) values.get(values.size() - 1)).getDate());
        d2 = sdformat.parse(((StocksImpl) values.get(0)).getDate());

        String dateInput =
            takeStringInput("Enter the dates between " + sdformat.format(d1) + " and "
                + sdformat.format(d2));
        Date newDate = sdformat.parse(dateInput);
        if (newDate.compareTo(d1) < 0 || newDate.compareTo(d2) > 0) {
          append("Entered an invalid date. Please enter the date within the given time range.\n");
          getTickerValue();
        }

        String formattedDateInput = sdformat.format(newDate);
        boolean foundDate = false;
        for (Object value : values) {
          StocksImpl currentStock = (StocksImpl) value;
          if (Objects.equals(currentStock.getDate(), formattedDateInput)) {
            model.setCurrentStock(tickerValue, currentStock.getDate(), currentStock.getOpen(),
                currentStock.getHigh(), currentStock.getLow(), currentStock.getClose(),
                currentStock.getVolume(), 0, 0);
            foundDate = true;
            break;
          }
        }
        if (!foundDate) {
          append("There is not stock data for the entered date as it could be an holiday.\n");
          model.setCurrentStock(tickerValue, dateInput, 0, 0, 0, 0, 0, 0, 0);
        }
      } catch (ParseException e) {
        append("RuntimeException, entered bad date format " + e + "\n");
        getTickerValue();
      }
      return afterStocksDisplay(model);
    }
  }

  /**
   * Method used to add a stock to a portfolio.
   *
   * @param models model to which the stocks to be added.
   * @return stocks added to portfolio.
   * @throws IOException invalid data.
   */
  public StocksImpl addStockToPortfolio(Object models) throws IOException {
    StocksImpl currModel = (StocksImpl) models;
    try {
      int value =
          takeIntegerInput(
              "Enter the number of shares you want to invest in "
                  + currModel.getCompany());
      float fee = takeFloatInput("Enter the commission fee and it has to greater than zero");

      if (value <= 0 || fee <= 0) {
        append("The entered values (shares & fee) should be greater"
            + " than 0.\n");
        return null;
      } else {
        currModel.updateStockValues(value);
        currModel.updateCommisionValue(fee);
        return currModel;
      }

    } catch (Exception e) {
      append("Please enter a valid number.\n");
      return null;
    }
  }

}
