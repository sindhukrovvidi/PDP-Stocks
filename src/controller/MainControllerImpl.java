package controller;

import java.io.BufferedReader;
import java.io.IOException;

import model.FileAccessorsImpl;
import model.PortfolioImpl;
import model.StocksImpl;
import model.ListOfStocksImpl;
import view.PortfolioView;
import view.StockView;

import static model.Input.takeIntegerInput;
import static model.Input.takeStringInput;
import static model.Output.append;

/**
 *
 */
public class MainControllerImpl implements MainController {

  ListOfStocksImpl listOfStocksImpl;
  StocksImpl stocksImpl;
  PortfolioImpl portfolioImpl;


  private FileAccessorsImpl fileAccessorsImpl = new FileAccessorsImpl();

  public MainControllerImpl() throws IOException {
    this.listOfStocksImpl = preprocessStocksData();
    this.portfolioImpl = new PortfolioImpl();
  }

  /**
   *
   * @return
   * @throws IOException
   */
  @Override
  public ListOfStocksImpl preprocessStocksData() throws IOException {
    FileAccessorsImpl reader = new FileAccessorsImpl();
    BufferedReader output = reader.readCSV("stocksdata/stocks_data.csv");

    return (new ListOfStocksImpl(output));
  }

  /**
   *
   * @throws IOException
   */
  @Override
  public void go() throws IOException {
    try {
      Integer option = takeIntegerInput(
          "Choose from below options to proceed "
              + "further."
              + "(Type the index number). "
              + "\n1. Create a portfolio.\n2. View & speculate existing portfolio \n3. Exit\n");
      getInitialController(option);
    } catch (Exception e) {
      append("Please enter a valid input.");
      go();
    }

  }

  /**
   *
   * @param option
   * @throws IOException
   */
  @Override
  public void getInitialController(int option) throws IOException {
    String input;
    switch (option) {
      case 1:
        try {
          input = takeStringInput("Enter the name for your portfolio.");
          this.portfolioImpl.setPortfolioName(input);
        } catch (Exception e) {
          append("Invalid input or the file already exists. Please try again.\n");
          go();
        }
        getInitialController(4);
        break;
      case 2:
        input = takeStringInput("Enter the name for your portfolio to view");
        PortfolioView portfolioView = new PortfolioView();
        PortfolioImpl portfolioImpl = new PortfolioImpl();
        PortfolioController portfolioController = new PortfolioController(portfolioImpl,
            portfolioView);
        portfolioController.viewSpeculate(input, listOfStocksImpl);
        go();
        break;
      case 3:
        System.exit(0);
      case 4:
        StockController stocksController = new StockController(new StocksImpl(), new StockView());
        stocksController.setStocksList(listOfStocksImpl);
        StocksImpl stocksImpl = stocksController.getTickerValue();
        if (stocksImpl == null) {
          go();
        } else {
          this.stocksImpl = stocksImpl;
          getInitialController(5);
        }
        break;
      case 5:
        PortfolioView portfolioViews = new PortfolioView();
        PortfolioController portfolioControllers = new PortfolioController(this.stocksImpl,
            this.portfolioImpl,
            portfolioViews);
        this.portfolioImpl = portfolioControllers.addStock();
        if (this.portfolioImpl == null) {
          this.portfolioImpl = new PortfolioImpl();
          go();
        } else if (!this.portfolioImpl.isSaved()) {
          getInitialController(4);
        }
        break;
    }

  }


}
