package model;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
public interface Portfolio {

  /**
   *
   * @param data
   */
  void addStockInPortfolio(StocksImpl data);

  /**
   *
   * @return
   */
  HashMap getPortfolio();

  /**
   *
   * @param portfolio
   */
  void setPortfolio(HashMap<String, StocksImpl> portfolio);

  /**
   *
   * @return
   */
  ArrayList<StocksImpl> getCompanyNames();

  /**
   *
   * @return
   */
  boolean isSaved();

  /**
   *
   */
  void save();

  /**
   *
   * @param name
   * @throws FileAlreadyExistsException
   */
  void setPortfolioName(String name) throws FileAlreadyExistsException;

}
