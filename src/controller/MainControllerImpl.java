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
 * The main controller class implements all the methods in the main controller.
 */
public class MainControllerImpl implements MainController {

  ListOfStocksImpl listOfStocksImpl;
  StocksImpl stocksImpl;
  PortfolioImpl portfolioImpl;


  private FileAccessorsImpl fileAccessorsImpl = new FileAccessorsImpl();

  /**
   * Constructor that initializes the list of stocks and the portfolio.
   *
   * @throws IOException invalid stocks or portfolio.
   */
  public MainControllerImpl() throws IOException {
    this.listOfStocksImpl = new ListOfStocksImpl();
    this.portfolioImpl = new PortfolioImpl();
  }

  /**
   * This method reads and processes the data and forms lists to be used for stock selection.
   *
   * @return list of the stocks after processing and forming a list.
   * @throws IOException when the entered data is invalid.
   */
  @Override
  public ListOfStocksImpl preprocessStocksData() throws IOException {
    FileAccessorsImpl reader = new FileAccessorsImpl();
    BufferedReader output = reader.readCSV("stocksdata/stocks_data.csv");

    return (new ListOfStocksImpl());
  }

  /**
   * This method is responsible for the display of main menu and handles the main menu.
   *
   * @throws IOException when the user chooses an invalid or unlisted option.
   */
  @Override
  public void programStartsHere() throws IOException {
    try {
      Integer option = takeIntegerInput(
          "Choose from below options to proceed further. (Type the index number)."
              + " \n1. Create a "
              + "portfolio.\n2. View & speculate existing portfolio " +
              "\n3. Exit\n");
      getInitialController(option);
    } catch (Exception e) {
      append("Please enter a valid input.");
      programStartsHere();
    }

  }

  /**
   * This method takes the option chosen by the user from the main menu and controls accordingly.
   *
   * @param option chosen by the user from the main menu.
   * @throws IOException when the user chooses an invalid or unlisted option.
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
          programStartsHere();
        }
        // TODO ask for the type of the portfolio to be created.(flexible, inflexible)
        getInitialController(4);
        break;
      case 2:
        String[] files = fileAccessorsImpl.listOfPortfolioFiles("portfolios/");
        input =
            takeStringInput(
                "Enter the name of the portfolio from the below list: " +
                    "(Just enter the filename without the extension)\n" + Arrays.toString(files));
        PortfolioViewImpl portfolioViewImpl = new PortfolioViewImpl();
        PortfolioImpl portfolioImpl = new PortfolioImpl();
        PortfolioControllerImpl portfolioControllerImpl = new PortfolioControllerImpl(portfolioImpl,
            portfolioViewImpl);
        portfolioControllerImpl.viewSpeculate(input, listOfStocksImpl);
        programStartsHere();
        break;
      case 3:
        System.exit(0);
        break;
      case 4:
        StockControllerImpl stocksController = new StockControllerImpl(new StocksImpl(),
            new StockViewImpl());
        stocksController.setStocksList(listOfStocksImpl);
        // TODO set flag to determine
        StocksImpl stocksImpl = stocksController.getTickerValue();
        if (stocksImpl == null) {
          programStartsHere();
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
          programStartsHere();
        } else if (!this.portfolioImpl.isSaved()) {
          getInitialController(4);
        }
        break;
      default:
        System.exit(0);
        break;
    }

  }


}
