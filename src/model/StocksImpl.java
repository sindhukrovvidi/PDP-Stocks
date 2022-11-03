package model;

/**
 * Class that contains all the operations required for retrieving data of a stock and implements
 * the methods of stocks interface.
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
   * Constructor that initializes the company, open,close,high,low,date,shares and the volume.
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

  /**
   * Constructor that takes date,open,high,low,close,volume as parameters and initializes them.
   *
   * @param date   the date.
   * @param open   opening price.
   * @param high   highest price.
   * @param low    lowest price.
   * @param close  closing price.
   * @param volume volume of shares of the company.
   */
  public StocksImpl(String date, float open, float high, float low, float close, float volume) {
    this.date = date;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
  }

  /**
   * Constructor that takes date,open,high,low,close,volume and shares as parameters and
   * initializes them.
   *
   * @param date   the date.
   * @param open   opening price.
   * @param high   highest price.
   * @param low    lowest price.
   * @param close  closing price.
   * @param volume volume of shares of the company.
   * @param shares number of shares.
   */
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

  /**
   * Method used to set the values of a current stock.
   *
   * @param company name of the company.
   * @param date    date on which the data is retrieved.
   * @param open    price at open.
   * @param high    highest price of the day.
   * @param low     lowest price of the day.
   * @param close   closing price of the day.
   * @param volume  amount of stocks available for that company.
   * @param shares  number of share purchased.
   */
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

  /**
   * Method used to update the quantity of the stocks.
   *
   * @param values value to be updated.
   */
  @Override
  public void updateStockValues(int values) {
    this.shares = values;
  }

  /**
   * Method used to get date.
   *
   * @return date in string format.
   */
  @Override
  public String getDate() {
    return this.date;
  }

  /**
   * Method used to get the opening price.
   *
   * @return opening price.
   */
  @Override
  public float getOpen() {
    return this.open;
  }

  /**
   * Method used to get the company name.
   *
   * @return name of the company.
   */
  @Override
  public String getCompany() {
    return this.company;
  }

  /**
   * Method used to get the high price of the day.
   *
   * @return highest price of the day.
   */
  @Override

  public float getHigh() {
    return this.high;
  }

  /**
   * Method used to get the lowest price of the day.
   *
   * @return lowest price of the day.
   */
  @Override
  public float getLow() {
    return this.low;
  }

  /**
   * Method used to get the closing price of the day.
   *
   * @return closing price of the day.
   */
  @Override
  public float getClose() {
    return this.close;
  }

  /**
   * Method used to get the volume of stocks of a company.
   *
   * @return number of available stocks.
   */
  @Override
  public float getVolume() {
    return this.volume;
  }

  /**
   * Method used to get the number of shares purchased.
   *
   * @return number of shares.
   */
  @Override
  public int getShares() {
    return this.shares;
  }

}