package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class contains the methods to display the list of stocks.
 */
public class ListOfStocksImpl implements ListOfStocks {

  HashMap<String, ArrayList<StocksImpl>> listOfStocks;

  public ListOfStocksImpl() throws IOException {
    listOfStocks = new HashMap<>();
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

  public void updateStocksList(HashMap stocksList,
      StringBuilder data,
      String company) {
    if (!stocksList.containsKey(company)) {
      String line; // Reading header, Ignoring;
      String[] entries = data.toString().split("\r\n");
      for (int i = 1; i < entries.length; i++) {

        String[] fields = entries[i].split(",");
        StocksImpl newStock = new StocksImpl(
            fields[0],
            Float.parseFloat(fields[1]),
            Float.parseFloat(fields[2]),
            Float.parseFloat(fields[3]),
            Float.parseFloat(fields[4]),
            Float.parseFloat(fields[5])
        );
        if (listOfStocks.containsKey(company)) {
          ArrayList<StocksImpl> currentValues = listOfStocks.get(company);
          currentValues.add(newStock);
          listOfStocks.put(company, currentValues);
        } else {
          ArrayList<StocksImpl> stocks = new ArrayList<StocksImpl>();
          stocks.add(newStock);
          listOfStocks.put(company, stocks);
        }
      }

    }
  }

}

