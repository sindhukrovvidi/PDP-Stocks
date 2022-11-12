package model;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that implements the portfolio interface and is responsible for portfolio actions.
 */
public class PortfolioImpl implements Portfolio {
// TODO add a new function to handle selling the stocks.
  // TODO incorporate broker fees.

  HashMap<String, StocksImpl> entriesInPortfolio = new HashMap<>();
  boolean saved = false;
  private String portfolioName;

  private final FileAccessorsImpl fileAccessor = new FileAccessorsImpl();

  /**
   * Method used to add stock to the portfolio.
   *
   * @param data data to be added.
   */
  @Override
  public void addStockInPortfolio(StocksImpl data) {
    String company = data.getCompany();
    if (entriesInPortfolio.containsKey(company)) {
      StocksImpl currStock = entriesInPortfolio.get(company);
      // TODO handle cases when isFlexible is true
      data.updateStockValues(currStock.getShares() + data.getShares());
      entriesInPortfolio.put(data.getCompany(), data);
    }
    entriesInPortfolio.put(data.getCompany(), data);
  }

  /**
   * Method used to get portfolio.
   *
   * @return portfolio.
   */
  @Override
  public HashMap getPortfolio() {
    return entriesInPortfolio;
  }

  /**
   * Method to create a portfolio.
   *
   * @param portfolio mapping of the portfolio.
   */
  @Override
  public void setPortfolio(HashMap<String, StocksImpl> portfolio) {
    this.entriesInPortfolio = portfolio;

  }

  /**
   * Method used to get company name.
   *
   * @return the list of company names.
   */
  @Override
  public ArrayList<StocksImpl> getCompanyNames() {
    return new ArrayList<StocksImpl>(entriesInPortfolio.values());
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
  public void save() {
    fileAccessor.writeIntoCSVFile(portfolioName, entriesInPortfolio);
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
    if (!fileAccessor.isFileExists(name)) {
      portfolioName = name;
    } else {
      throw new FileAlreadyExistsException(name);
    }
  }

}
