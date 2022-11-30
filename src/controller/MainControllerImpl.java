package controller;

import java.io.IOException;

import java.nio.file.FileAlreadyExistsException;
import java.text.ParseException;
import java.util.Arrays;

import model.FileAccessors;
import model.FileAccessorsImpl;
import model.Portfolio;
import model.PortfolioImpl;
import model.StocksImpl;
//import view.JFrameStocksView;
import view.PortfolioViewImpl;
import view.StockView;
import view.StockViewImpl;

import static model.Input.takeIntegerInput;
import static model.Input.takeStringInput;

/**
 * The main controller class implements all the methods in the main controller.
 */
public class MainControllerImpl implements MainController {

  StocksImpl stocksImpl;
  Portfolio portfolioImpl;

  StockView view;
  private FileAccessorsImpl fileAccessorsImpl = new FileAccessorsImpl();

  /**
   * Constructor that initializes the list of stocks and the portfolio.
   *
   * @throws IOException invalid stocks or portfolio.
   */
  public MainControllerImpl() throws IOException {
    this.portfolioImpl = new PortfolioImpl();
    this.view = new StockViewImpl();
  }


  /**
   * This method is responsible for the display of main menu and handles the main menu.
   *
   * @throws IOException when the user chooses an invalid or unlisted option.
   */
  @Override
  public void programStartsHere() throws IOException, ParseException {
    try {
      view.displayStarterMenu(this);
      Integer option = takeIntegerInput();

      view.addFeature(this);
      getInitialController(option);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * This method takes the option chosen by the user from the main menu and controls accordingly.
   *
   * @param option chosen by the user from the main menu.
   * @throws IOException when the user chooses an invalid or unlisted option.
   */
  @Override
  public void getInitialController(int option) throws IOException, ParseException {
    String input;
    boolean isFlexible = false;
    PortfolioViewImpl portfolioViewImpl = new PortfolioViewImpl();
    PortfolioImpl portfolioImpl = new PortfolioImpl();
    String[] files;
    PortfolioControllerImpl portfolioControllerImpl;
    StockControllerImpl stocksController;

    switch (option) {
      case 1:
        this.portfolioImpl.setIsFlexible(false);
        isFlexible = false;
        getFileNameInput();
        getInitialController(6);
        break;
      case 2:
        isFlexible = true;
        this.portfolioImpl.setIsFlexible(true);
        getFileNameInput();
        getInitialController(11);

        break;
      case 3:
        isFlexible = false;
        files = fileAccessorsImpl.listOfPortfolioFiles("portfolios/inflexible");

        view.enterExistingPortfolioName(Arrays.toString(files));
        input = takeStringInput();

        portfolioControllerImpl = new InflexiblePortfolioControllerImpl(portfolioImpl,
            view);
        this.portfolioImpl = portfolioControllerImpl.viewSpeculate(input);
        programStartsHere();
        break;
      case 4: // view flexible portfolio
        portfolioImpl.setIsFlexible(true);
        isFlexible = true;
        files = fileAccessorsImpl.listOfPortfolioFiles("portfolios/flexible");

        view.enterExistingPortfolioName(Arrays.toString(files));
        input = takeStringInput();

        portfolioControllerImpl = new FlexiblePortfolioControllerImpl(portfolioImpl,
            view);
        this.portfolioImpl = portfolioControllerImpl.viewSpeculate(input);
        if (portfolioImpl.getIsFlexible() && portfolioImpl.getBuy()) {
          getInitialController(11);
        }
        programStartsHere();
        break;
      case 5:
        System.exit(0);
        break;

      case 6:

        if (isFlexible) {
          getInitialController(11);
        } else {
          stocksController = new InflexibleStockControllerImpl(new StocksImpl(), view);
          StocksImpl stocksImpl = stocksController.getTickerValue();
          if (stocksImpl == null) {
            getInitialController(6);
          } else {
            this.stocksImpl = stocksImpl;
            getInitialController(7);
          }
        }
        break;
      case 7:
        PortfolioViewImpl portfolioViewsImpl = new PortfolioViewImpl();
        PortfolioControllerImpl portfolioControllersImpl;
        if (isFlexible) {
          portfolioControllersImpl = new FlexiblePortfolioControllerImpl(
              this.stocksImpl,
              this.portfolioImpl,
              view);
        } else {
          portfolioControllersImpl = new InflexiblePortfolioControllerImpl(
              this.stocksImpl,
              this.portfolioImpl,
              view);
        }
        this.portfolioImpl = portfolioControllersImpl.addStock();
        if (this.portfolioImpl == null) {
          this.portfolioImpl = new PortfolioImpl();
          programStartsHere();
        } else if (!this.portfolioImpl.isSaved()) {
          getInitialController(6);
        }
        break;

      case 11:

        portfolioControllersImpl = new FlexiblePortfolioControllerImpl(
            this.stocksImpl,
            this.portfolioImpl,
            view,
            new FlexibleStockControllerImpl(new StocksImpl(), view));

        StocksImpl stocksImpls = portfolioControllersImpl.isBulkStockAddition();
        if (stocksImpls == null) {
          getInitialController(11);
        } else {
          this.stocksImpl = stocksImpls;
          getInitialController(7); // or 11
        }
        break;
      default:
        // there is no default for this.
        break;
    }

  }

  /**
   * The method that is used to take the filename input from the user which he wants to view.
   *
   * @throws IOException    when the input is invalid.
   * @throws ParseException when the data is not parsable.
   */
  public void getFileNameInput() throws IOException, ParseException {
    try {
      view.enterPortfolioName();
      String input = takeStringInput();

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
      view.portfolioExistsMessage();
      programStartsHere();
    }
  }
}
