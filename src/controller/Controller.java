package controller;

import java.io.IOException;
import java.util.HashMap;

import model.HTTPRequests;
import model.HTTPRequestsImpl;
import model.ListOfStocksImpl;

public abstract class Controller {
  private ListOfStocksImpl listOfStocksImpl;

  /**
   * Method used to set the list of stocks to a single list.
   *
   * @param list to be set to a stock list.
   */
  public void setStocksList(ListOfStocksImpl list) {
    listOfStocksImpl = list;
  }

  /**
   * @return
   */
  public ListOfStocksImpl getStockList() {
    return listOfStocksImpl;
  }

  protected void updateListOfStocks(String tickerValue) throws IOException {
    if (listOfStocksImpl == null) {
      listOfStocksImpl = new ListOfStocksImpl();
    }
    HashMap map = listOfStocksImpl.getLStocksMap();
    HTTPRequests requests = new HTTPRequestsImpl();
    StringBuilder currTickerData = requests.getData(tickerValue);
    listOfStocksImpl.updateStocksList(map,
            currTickerData, tickerValue);
    setStocksList(listOfStocksImpl);
  }

}
