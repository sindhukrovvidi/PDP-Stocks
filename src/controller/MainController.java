package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import model.FileAccessors;
import model.ListOfStocks;
import model.Stocks;
import model.StocksList;
import view.StockView;

public class MainController {

  protected Readable in;

  protected Appendable out;

  HashMap stocksList;

  public MainController() throws IOException {
    this.in = new InputStreamReader(System.in);
    this.out = System.out;
    this.stocksList = preprocessStocksData();
//    preprocessStocksData();
  }

//  public MainController(Readable in, Appendable out) {
//    this.in = in;
//    this.out = out;
//  }

  public HashMap preprocessStocksData() throws IOException {
    FileAccessors reader = new FileAccessors(new FileReader("stocksdata/stocks_data.csv"));
    FileAccessors output = (FileAccessors) reader.readCSV();

    return (new StocksList(output)).getMap();
  }
  public Integer takeIntegerInput(String question) throws IOException {
    Objects.requireNonNull(this);
    this.out.append(question).append("\n");
    Scanner scan = new Scanner(this.in);
    Integer input = scan.nextInt();
    return input;
  }

  public String takeStringInput(String question) throws IOException {
    Objects.requireNonNull(this);
    this.out.append(question).append("\n");
    Scanner scan = new Scanner(this.in);
    String input = scan.nextLine();
    return input.toString();
  }

  public void go() throws IOException {
    Integer option = takeIntegerInput(
        "Welcome to stock market. Choose from below options to proceed "
            + "further."
            + "(Type the index number). "
            + "\n1. Enter company names and create a portfolio.\n2. View portfolio \n3. Exit\n");
    getInitialController(option);
  }

  private void getInitialController(int option) throws IOException {
    System.out.println("Inside getInitialController");
    switch (option) {
      case 1:
        StockController stocksController = new StockController(new Stocks(), new StockView());
        stocksController.getTickerValue();
        break;
    }

  }

  public void afterStocksDisplay(Object model) throws IOException {
    Integer input = takeIntegerInput("Select from the following options.\n1. Add this to "
        + "portfolio? (YES/NO).\n 2. Do not add and search stocks for new company.\n 3. Go back. "
        + "\n 4. Exit\n");
    Stocks stocksModel = new Stocks();
    StockView stocksView = new StockView();
    StockController stockController = new StockController(stocksModel,stocksView);
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
        go();
        break;
      case 4:
        System.exit(0);
        break;
      default:
        System.out.println("Invalid input");
        System.exit(0);
    }
  }

}
