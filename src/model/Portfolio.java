package model;

import java.nio.file.FileAlreadyExistsException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Interface that contains methods to get and set the values in portfolio.
 */
public interface Portfolio {

  /**
   * Method used to add stock to the portfolio.
   *
   * @param data data to be added.
   */
  void addStockInPortfolio(StocksImpl data) throws ParseException;

  /**
   * Method used to get portfolio.
   *
   * @return portfolio.
   */
  HashMap<String, TreeMap<Date, StocksImpl>> getPortfolio();

  /**
   * Method to create a portfolio.
   *
   * @param portfolio mapping of the portfolio.
   */
  void setPortfolio(HashMap<String, TreeMap<Date, StocksImpl>> portfolio);

  /**
   * Method used to get company name.
   *
   * @return array list of stocks for a company.
   */
//  ArrayList<TreeMap<Date, StocksImpl>> getCompanyNames();

  TreeMap getCompanyNames();
  /**
   * Method used to check if file is saved or not.
   *
   * @return true if saved else false.
   */
  boolean isSaved();

  /**
   * Method used to save a file.
   */
  void save(String path);

//  void saveInflexible();

  /**
   * Method used to check if the file is already created.
   *
   * @param name name of the file.
   * @throws FileAlreadyExistsException if the file already exists.
   */
  void setPortfolioName(String name) throws FileAlreadyExistsException;

  /**
   * @param isFlexible
   */
  void setIsFlexible(boolean isFlexible);

  /**
   * @return
   */
  boolean getIsFlexible();

  /**
   *
   * @param buy
   */
  void setBuy(boolean buy);

  /**
   *
   * @return
   */
  boolean getBuy();

  void setIsCostBasis(boolean isCostBasis);

  boolean getIsCostBasis();

}
