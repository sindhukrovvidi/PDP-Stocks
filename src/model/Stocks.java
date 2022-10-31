package model;

public class Stocks {

  private int shares;

  private float open;
  private float close;
  private float high;
  private float low;
  private String date;
  private String company;

  private float volume;


  public Stocks() {
    this.company = "";
    this.open = 0;
    this.close = 0;
    this.high = 0;
    this.low = 0;
    this.date = "";
    this.shares = 0;
    this.volume = 0;

  }

  public Stocks(String date, float open, float high, float low, float close, float volume) {
    this.date = date;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
  }

  public Stocks(String date, float open, float high, float low, float close, float volume,
      int shares) {
    this.date = date;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
    this.shares = shares;
  }

  public String getDate() {
    return this.date;
  }

  public float getOpen() {
    return this.open;
  }

  public float getHigh() {
    return this.high;
  }

  public float getLow() {
    return this.low;
  }

  public float getClose() {
    return this.close;
  }

  public float getVolume() {
    return this.volume;
  }

  public int getShares() {
    return this.shares;
  }

}
