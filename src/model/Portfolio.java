package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Portfolio implements PortfolioInterface{

  HashMap<String, Stocks> entriesInPortfolio = new HashMap<>();

  @Override
  public void addStockInPortfolio(Stocks data) {
    entriesInPortfolio.put(data.getCompany(), data);
  }

  @Override
  public HashMap getPortfolio() {
    return entriesInPortfolio;
  }
}
