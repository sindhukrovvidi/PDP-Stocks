package model;

import java.util.HashMap;

public class Portfolio {

  private int sharesInvested;
  private float priceOnThatDay;
  private String dateInvested;

  private float totalAmountInvested;

  private String compnay;

  private HashMap<String, Portfolio> portfolioData = new HashMap<String, Portfolio>();

  public Portfolio(String company, int sharesInvested, float priceOnThatDay,
      String dateInvested) {
    this.compnay = company;
    this.sharesInvested = sharesInvested;
    this.priceOnThatDay = priceOnThatDay;
    this.dateInvested = dateInvested;
    this.totalAmountInvested = priceOnThatDay * sharesInvested;
//    portfolioData.put(compnay, this);
  }

  public Portfolio() {
    this.compnay = "";
    this.sharesInvested = 0;
    this.priceOnThatDay = 0;
    this.dateInvested = "";
    this.totalAmountInvested = priceOnThatDay * sharesInvested;
//    portfolioData.put(compnay, this);
  }

  public void saveStocksData(String company, int sharesInvested, float priceOnThatDay,
      String dateInvested) {
    Portfolio currentStock = new Portfolio(company, sharesInvested, priceOnThatDay, dateInvested);
//    this.compnay = company;
//    this.sharesInvested = sharesInvested;
//    this.priceOnThatDay = priceOnThatDay;
//    this.dateInvested = dateInvested;
//    this.totalAmountInvested = priceOnThatDay * sharesInvested;
    portfolioData.put(company, currentStock);
  }

  public HashMap getPortfolioData() {
    return portfolioData;
  }

  public String getCompnay() {
    return compnay;
  }

  public int getSharesInvested() {
    return sharesInvested;
  }

  public float getPriceOnThatDay() {
    return priceOnThatDay;
  }

  public String getDateInvested() {
    return dateInvested;
  }

  public float getTotalAmountInvested() {
    return totalAmountInvested;
  }
  //  protected void saveStocksData() {
//    portfolioData.put(compnay, this);
//  }
  // should store compnay name, no.of shares, amount when invested, current value

}