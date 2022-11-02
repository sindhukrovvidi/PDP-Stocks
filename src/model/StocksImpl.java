package model;

/**
 *
 */
public class StocksImpl implements Stocks {

  private int shares;

  private float open;
  private float close;
  private float high;
  private float low;
  private String date;
  private String company;

  private float volume;

  /**
   *
   */
  public StocksImpl() {
    this.company = "";
    this.open = 0;
    this.close = 0;
    this.high = 0;
    this.low = 0;
    this.date = "";
    this.shares = 0;
    this.volume = 0;

  }

  public StocksImpl(String date, float open, float high, float low, float close, float volume) {
    this.date = date;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
  }

  public StocksImpl(String date, float open, float high, float low, float close, float volume,
      int shares) {
    this.date = date;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
    this.shares = shares;
  }

  @Override
  public void setCurrentStock(String company, String date, float open, float high, float low,
      float close, float volume,
      int shares) {
    this.company = company;
    this.date = date;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
    this.shares = shares;
  }

  @Override
  public void updateStockValues(int values) {
    this.shares = values;
  }

  @Override
  public String getDate() {
    return this.date;
  }

  @Override
  public float getOpen() {
    return this.open;
  }

  @Override
  public String getCompany() {
    return this.company;
  }

  @Override
  public float getHigh() {
    return this.high;
  }

  @Override
  public float getLow() {
    return this.low;
  }

  @Override
  public float getClose() {
    return this.close;
  }

  @Override
  public float getVolume() {
    return this.volume;
  }

  @Override
  public int getShares() {
    return this.shares;
  }

}