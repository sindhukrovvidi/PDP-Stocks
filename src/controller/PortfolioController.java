package controller;

import java.util.HashMap;
import model.ListOfStocksImpl;
import model.PortfolioImpl;
import java.io.IOException;
import model.StocksImpl;

public interface PortfolioController {

  PortfolioImpl addStock() throws IOException;;

  PortfolioImpl afterAddingStock(PortfolioImpl model) throws IOException;

  void viewSpeculate(String input, ListOfStocksImpl listOfStocksImpl) throws IOException;

  boolean viewSpeculateHelper(String fileName, ListOfStocksImpl listOfStocksImpl) throws IOException;

  void controllerToViewHelper(HashMap<String, StocksImpl> portfolioEntries);

}
