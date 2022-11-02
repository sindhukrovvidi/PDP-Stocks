package model;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.HashMap;

public class Portfolio implements PortfolioInterface {

  HashMap<String, Stocks> entriesInPortfolio = new HashMap<>();
  boolean saved = false;
  private String portfolioName;

  private final FileAccessors fileAccesor = new FileAccessors();

  @Override
  public void addStockInPortfolio(Stocks data) {
    entriesInPortfolio.put(data.getCompany(), data);
  }

  @Override
  public HashMap getPortfolio() {
    return entriesInPortfolio;
  }

  public boolean isSaved() {
    return saved;
  }

  public void save() {
    fileAccesor.writeIntoCSVFile(portfolioName, entriesInPortfolio);
    this.saved = true;
  }

  public void setPortfolioName(String name) throws FileAlreadyExistsException {
    if (!fileAccesor.isFileExists(name)) {
      portfolioName = name;
    } else {
     throw new FileAlreadyExistsException(name);
    }
  }

  public String getPortfolioName() {
    return portfolioName;
  }

}
