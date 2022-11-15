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

import static model.Input.takeStringInput;
import static model.Output.append;

public class InflexibleStockControllerImpl extends StockControllerImpl{
  /**
   * Constructor that takes stocks model and view as parameters and initializes them.
   *
   * @param stocksImpl    model of stockImpl type.
   * @param stockViewImpl view of stockViewImpl type.
   */
  public InflexibleStockControllerImpl(StocksImpl stocksImpl, StockViewImpl stockViewImpl) {
    super(stocksImpl, stockViewImpl);
  }
  public StocksImpl getTickerValue() throws IOException {
    String tickerValue = takeStringInput("Enter the ticker value:\n");
    updateListOfStocks(tickerValue);
    HashMap map = getStockList().getLStocksMap();
    ArrayList values = (ArrayList) map.get(tickerValue);
    if (values == null) {
      append("You entered an invalid ticker symbol. Please try again\n");
//      getTickerValue();
      return null;
    } else {
        StocksImpl currentStock = (StocksImpl) values.get(0);
        model.setCurrentStock(tickerValue, currentStock.getDate(), currentStock.getOpen(),
                currentStock.getHigh(), currentStock.getLow(), currentStock.getClose(),
                currentStock.getVolume(), 0, 0);
        controllerToViewHelperForStocks(tickerValue, values);
      return afterStocksDisplay(model);
    }

//    return (values.size() > 0 ? afterStocksDisplay(model) : null);
  }

}
