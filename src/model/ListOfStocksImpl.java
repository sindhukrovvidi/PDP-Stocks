package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class contains the methods to display the list of stocks.
 */
public class ListOfStocksImpl implements ListOfStocks {

  HashMap<String, ArrayList<StocksImpl>> listOfStocks = new HashMap<>();

  /**
   * Method used to fetch the list of stocks.
   *
   * @param stocksData data.
   * @throws IOException invalid data.
   */
  public ListOfStocksImpl(BufferedReader stocksData) throws IOException {
    String line = stocksData.readLine(); // Reading header, Ignoring;
    while ((line = stocksData.readLine()) != null && !line.isEmpty()) {
      String[] fields = line.split(",");
      String name = fields[0];
      StocksImpl newStock = new StocksImpl(
          fields[1],
          Float.parseFloat(fields[2]),
          Float.parseFloat(fields[3]),
          Float.parseFloat(fields[4]),
          Float.parseFloat(fields[5]),
          Float.parseFloat(fields[6])
      );
      if (listOfStocks.containsKey(name)) {
        ArrayList<StocksImpl> currentValues = listOfStocks.get(name);
        currentValues.add(newStock);
        listOfStocks.put(name, currentValues);
      } else {
        ArrayList<StocksImpl> stocks = new ArrayList<StocksImpl>();
        stocks.add(newStock);
        listOfStocks.put(name, stocks);
      }
    }
    stocksData.close();
  }

  /**
   * Method used to fetch the list of stocks.
   *
   * @return the list of stocks when called with the particular model.
   */
  @Override
  public HashMap getLStocksMap() {
    return listOfStocks;
  }

}

