package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

import model.FileAccessors;
import model.ListOfStocks;
import model.Portfolio;
import model.Stocks;
import model.StocksList;
import view.PortfolioView;
import view.StockView;

import static model.Input.takeIntegerInput;
import static model.Input.takeStringInput;
import static model.Output.append;

public class MainController {

  // protected Readable in;

  // protected Appendable out;

  StocksList stocksList;
  Stocks stocks;
  Portfolio portfolio;
  String fileName;

  private FileAccessors fileAccessors = new FileAccessors();

  public MainController() throws IOException {
    this.stocksList = preprocessStocksData();
    this.portfolio = new Portfolio();
//    preprocessStocksData();
  }

//  public MainController(Readable in, Appendable out) {
//    this.in = in;
//    this.out = out;
//  }

  public StocksList preprocessStocksData() throws IOException {
    FileAccessors reader = new FileAccessors();
    BufferedReader output = reader.readCSV("stocksdata/stocks_data.csv");

    return (new StocksList(output));
  }


  public void go() throws IOException {
    Integer option = takeIntegerInput(
        "Welcome to stock market.\n Choose from below options to proceed "
            + "further."
            + "(Type the index number). "
            + "\n1. Create a portfolio.\n2. View portfolio \n3. Exit\n");
    getInitialController(option);
  }

  private void getInitialController(int option) throws IOException {
    System.out.println("Inside getInitialController");
    switch (option) {
      case 1:
        String input = takeStringInput("Enter the name for your portfolio.");
        try {
          this.portfolio.setPortfolioName(input);
        } catch (Exception e) {
          append("The file already exists.");
          go();
        }
        getInitialController(4);
        break;
      case 2:
        input = takeStringInput("Enter the name for your portfolio to view");
        try {
          if (!fileAccessors.isFileExists(input)) {
            throw new FileNotFoundException(input);
          }

        } catch (Exception e) {
          append("Portfolio doesn't exists");
          go();
        }

        break;
      case 4:
        StockController stocksController = new StockController(new Stocks(), new StockView());
        stocksController.setStocksList(stocksList);
        Stocks stocks = stocksController.getTickerValue();
        if (stocks == null) {
          go();
        } else {
          this.stocks = stocks;
          getInitialController(5);
        }
        break;
      case 5:
        PortfolioView portfolioView = new PortfolioView();
        PortfolioController portfolioController = new PortfolioController(this.stocks,
            this.portfolio,
            portfolioView);
        this.portfolio = portfolioController.addStock();
        if (this.portfolio == null) {
          this.portfolio = new Portfolio();
          go();
        } else if (!this.portfolio.isSaved()) {
          getInitialController(4);
        }
        break;
    }

  }


}
