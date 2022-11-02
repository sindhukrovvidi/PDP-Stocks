package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 */
public class FileAccessorsImpl implements FileAccessors {

  /**
   *
   * @param file
   * @return
   * @throws IOException
   */
  @Override
  public BufferedReader readCSV(String file) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    StringBuilder output = new StringBuilder();
    return reader;
  }

  /**
   *
   * @param filename
   * @param entriesInPortfolio
   */
  @Override
  public void writeIntoCSVFile(String filename, HashMap<String, StocksImpl> entriesInPortfolio) {
    try {
      FileWriter outputFile = new FileWriter("portfolios/" + filename + ".csv");
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
   *
   * @param filename
   * @return
   */
  @Override
  public boolean isFileExists(String filename) {
    File f = new File("portfolios/" + filename + ".csv");
    return f.exists() && !f.isDirectory();
  }

  /**
   *
   * @param portfolioName
   * @return
   * @throws IOException
   */
  @Override
  public HashMap<String, StocksImpl> viewFile(String portfolioName) throws IOException {
    HashMap<String, StocksImpl> portfolio = new HashMap<>();
    String line;
    BufferedReader reader = readCSV("portfolios/" + portfolioName + ".csv");
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

  @Override
  public String[] listOfPortfolioFiles(String directory) {
    String files[] = {};
    File directoryPath = new File(directory);
    files = directoryPath.list();
    return files;
  }
}
