package controller;

import java.io.BufferedReader;
import java.io.IOException;

import java.util.Arrays;
import model.FileAccessorsImpl;
import model.PortfolioImpl;
import model.StocksImpl;
import model.ListOfStocksImpl;
import view.PortfolioViewImpl;
import view.StockViewImpl;

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
        String[] files = fileAccessorsImpl.listOfPortfolioFiles("portfolios/");
        input =
            takeStringInput(
                "Enter the name of the portfolio from the below list: (Just enter the filename "
                    + "without the extension \n" + Arrays.toString(files));
        PortfolioViewImpl portfolioViewImpl = new PortfolioViewImpl();
        PortfolioImpl portfolioImpl = new PortfolioImpl();
        PortfolioControllerImpl portfolioControllerImpl = new PortfolioControllerImpl(portfolioImpl,
            portfolioViewImpl);
        portfolioControllerImpl.viewSpeculate(input, listOfStocksImpl);
        go();
        break;
      case 3:
        System.exit(0);
      case 4:
        StockControllerImpl stocksController = new StockControllerImpl(new StocksImpl(),
            new StockViewImpl());
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
        PortfolioViewImpl portfolioViewsImpl = new PortfolioViewImpl();
        PortfolioControllerImpl portfolioControllersImpl = new PortfolioControllerImpl(
            this.stocksImpl,
            this.portfolioImpl,
            portfolioViewsImpl);
        this.portfolioImpl = portfolioControllersImpl.addStock();
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
