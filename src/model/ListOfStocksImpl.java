package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
public class ListOfStocksImpl implements ListOfStocks {

  HashMap<String, ArrayList<StocksImpl>> listOfStocks = new HashMap<>();

  /**
   * @param stocksData
   * @throws IOException
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
   * @return
   */
  @Override
  public HashMap getLStocksMap() {
    return listOfStocks;
  }

}

