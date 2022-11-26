package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;

import model.Portfolio;
import model.StocksImpl;
import view.PortfolioViewImpl;
import view.StockView;

import static model.Input.takeFloatInput;
import static model.Input.takeIntegerInput;
import static model.Input.takeStringInput;
import static model.Output.append;
import static model.Output.appendNewLine;

/**
 * Class that controls the portfolios and that implements the functions in  portfolio controller.
 */
abstract public class PortfolioControllerImpl extends Controller implements PortfolioController {


  protected Portfolio model;
  protected StockView view;

  protected StocksImpl stocksImplModel;

  protected StockController stocksController;

  /**
   * Constructor that takes model and view as the input as parameters and initializes the model and
   * view.
   *
   * @param portfolioImpl model input.
   * @param view          view input.
   * @throws IOException invalid input of model or view.
   */
  public PortfolioControllerImpl(Portfolio portfolioImpl, StockView view)
      throws IOException {
    super();
    this.model = portfolioImpl;
    this.view = view;
  }

  /**
   * Constructor that takes model,stock model and view as parameters and initializes them.
   *
   * @param model         the model input.
   * @param portfolioImpl portfolio implementation.
   * @param view          view input.
   * @throws IOException if the parameters given are invalid.
   */
  public PortfolioControllerImpl(StocksImpl model, Portfolio portfolioImpl,
      StockView view)
      throws IOException {
    super();
    this.stocksImplModel = model;
    this.model = portfolioImpl;
    this.view = view;
  }

  public PortfolioControllerImpl(StocksImpl stocksImpl, Portfolio portfolioImpl,
      StockView view, StockController controller) throws IOException {
    super();
    this.stocksImplModel = stocksImpl;
    this.model = portfolioImpl;
    this.view = view;
    this.stocksController = controller;
  }

  /**
   * Method that is used to map the stocks to the model using a hashmap.Also tells the user that the
   * stock was added successfully, it is currently in draft.
   *
   * @return model after adding the stocks.
   * @throws IOException invalid stock details.
   */
  @Override
  public Portfolio addStock() throws IOException, ParseException {
    model.addStockInPortfolio(stocksImplModel);
    append("Successfully added the stock in draft portfolio");
    appendNewLine();
    HashMap<String, TreeMap<Date, StocksImpl>> portfolioEntries = model.getPortfolio();
    controllerToViewHelper(portfolioEntries);
    return afterAddingStock(model);
  }

  /**
   * Method that takes a confirmation from the user and saves the stocks into the portfolio.It also
   * provides an option for the user to add a new stock after adding one and saving it.
   *
   * @param model into which the stocks are to be written.
   * @return model after updating the stock.
   * @throws IOException if the entered option is invalid or not listed.
   */
  @Override
  public Portfolio afterAddingStock(Portfolio model) throws IOException {
    append("Successfully added the stock in portfolio");
    appendNewLine();
    int input = 0;
    try {
      input = takeIntegerInput("Choose from below options.\n 1."
          + " Add another stock\n2. Save this portfolio.\n3. "
          + "Back to main menu.\n4. Exit.");
    } catch (Exception e) {
      append("Please enter a valid input.\n");
      afterAddingStock(model);
    }
    switch (input) {
      case 1:
        break;
      case 2:
        if (model.getIsFlexible()) {
          model.save("portfolios/flexible");
        } else {
          model.save("portfolios/inflexible");
        }

        break;
      case 3:
        model = null;
        break;
      case 4:
        System.exit(0);
        break;
      default:
        append("Invalid input");
        System.exit(0);
    }
    return model;
  }

  /**
   * Method used to format the view of a portfolio.
   *
   * @param portfolioEntries hash map of the portfolio entries in the file.
   */
  @Override
  public void controllerToViewHelper(HashMap<String, TreeMap<Date, StocksImpl>> portfolioEntries) {
    AtomicBoolean displayHeaders = new AtomicBoolean(true);
    portfolioEntries.forEach((k, v) -> {
      v.forEach((key, value) -> {
        try {
          view.displayPortfolio(displayHeaders.get(), value.getCompany(),
              value.getDate(),
              value.getOpen(), value.getHigh(), value.getLow(),
              value.getClose(), value.getVolume(), value.getShares(),
              value.getCommisionFee());
          displayHeaders.set(false);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      });
    });
  }


  @Override
  public StocksImpl isBulkStockAddition() throws IOException, ParseException {
    StocksImpl stocksImpl = null;
    int input = takeIntegerInput("Choose from the below options.\n"
        + "1. Add a single stock to the portfolio.\n"
        + "2. Add multiple stocks at once.\n"
        + "3. Save the current portfolio.\n");
    switch (input) {
      case 1:
        stocksImpl = stocksController.getTickerValue();
        break;
      case 2:
        investMultipleStocksAtOnce();
        stocksImpl = null;
        break;
      case 3:
        model.save("portfolios/flexible");
      default:
        System.exit(0);
    }
    return stocksImpl;
  }

  public void investMultipleStocksAtOnce() throws IOException, ParseException {
    String stocksInput = takeStringInput("Enter the list of stocks with coma (,) separated.");
    float valueInvested = takeFloatInput("Enter the amount to be invested.");
    String weightage = takeStringInput("Enter the weightage for each stock. Keep in mind that it "
        + "should add up to 100!!!");
    int fee = takeIntegerInput("Enter the commission fee");
    String lowerDate = takeStringInput("Enter the lower limit of the timerange in yyyy-mm-dd");
    String upperDate = takeStringInput("Enter the upper limit of the timerange in yyyy-mm-dd");
    int frequency = takeIntegerInput("Enter the frequency in days.");
    String[] tickerValuesList = stocksInput.split(",");
    for (String s : tickerValuesList) {
      updateListOfStocks(s.trim().toUpperCase());
      HashMap map = getStockList().getLStocksMap();
      ArrayList values = (ArrayList) map.get(s.trim().toUpperCase());
      if (values == null) {
        investMultipleStocksAtOnce();
//        return null;
      }
    }

    boolean areValid = model.validateInputForMultiStocks(valueInvested, weightage, fee, lowerDate,
        upperDate, frequency);
    if (!areValid) {
      investMultipleStocksAtOnce();
    } else {
      model.addMultipleStocksInPortfolio(getStockList().getLStocksMap(), lowerDate, upperDate,
          frequency, tickerValuesList, valueInvested, weightage, fee);
//      view.displayPortfolio();
      System.out.println("Updated in portfolio");
//      stocksController.afterStocksDisplay();
      // Should show more options
//      isBulkStockAddition();
    }
  }
}

