package controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import model.Stocks;
import view.StocksView;

public class StocksController {

  private Stocks model;
  private StocksView view;

  private final String apiKey = "5VTE6APNI1ZMURXC";

  private URL url = null;

  private boolean fetchData(String stockSymbol) {
    try {
      System.out.println("Inside Controller fetchData");
      url = new URL("https://www.alphavantage"
          + ".co/query?function=GLOBAL_QUOTE"
//          + "&outputsize=compact"
          + "&symbol"
          + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
          + "no longer works");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();
    StringBuilder output1 = new StringBuilder();

    try {
      in = url.openStream();
      int b;
      int isValues = 0;

      while ((b = in.read()) != -1) {
        if (isValues == 1) {
          output1.append((char) b);
        } else {
          if (!((char) b == '\r' || (char) b == '\n')) {
            output.append((char) b);
          }
        }
        if ((char) b == '\n') {
          isValues = 1;
        }
      }
      String[] headers = output.toString().split(",");
      String[] values = output1.toString().split(",");
      if (output.toString().equals("{}")) {
        return false;
//        System.out.println("no price data found for " + stockSymbol + ". Please enter a valid "
//            + "ticker symbol");
      } else {
        for (int i = 0; i < headers.length; i++) {
          updateStocksModel(headers[i], values[i]);
        }
        updateStockView();
        return true;
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
  }


  private void updateStocksModel(String key, String value) {
    switch (key) {
      case "symbol":
        setStocksCompany(value);
        break;
      case "open":
        model.setOpenValue(Float.parseFloat(value));
        break;
      case "high":
        model.setHighValue(Float.parseFloat(value));
        break;
      case "low":
        model.setLowValue(Float.parseFloat(value));
        break;
      case "price":
        model.setPriceValue(Float.parseFloat(value));
        break;
      case "latestDay":
        model.setLatestDay(value);
        break;
      case "previousClose":
        model.setPreviousClose(Float.parseFloat(value));
        break;

      default:
        break;
//        System.out.println("matching not found");
    }
  }

  public StocksController(Stocks model, StocksView view) {
    this.model = model;
    this.view = view;
  }

  public boolean getTickerSymbol(String tickerSymbol) {
    return fetchData(tickerSymbol);
  }

  public void setSharesValue(int shares) {
    model.setStocks(shares);
  }

  public int getSharesValue() {
    return model.getSharesValue();
  }

  public void setStocksCompany(String companyName) {
    model.setCompany(companyName);
  }

  public void updateStockView() {
    view.displaySelectedStockDetails(model.getCompany(), model.getOpenValue(),
        model.getHighValue(), model.getLowValue(), model.getPriceValue(), model.getLatestDay(),
        model.getPreviousClose());
  }

}
