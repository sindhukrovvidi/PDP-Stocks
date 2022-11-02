package model;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
public class PortfolioImpl implements Portfolio {

  HashMap<String, StocksImpl> entriesInPortfolio = new HashMap<>();
  boolean saved = false;
  private String portfolioName;

  private final FileAccessorsImpl fileAccessor = new FileAccessorsImpl();

  /**
   * @param data
   */
  @Override
  public void addStockInPortfolio(StocksImpl data) {
    String company = data.getCompany();
    if (entriesInPortfolio.containsKey(company)) {
      StocksImpl currStock = entriesInPortfolio.get(company);
      data.updateStockValues(currStock.getShares() + data.getShares());
      entriesInPortfolio.put(data.getCompany(), data);
    }
    entriesInPortfolio.put(data.getCompany(), data);
  }

  /**
   * @return
   */
  @Override
  public HashMap getPortfolio() {
    return entriesInPortfolio;
  }

  /**
   * @param portfolio
   */
  @Override
  public void setPortfolio(HashMap<String, StocksImpl> portfolio) {
    this.entriesInPortfolio = portfolio;

  }

  /**
   * @return
   */
  @Override
  public ArrayList<StocksImpl> getCompanyNames() {
    return new ArrayList<StocksImpl>(entriesInPortfolio.values());
  }

  /**
   * @return
   */
  @Override
  public boolean isSaved() {
    return saved;
  }

  /**
   *
   */
  @Override
  public void save() {
    fileAccessor.writeIntoCSVFile(portfolioName, entriesInPortfolio);
    this.saved = true;
  }

  /**
   * @param name
   * @throws FileAlreadyExistsException
   */
  @Override
  public void setPortfolioName(String name) throws FileAlreadyExistsException {
    if (!fileAccessor.isFileExists(name)) {
      portfolioName = name;
    } else {
      throw new FileAlreadyExistsException(name);
    }
  }

}
