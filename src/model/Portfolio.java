package model;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.HashMap;

public class Portfolio implements PortfolioInterface {

  HashMap<String, Stocks> entriesInPortfolio = new HashMap<>();
  boolean saved = false;
  private String portfolioName;

  private final FileAccessors fileAccessor = new FileAccessors();

  @Override
  public void addStockInPortfolio(Stocks data) {
    String company = data.getCompany();
    if (entriesInPortfolio.containsKey(company)) {
      Stocks currStock = entriesInPortfolio.get(company);
      data.updateStockValues(currStock.getShares() + data.getShares());
      entriesInPortfolio.put(data.getCompany(), data);
    }
    entriesInPortfolio.put(data.getCompany(), data);
  }

  @Override
  public HashMap getPortfolio() {
    return entriesInPortfolio;
  }

  public void setPortfolio(HashMap<String, Stocks> portfolio) {
    this.entriesInPortfolio = portfolio;

  }
  public ArrayList<Stocks> getCompanyNames(){
    return new ArrayList<Stocks>(entriesInPortfolio.values());
  }
  public boolean isSaved() {
    return saved;
  }

  public void save() {
    fileAccessor.writeIntoCSVFile(portfolioName, entriesInPortfolio);
    this.saved = true;
  }

  public void setPortfolioName(String name) throws FileAlreadyExistsException {
    if (!fileAccessor.isFileExists(name)) {
      portfolioName = name;
    } else {
      throw new FileAlreadyExistsException(name);
    }
  }

  public String getPortfolioName() {
    return portfolioName;
  }

  public boolean isCompanyPresent(String tickerSymbol) {
    return entriesInPortfolio.containsKey(tickerSymbol);
  }
}
