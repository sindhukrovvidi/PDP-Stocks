package view;

import java.io.IOException;

public interface PortfolioView {

  void displayPortfolio(boolean displayHeaders, String company, String date,
      float open, float high,
      float low, float close, float volume, int shares) throws IOException;

}
