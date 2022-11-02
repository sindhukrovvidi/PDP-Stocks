package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 */
public interface FileAccessors {

  /**
   *
   * @param file
   * @return
   * @throws IOException
   */
  BufferedReader readCSV(String file) throws IOException;

  /**
   *
   * @param fileName
   * @param hashMap
   */
  void writeIntoCSVFile(String fileName, HashMap<String, StocksImpl> hashMap);

  /**
   *
   * @param name
   * @return
   */
  boolean isFileExists(String name);

  HashMap<String, StocksImpl> viewFile(String portfolioName) throws IOException;

  String[] listOfPortfolioFiles(String directory);
}
