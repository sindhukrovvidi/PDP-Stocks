package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 * Class that contains all the operations required for retrieving data of a stock and implements the
 * methods of stocks interface.
 */
public class StocksImpl implements Stocks {

  private float shares;

  private float open;
  private float close;
  private float high;
  private float low;
  private String date;
  private String company;

  private float volume;
  private float commisionFee;

  private float percentage;

  private boolean isFuture;

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
    this.commisionFee = 0;
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
   * Constructor that takes date,open,high,low,close,volume and shares as parameters and initializes
   * them.
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
                    float shares) {
    this.date = date;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
    this.shares = shares;
  }

  /**
   * Constructor that takes date,open,high,low,close,volume and shares as parameters and initializes
   * them.
   *
   * @param date   the date.
   * @param open   opening price.
   * @param high   highest price.
   * @param low    lowest price.
   * @param close  closing price.
   * @param volume volume of shares of the company.
   * @param shares number of shares.
   */
  public StocksImpl(String company, String date, float open, float high, float low, float close,
                    float volume,
                    float shares, float fee) {
    this.company = company;
    this.date = date;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
    this.shares = shares;
    this.commisionFee = fee;
  }

  /**
   * Constructor that takes name,date,open,high,low,close,volume and shares,fees,percentage,isFuture
   * as parameters and initializes them.
   *
   * @param name       name of the portfolio.
   * @param date       the date.
   * @param open       opening price.
   * @param high       highest price.
   * @param low        lowest price.
   * @param close      closing price.
   * @param volume     volume on that day.
   * @param shares     number of shares purchased.
   * @param fee        the commission fee.
   * @param percentage percentage that is invested.
   * @param isFuture   future date.
   */
  public StocksImpl(String name, String date, float open, float high, float low,
                    float close, float volume,
                    float shares, float fee, float percentage, boolean isFuture) {
    this.company = name;
    this.date = date;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
    this.shares = shares;
    this.commisionFee = fee;
    this.percentage = percentage;
    this.isFuture = isFuture;
  }

  /**
   * Method used to set the values of a current stock.
   *
   * @param name   name of the company.
   * @param date   date on which the data is retrieved.
   * @param open   price at open.
   * @param high   highest price of the day.
   * @param low    lowest price of the day.
   * @param close  closing price of the day.
   * @param volume amount of stocks available for that company.
   * @param shares number of share purchased.
   */
  @Override
  public void setCurrentStock(String name, String date, float open, float high, float low,
                              float close, float volume,
                              float shares, float fee, float percentage, boolean isFuture) {
    this.company = name;
    this.date = date;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
    this.shares = shares;
    this.commisionFee = fee;
    this.percentage = percentage;
    this.isFuture = isFuture;
  }


  /**
   * Method used to update the quantity of the stocks.
   *
   * @param values value to be updated.
   */
  @Override
  public void updateStockValues(float values) {
    this.shares = values;
  }

  public void updateCommisionValue(float fee) {
    this.commisionFee = fee;
  }

  public float getCommisionFee() {
    return this.commisionFee;
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
  public float getShares() {
    return this.shares;
  }

  public void setShares(float shares) {
    this.shares = shares;
  }

  public float getPercentage() {
    return this.percentage;
  }

  public boolean getIsFuture() {
    return this.isFuture;
  }

  /**
   * Method used to check if the given date is valid or not.
   *
   * @param date given date.
   * @return boolean true if the date is valid else false.
   */
  public boolean isValidDate(String date) {
    try {
      LocalDate lt = LocalDate.parse(date);
      if (lt.compareTo(LocalDate.now()) < 0) {
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public StocksImpl createAndAddStockInPortfolio(HashMap map, String ticker, String date,
                                                 int stocks, float fee) throws ParseException {
    String tickerValue = ticker.toUpperCase();
    StocksImpl currStock = null;
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
    Date newDate = sdformat.parse(date);
    String formattedDateInput = sdformat.format(newDate);
    ArrayList values = (ArrayList) map.get(tickerValue);
    boolean foundDate = false;
    for (Object value : values) {
      StocksImpl currentStock = (StocksImpl) value;
      if (Objects.equals(currentStock.getDate(), formattedDateInput)) {
        currStock = new StocksImpl(tickerValue, currentStock.getDate(),
                currentStock.getOpen(),
                currentStock.getHigh(), currentStock.getLow(), currentStock.getClose(),
                currentStock.getVolume(), stocks, fee, 0, false);
        foundDate = true;
        break;
      }
    }
    if (!foundDate) {
      currStock = new StocksImpl(tickerValue, date, 0, 0, 0, 0, 0, 0, 0, 0, false);
    }
    return currStock;
  }

}