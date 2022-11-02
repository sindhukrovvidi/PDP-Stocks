package controller;

import java.io.IOException;
import java.util.ArrayList;
import model.ListOfStocksImpl;
import model.StocksImpl;

public interface StockController {

  StocksImpl afterStocksDisplay(Object model) throws IOException;

  void setStocksList(ListOfStocksImpl list);

  StocksImpl getTickerValue() throws IOException;

  StocksImpl addStockToPortfolio(Object models) throws IOException;

  void controllerToViewHelperForStocks(String companyName,
      ArrayList<StocksImpl> values);

}
