package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class StocksList implements ListOfStocks {
  HashMap<String, ArrayList<Stocks>> listOfStocks = new HashMap<>();

  public StocksList(BufferedReader stocksData) throws IOException {
    String line = stocksData.readLine(); // Reading header, Ignoring;
    while ((line = stocksData.readLine()) != null && !line.isEmpty()) {
      String[] fields = line.split(",");
      String name = fields[0];
      Stocks newStock = new Stocks(
          fields[1],
          Float.parseFloat(fields[2]),
          Float.parseFloat(fields[3]),
          Float.parseFloat(fields[4]),
          Float.parseFloat(fields[5]),
          Float.parseFloat(fields[6])
      );
      if (listOfStocks.containsKey(name)) {
        ArrayList<Stocks> currentValues = listOfStocks.get(name);
        currentValues.add(newStock);
        listOfStocks.put(name, currentValues);
      } else {
        ArrayList<Stocks> stocks = new ArrayList<Stocks>();
        stocks.add(newStock);
        listOfStocks.put(name, stocks);
      }
    }
    stocksData.close();
  }

  @Override
  public void updateMap() {
    System.out.println("Inside updateHashMap");
  }

  @Override
  public HashMap getMap() {
    return listOfStocks;
  }

}

