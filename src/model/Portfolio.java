package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Portfolio implements PortfolioInterface{

  HashMap<String, Stocks> entriesInPortfolio = new HashMap<>();
  boolean saved = false;

  @Override
  public void addStockInPortfolio(Stocks data) {
    entriesInPortfolio.put(data.getCompany(), data);
  }

  @Override
  public HashMap getPortfolio() {
    return entriesInPortfolio;
  }
  public boolean isSaved(){
    return saved;
  }
  public void save(){
    this.saved = true;
  }
}
