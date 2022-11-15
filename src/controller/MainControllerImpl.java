package controller;

import java.io.BufferedReader;
import java.io.IOException;

import java.nio.file.FileAlreadyExistsException;
import java.util.Arrays;

import model.FileAccessors;
import model.FileAccessorsImpl;
import model.Portfolio;
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

  StocksImpl stocksImpl;
  Portfolio portfolioImpl;
  private boolean isFlexible;

  private FileAccessorsImpl fileAccessorsImpl = new FileAccessorsImpl();

  /**
   * Constructor that initializes the list of stocks and the portfolio.
   *
   * @throws IOException invalid stocks or portfolio.
   */
  public MainControllerImpl() throws IOException {
    this.portfolioImpl = new PortfolioImpl();
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
      throw e;
      //append("Please enter a valid input.");
      // programStartsHere();
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
        String input1 = takeStringInput("Do you want the type of portfolio as flexible?" +
                "(YES/NO)");
        if (input1.equals("YES")) {
          isFlexible = true;
          this.portfolioImpl.setIsFlexible(true);
        } else {
          this.portfolioImpl.setIsFlexible(false);
          isFlexible = false;
        }
        try {
          input = takeStringInput("Enter the name for your portfolio.");
          FileAccessors fileAccessor = new FileAccessorsImpl();
          String path = this.portfolioImpl.getIsFlexible() ? "portfolios/flexible" : "portfolios" +
                  "/inflexible";
          if (!fileAccessor.isFileExists(input, path)) {
            this.portfolioImpl.setPortfolioName(input);
          } else {
            throw new FileAlreadyExistsException(input);
          }
          this.portfolioImpl.setPortfolioName(input);
        } catch (Exception e) {
          append("Invalid input or the file already exists. Please try again.\n");
          programStartsHere();
        }
//        String input1 = takeStringInput("Do you want the type of portfolio as flexible?" +
//                "(YES/NO)");
//        if (input1.equals("YES")) {
//          isFlexible = true;
//          this.portfolioImpl.setIsFlexible(true);
//        } else {
//          this.portfolioImpl.setIsFlexible(false);
//          isFlexible = false;
//        }
        getInitialController(4);
        break;
      case 2:
        String input2 = takeStringInput("Do you want to view a flexible portfolio?" +
                "(YES/NO)");
        PortfolioViewImpl portfolioViewImpl = new PortfolioViewImpl();
        PortfolioImpl portfolioImpl = new PortfolioImpl();
        String[] files;
        if (input2.equals("YES")) {
          files = fileAccessorsImpl.listOfPortfolioFiles("portfolios/flexible");
        } else {
          files = fileAccessorsImpl.listOfPortfolioFiles("portfolios/inflexible");
        }
        // TODO ask the user if he wants flexible or inflexible.
        input =
                takeStringInput(
                        "Enter the name of the portfolio from the below list: " +
                                "(Just enter the filename without the extension \n" + Arrays.toString(files));
        PortfolioControllerImpl portfolioControllerImpl;
        if (input2.equals("YES")) {
          portfolioControllerImpl = new FlexiblePortfolioControllerImpl(portfolioImpl,
                  portfolioViewImpl);
        } else {
          portfolioControllerImpl = new InflexiblePortfolioControllerImpl(portfolioImpl,
                  portfolioViewImpl);
        }

        this.portfolioImpl = portfolioControllerImpl.viewSpeculate(input);
        if(portfolioImpl.getIsFlexible()){
          getInitialController(4);
        }
        programStartsHere();
        break;
      case 3:
        System.exit(0);
        break;
      case 4:
        StockControllerImpl stocksController;
        if (isFlexible) {
          stocksController = new FlexibleStockControllerImpl(new StocksImpl(), new StockViewImpl());
        } else {
          stocksController = new InflexibleStockControllerImpl(new StocksImpl(), new StockViewImpl());
        }
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
        PortfolioControllerImpl portfolioControllersImpl;
        if (isFlexible) {
          portfolioControllersImpl = new FlexiblePortfolioControllerImpl(
                  this.stocksImpl,
                  this.portfolioImpl,
                  portfolioViewsImpl);
        } else {
          portfolioControllersImpl = new InflexiblePortfolioControllerImpl(
                  this.stocksImpl,
                  this.portfolioImpl,
                  portfolioViewsImpl);
        }
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
