package model;

import java.util.HashMap;

/**
 * Interface to maintain the list of stocks.
 */
public interface ListOfStocks {

  /**
   * Method used to fetch the list of stocks.
   *
   * @return the list of stocks when called with the particular model.
   */
  HashMap getLStocksMap();
}
