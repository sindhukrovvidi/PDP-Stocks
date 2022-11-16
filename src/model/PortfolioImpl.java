package model;

import java.nio.file.FileAlreadyExistsException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Class that implements the portfolio interface and is responsible for portfolio actions.
 */
public class PortfolioImpl implements Portfolio{
// TODO add a new function to handle selling the stocks.
  // TODO incorporate broker fees.

//  HashMap<String, StocksImpl> entriesInPortfolio = new HashMap<>();

  HashMap<String, TreeMap<Date, StocksImpl>> entriesInPortfolio = new HashMap<>();

  boolean saved = false;
  private String portfolioName;
  boolean isFlexible;

  private final FileAccessorsImpl fileAccessor = new FileAccessorsImpl();

  /**
   * Method used to add stock to the portfolio.
   *
   * @param data data to be added.
   */
//  @Override
//  public void addStockInPortfolio(StocksImpl data) {
//    String company = data.getCompany();
//    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
//    TreeMap<Date, StocksImpl> currStockData = new TreeMap<>();
//    Date newDate = sdformat.parse(data.getDate());
////    Date newDate = data.getDate();
//    if (entriesInPortfolio.containsKey(company)) {
//      currStockData = entriesInPortfolio.get(company);
//
//      if (currStockData.containsKey(newDate)) {
//        StocksImpl currStock = (entriesInPortfolio.get(company)).get(newDate);
//        data.updateStockValues(currStock.getShares() + data.getShares());
//        currStockData.put(newDate, data);
//      } else {
//        (entriesInPortfolio.get(company)).put(newDate, data);
//      }
//    } else {
//      currStockData.put(newDate, data);
//      entriesInPortfolio.put(data.getCompany(), currStockData);
//    }
//  }

  @Override
  public void addStockInPortfolio(StocksImpl data) throws ParseException {
    String company = data.getCompany();
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
    TreeMap<Date, StocksImpl> currStockData = new TreeMap<>();
    Date newDate = sdformat.parse(data.getDate());

    if (entriesInPortfolio.containsKey(company)) {
      currStockData = entriesInPortfolio.get(company);

      if (currStockData.containsKey(newDate)) {
        StocksImpl currStock = (entriesInPortfolio.get(company)).get(newDate);
        data.updateStockValues(currStock.getShares() + data.getShares());
        currStockData.put(newDate, data);
      } else {
        (entriesInPortfolio.get(company)).put(newDate, data);
      }
    } else {
      currStockData.put(newDate, data);
      entriesInPortfolio.put(data.getCompany(), currStockData);
    }
  }

  /**
   * Method used to get portfolio.
   *
   * @return portfolio.
   */
  @Override
  public HashMap<String, TreeMap<Date, StocksImpl>> getPortfolio() {
    return entriesInPortfolio;
  }

  /**
   * Method to create a portfolio.
   *
   * @param portfolio mapping of the portfolio.
   */
  @Override
  public void setPortfolio(HashMap<String, TreeMap<Date, StocksImpl>> portfolio) {
    this.entriesInPortfolio = portfolio;

  }

  /**
   * Method used to get company name.
   *
   * @return the list of company names.
   */
  @Override
  public ArrayList<TreeMap<Date, StocksImpl>> getCompanyNames() {
    return (ArrayList<TreeMap<Date, StocksImpl>>) entriesInPortfolio.values();
//    return new ArrayList<StocksImpl>(entriesInPortfolio.values());
  }

  /**
   * Method used to check if file is saved or not.
   *
   * @return checks whether the file is saved or not.
   */
  @Override
  public boolean isSaved() {
    return saved;
  }

  /**
   * Readers and saves the portfolio in file.
   */
  @Override
  public void save(String path) {
    fileAccessor.writeIntoCSVFile(portfolioName, entriesInPortfolio, path);
    this.saved = true;
  }

  /**
   * Method used to check if the file is already created.
   *
   * @param name sets the name of the portfolio.
   * @throws FileAlreadyExistsException file doesn't exist.
   */
  @Override
  public void setPortfolioName(String name) throws FileAlreadyExistsException {
    portfolioName = name;
  }

  @Override
  public void setIsFlexible(boolean isFlexible) {
    this.isFlexible = isFlexible;
  }

  @Override
  public boolean getIsFlexible() {
    return isFlexible;
  }

}
