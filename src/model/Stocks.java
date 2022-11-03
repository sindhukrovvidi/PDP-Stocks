package model;

/**
 * Interface that contains all the operations related to the stocks.
 */
public interface Stocks {

  /**
   * Method used to set the values to a current stock.
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
  void setCurrentStock(String company, String date, float open, float high, float low,
                       float close, float volume,
                       int shares);

  /**
   * Method used to update the quantity of the stocks.
   *
   * @param values value to be updated.
   */
  void updateStockValues(int values);

  /**
   * Method used to get date.
   *
   * @return date in string format.
   */
  String getDate();

  /**
   * Method used to get the opening price.
   *
   * @return opening price.
   */
  float getOpen();

  /**
   * Method used to get the company name.
   *
   * @return name of the company.
   */
  String getCompany();

  /**
   * Method used to get the high price of the day.
   *
   * @return highest price of the day.
   */
  float getHigh();

  /**
   * Method used to get the lowest price of the day.
   *
   * @return lowest price of the day.
   */
  float getLow();

  /**
   * Method used to get the closing price of the day.
   *
   * @return closing price of the day.
   */
  float getClose();

  /**
   * Method used to get the volume of stocks of a company.
   *
   * @return number of available stocks.
   */
  float getVolume();

  /**
   * Method used to get the number of shares purchased.
   *
   * @return number of shares.
   */
  int getShares();


}
