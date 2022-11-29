package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;
import model.StocksImpl;

public interface Features {

  boolean createPortfolio(String filename) throws IOException;
  boolean validateSingleStocksData(String ticker, String date, int stocks, float fee)
      throws IOException;

  void addStockToPortfolio(String tickerName, String date, int stocks, float fee)
      throws ParseException;

  void saveCurrentPortfolio();

  HashMap<String, TreeMap<Date, StocksImpl>> getPortfolio();

  boolean isValidateInputForMultiStocks(String stocksInput, float investedAmount, String weightage
      , float fee,
      String lowerDate, String upperDate, int frequency) throws ParseException, IOException;

  void addDollarCostAveragingStocks(String lowerDate, String upperDate, int frequency,
      String tickerValuesList, float valueInvested, String weightage, float fee)
      throws ParseException;

  String[] getPortfolioNames();

  HashMap<String, TreeMap<Date, StocksImpl>> renderTheSelectedPortfolio(String fileName)
      throws IOException, ParseException;

//  void addKeyListeners();

  HashMap getCompositionOfThePortfolio(String fileName, String date, boolean isCostBasis) throws ParseException, IOException;

//  HashMap<String, TreeMap<Date, StocksImpl>> updateTheCurrentPortfolio(String filename) throws IOException, ParseException;

}
