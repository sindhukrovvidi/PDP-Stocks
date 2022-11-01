package controller;

import java.io.BufferedReader;
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

import static model.Input.takeIntegerInput;
import static model.Input.takeStringInput;

public class MainController {

  // protected Readable in;

  // protected Appendable out;

  StocksList stocksList;

  String fileName;

  public MainController() throws IOException {
    this.stocksList = preprocessStocksData();
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
            "Welcome to stock market\n. Choose from below options to proceed "
                    + "further."
                    + "(Type the index number). "
                    + "\n1. Create a portfolio.\n2. View portfolio \n3. Exit\n");
    getInitialController(option);
  }

  private void getInitialController(int option) throws IOException {
    System.out.println("Inside getInitialController");
    switch (option) {
      case 1:
        String input = takeStringInput("Enter the name for your portfolio");
//        FileAccessors files = new FileAccessors();
//        this.fileName = input;
//        files.createCSV(input);
        StockController stocksController = new StockController(new Stocks(), new StockView());
        stocksController.setStocksList(stocksList);
        stocksController.getTickerValue();
        go();
        break;
    }

  }


}
