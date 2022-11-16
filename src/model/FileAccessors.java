package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Interface that contains all the methods to control the file accessing operations.
 */
public interface FileAccessors {

  /**
   * Method used to read a CSV file.
   *
   * @param file to be read.
   * @return reads the content of the file into a buffer reader.
   * @throws IOException invalid file name or if the file does not exist.
   */
  BufferedReader readCSV(String file) throws IOException;

  /**
   * Method used to write all the information into a CSV file.
   *
   * @param fileName name of the file to be written into.
   * @param hashMap  content to be written into the file.
   */
  void writeIntoCSVFile(String fileName, HashMap<String, TreeMap<Date, StocksImpl>> hashMap,
                        String path);

  /**
   * Method used to check if the file exists or not.
   *
   * @param name name of the file to check.
   * @return true if the file exists else false.
   */
  boolean isFileExists(String name, String path);

  /**
   * Method used to view or display a file.
   *
   * @param portfolioName name of the portfolio.
   * @return content present in the portfolio in the form of a map.
   * @throws IOException invalid file name.
   */
//  HashMap<String, StocksImpl> viewFile(String portfolioName, String path) throws IOException;

  HashMap<String, TreeMap<Date, StocksImpl>> viewFile(String portfolioName, String path)
          throws IOException, ParseException;

  /**
   * Method used to display the list of portfolios present in the directory.
   *
   * @param directory name of the directory for which we need the list of portfolios.
   * @return list of portfolios present in the directory.
   */
  String[] listOfPortfolioFiles(String directory);
}
