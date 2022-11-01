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
import model.Portfolio;
import model.Stocks;
import model.StocksList;
import view.PortfolioView;
import view.StockView;

import static model.Input.takeIntegerInput;
import static model.Input.takeStringInput;
import static model.Output.append;

public class StockController {

  private Stocks model;
  private StockView view;

  private StocksList stocksList;

  public StockController(Stocks stocks, StockView stockView) throws IOException {
    //super();
    this.model = stocks;
    this.view = stockView;
  }

  public boolean afterStocksDisplay(Object model) throws IOException {
    Integer input = takeIntegerInput("Select from the following options.\n1. Add this to "
            + "portfolio? (YES/NO).\n2. Do not add and search stocks for new company.\n3. Go back. "
            + "\n 4. Exit\n");
    Stocks stocksModel = new Stocks();
    StockView stocksView = new StockView();
    StockController stockController = new StockController(stocksModel, stocksView);
    boolean isGo = false;
    switch (input) {
      case 1:
        // save the portfolio
        stockController.addStockToPortfolio(model);
        break;
      case 2:
//        Stocks stocksModel = new Stocks();
//        StockView stocksView = new StockView();
//        StockController stockController = new StockController(stocksModel,stocksView);
        stockController.getTickerValue();
        break;
      case 3:
        isGo = true;
        break;
      case 4:
        System.exit(0);
        break;
      default:
        System.out.println("Invalid input");
        System.exit(0);
    }
    return isGo;
  }

  protected void setStocksList(StocksList list) {
    stocksList = list;
  }
  protected void getTickerValue() throws IOException {
    String tickerValue = takeStringInput("Enter the ticker value.");
//    this.out.append("Ticker value is  ").append(tickerValue);
    HashMap map = stocksList.getMap();
    ArrayList values = (ArrayList) map.get(tickerValue);
    if (values == null) {
      append("You entered an invalid ticker symbol. Please try again");
      //go();
    } else {
      Stocks currentStock = (Stocks) values.get(0);
      model.setCurrentStock(tickerValue, currentStock.getDate(), currentStock.getOpen(),
              currentStock.getHigh(), currentStock.getLow(), currentStock.getClose(),
              currentStock.getVolume(), 0);
      view.displayListOfDates(tickerValue, values);
      afterStocksDisplay(model);
    }

  }

  protected void addStockToPortfolio(Object models) throws IOException {
    Stocks currModel = (Stocks) models;
    int value =
            takeIntegerInput(
                    "Enter the number of shares you want to invest in " + currModel.getCompany());
    currModel.updateStockValues(value);
    PortfolioView portfolioView = new PortfolioView();
    PortfolioController portfolioController = new PortfolioController(currModel, new Portfolio(),
            portfolioView);
    portfolioController.addStock();
  }

}
