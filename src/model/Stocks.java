package model;
public class Stocks {

  private int shares;

  private float open;
  private float price;
  private float high;
  private float low;
  private String latestDay;
  private float previousClose;
  private String company;
//  private DATE date;

//  public Stocks(String company, int sharesInvested, float priceOnThatDay,
//      String dateInvested) {
//    this.company = company;
//    this.shares = sharesInvested;
//    this.priceOnThatDay = priceOnThatDay;
//    this.dateInvested = dateInvested;
//    this.totalAmountInvested = priceOnThatDay * sharesInvested;
////    portfolioData.put(compnay, this);
//  }
  public void setStocks(int share) {
    shares = share;
  }

  public void setSharesValue(int shares) {
    this.shares = shares;
  }

  public int getSharesValue() {
    return shares;
  }

  public int getStocks() {
    return this.shares;
  }

  public void setCompany(String companyName) {
    this.company = companyName;
  }

  public String getCompany() {
    return company;
  }

  public void setOpenValue(float value) {
    this.open = value;
  }

  public float getOpenValue() {
    return open;
  }

  public void setHighValue(float value) {
    this.high = value;
  }

  public float getHighValue() {
    return high;
  }

  public void setLowValue(float value) {
    this.low = value;
  }

  public float getLowValue() {
    return low;
  }

  public void setPriceValue(float value) {
    this.price = value;
  }

  public float getPriceValue() {
    return price;
  }

  public void setLatestDay(String latestDay) {
    this.latestDay = latestDay;
  }

  public String getLatestDay() {
    return latestDay;
  }

  public void setPreviousClose(float value) {
    this.previousClose = value;
  }

  public float getPreviousClose() {
    return previousClose;
  }

}
