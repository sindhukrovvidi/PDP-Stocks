package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class contains the methods to display the list of stocks.
 */
public class ListOfStocksImpl implements ListOfStocks {

  HashMap<String, ArrayList<StocksImpl>> listOfStocks;

  public ListOfStocksImpl() {
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

  /**
   * The method that is used to update the stocks lists.
   *
   * @param stocksList list of stocks in the file.
   * @param data       data of the company.
   * @param company    name of the company.
   */
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

