package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Class that controls all the file actions and implements methods of the FileAccessors interface.
 */
public class FileAccessorsImpl implements FileAccessors {

  /**
   * Method used to read a CSV file.
   *
   * @param file to be read.
   * @return reads the content of the file into a buffer reader.
   * @throws IOException invalid file name or if the file does not exist.
   */
  @Override
  public BufferedReader readCSV(String file) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    StringBuilder output = new StringBuilder();
    return reader;
  }

  /**
   * Method used to write all the information into a CSV file.
   *
   * @param filename           name of the file to be written into.
   * @param entriesInPortfolio content to be written into the file.
   */
  @Override
  public void writeIntoCSVFile(String filename, HashMap<String, StocksImpl> entriesInPortfolio) {
    try {
      Path currentPath = Paths.get(System.getProperty("user.dir"));
      Path fp = Paths.get(currentPath.toString(), "portfolios");
//      System.out.println(fp.toString());
      File theDir = new File(fp.toString());
      if (!theDir.exists()) {
        theDir.mkdirs();
      }
      FileWriter outputFile = new FileWriter(fp.toString() + "/" + filename + ".csv");
      BufferedWriter bwr = new BufferedWriter(outputFile);
      entriesInPortfolio.forEach((k, v) -> {
        StocksImpl currentStock = (StocksImpl) v;
        try {
          bwr.write(currentStock.getCompany());
          bwr.write(",");
          bwr.write(currentStock.getDate());
          bwr.write(",");
          bwr.write(String.valueOf(currentStock.getOpen())); // opening price on that day
          bwr.write(",");
          bwr.write(String.valueOf(currentStock.getHigh())); // high value
          bwr.write(",");
          bwr.write(String.valueOf(currentStock.getLow())); // low value
          bwr.write(",");
          bwr.write(String.valueOf(currentStock.getClose())); // closing price on that day
          bwr.write(",");
          bwr.write(String.valueOf(currentStock.getVolume())); // no shares invested on that day
          bwr.write(",");
          bwr.write(String.valueOf(currentStock.getShares())); // no shares invested on that day
          bwr.write(",");
          bwr.write(String.valueOf(currentStock.getClose() * currentStock.getShares())); // total
          // price for that company
          bwr.write("\n");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      });
      bwr.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Method used to check if the file exists or not.
   *
   * @param filename name of the file to check.
   * @return true if the file exists else false.
   */
  @Override
  public boolean isFileExists(String filename) {
    Path currentPath = Paths.get(System.getProperty("user.dir"));
    Path fp = Paths.get(currentPath.toString(), "portfolios");
    File theDir = new File(fp.toString());
    if (!theDir.exists()) {
      theDir.mkdirs();
    }
    File f = new File(fp.toString()  + "/" + filename + ".csv");
    return f.exists() && !f.isDirectory();
  }

  /**
   * Method used to view or display a file.
   *
   * @param portfolioName name of the portfolio.
   * @return content present in the portfolio in the form of a map.
   * @throws IOException invalid file name.
   */
  @Override
  public HashMap<String, StocksImpl> viewFile(String portfolioName) throws IOException {
    HashMap<String, StocksImpl> portfolio = new HashMap<>();
    String line;
    Path currentPath = Paths.get(System.getProperty("user.dir"));
    Path fp = Paths.get(currentPath.toString(), "portfolios");
    File theDir = new File(fp.toString());
    if (!theDir.exists()) {
      theDir.mkdirs();
    }
    File f = new File(fp.toString()  + "/" + portfolioName + ".csv");

    BufferedReader reader = readCSV(f.getPath());
    while ((line = reader.readLine()) != null && !line.isEmpty()) {
      String[] fields = line.split(",");

      StocksImpl stocksImpl = new StocksImpl();
      stocksImpl.setCurrentStock(
              fields[0],
              fields[1],
              Float.parseFloat(fields[2]),
              Float.parseFloat(fields[3]),
              Float.parseFloat(fields[4]),
              Float.parseFloat(fields[5]),
              Float.parseFloat(fields[6]),
              Integer.parseInt(fields[7])
      );
      portfolio.put(fields[0], stocksImpl);
    }
    reader.close();
    return portfolio;
  }

  /**
   * Method used to display the list of portfolios present in the directory.
   *
   * @param directory name of the directory for which we need the list of portfolios.
   * @return list of portfolios present in the directory.
   */
  @Override
  public String[] listOfPortfolioFiles(String directory) {
    String files[] = {};
    Path currentPath = Paths.get(System.getProperty("user.dir"));
    Path fp = Paths.get(currentPath.toString(), "portfolios");
    File theDir = new File(fp.toString());
    if (!theDir.exists()) {
      theDir.mkdirs();
    }
    File directoryPath = new File(fp.toString());
    files = directoryPath.list();
    return files;
  }
}
