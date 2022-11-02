package model;

/**
 *
 */
public interface Stocks {

  /**
   *
   * @param company
   * @param date
   * @param open
   * @param high
   * @param low
   * @param close
   * @param volume
   * @param shares
   */
  void setCurrentStock(String company, String date, float open, float high, float low,
      float close, float volume,
      int shares);

  /**
   *
   * @param values
   */
  void updateStockValues(int values);

  /**
   *
   * @return
   */
  String getDate();

  /**
   *
   * @return
   */
  float getOpen();

  /**
   *
   * @return
   */
  String getCompany();

  /**
   *
   * @return
   */
  float getHigh();

  /**
   *
   * @return
   */
  float getLow();

  /**
   *
   * @return
   */
  float getClose();

  /**
   *
   * @return
   */
  float getVolume();

  int getShares();


}
