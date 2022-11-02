package view;

import java.io.IOException;

public interface StockView {

  void displayListOfDates(boolean displayHeaders, String company, String date,
      float open, float high,
      float low, float close, float volume) throws IOException;

}
