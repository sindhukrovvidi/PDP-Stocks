package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import model.StocksImpl;
import view.StockView;

import static model.Input.takeStringInput;
import static model.Output.append;

/**
 * Class that contains all operations that are specific to the inflexible portfolio and extends the
 * abstract class StockControllerImpl.
 */
public class InflexibleStockControllerImpl extends StockControllerImpl {

  /**
   * Constructor that takes stocks model and view as parameters and initializes them.
   *
   * @param stocksImpl    model of stockImpl type.
   * @param stockViewImpl view of stockViewImpl type.
   */
  public InflexibleStockControllerImpl(StocksImpl stocksImpl, StockView stockViewImpl) {
    super(stocksImpl, stockViewImpl);
  }

  /**
   * Method used to obtain the stocks for a specific ticker value.
   *
   * @return data of the ticker value.
   * @throws IOException invalid data.
   */
  public StocksImpl getTickerValue() throws IOException {
    String tickerValue = takeStringInput("Enter the ticker value:\n");
    tickerValue = tickerValue.toUpperCase();
    updateListOfStocks(tickerValue);
    HashMap map = getStockList().getLStocksMap();
    ArrayList values = (ArrayList) map.get(tickerValue);
    if (values == null) {
      append("You entered an invalid ticker symbol. Please try again\n");
      return null;
    } else {
      StocksImpl currentStock = (StocksImpl) values.get(0);
      model.setCurrentStock(tickerValue, currentStock.getDate(), currentStock.getOpen(),
          currentStock.getHigh(), currentStock.getLow(), currentStock.getClose(),
          currentStock.getVolume(), 0, 0, 0, false);
      controllerToViewHelperForStocks(tickerValue, values);
      return afterStocksDisplay(model);
    }
  }

}
