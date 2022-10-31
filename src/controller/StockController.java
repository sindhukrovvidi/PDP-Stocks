package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.FileAccessors;
import model.FileAccessorsInterface;
import model.ListOfStocks;
import model.Stocks;
import view.StockView;

public class StockController extends MainController {

  private Stocks model;
  private StockView view;

  public StockController(Stocks stocks, StockView stockView) throws IOException {
    super();
    this.model = stocks;
    this.view = stockView;
  }

  protected void getTickerValue() throws IOException {
    String tickerValue = takeStringInput("Enter the ticker value.");
    this.out.append("Ticker value is  ").append(tickerValue);
    HashMap map = this.stocksList;
    ArrayList values = (ArrayList) map.get(tickerValue);
    if (values == null) {
      this.out.append("You entered an invalid ticker symbol. Please try again");
    } else {
      view.displayListOfDates(tickerValue, values);
    }
    afterStocksDisplay();
  }
}
