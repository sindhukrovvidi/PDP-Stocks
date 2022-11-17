import model.Stocks;

public class MockStocksModel implements Stocks {

  private StringBuilder log;
  private int shares;

  private float open;
  private float close;
  private float high;
  private float low;
  private String date;
  private String company;

  private float volume;
  private float commisionFee;

  public MockStocksModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void setCurrentStock(String company, String date, float open, float high, float low,
      float close, float volume, int shares, float commissionFee) {
    this.company = company;
    this.date = date;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
    this.shares = shares;
    this.commisionFee = commissionFee;
  }

  @Override
  public void updateStockValues(int values) {
    log.append("Set current shares");
    this.shares = values;
  }

  @Override
  public String getDate() {
    return date;
  }

  @Override
  public float getOpen() {
    return open;
  }

  @Override
  public String getCompany() {
    return company;
  }

  @Override
  public float getHigh() {
    return high;
  }

  @Override
  public float getLow() {
    return low;
  }

  @Override
  public float getClose() {
    return close;
  }

  @Override
  public float getVolume() {
    return volume;
  }

  @Override
  public int getShares() {
    return shares;
  }
}
