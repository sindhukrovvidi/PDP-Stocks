package controller;

import java.io.IOException;
import java.util.HashMap;

import java.util.concurrent.atomic.AtomicBoolean;

import model.Portfolio;
import model.PortfolioImpl;
import model.StocksImpl;
import view.PortfolioViewImpl;

import static model.Input.takeIntegerInput;
import static model.Output.append;
import static model.Output.appendNewLine;

/**
 * Class that controls the portfolios and that implements the functions in  portfolio controller.
 */
abstract public class PortfolioControllerImpl extends Controller implements PortfolioController {


  protected Portfolio model;
  protected PortfolioViewImpl view;

  protected StocksImpl stocksImplModel;

  /**
   * Constructor that takes model and view as the input as parameters and initializes the model and
   * view.
   *
   * @param portfolioImpl     model input.
   * @param portfolioViewImpl view input.
   * @throws IOException invalid input of model or view.
   */
  public PortfolioControllerImpl(Portfolio portfolioImpl, PortfolioViewImpl portfolioViewImpl)
          throws IOException {
    super();
    this.model = portfolioImpl;
    this.view = portfolioViewImpl;
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
                                 PortfolioViewImpl view)
          throws IOException {
    super();
    this.stocksImplModel = model;
    this.model = portfolioImpl;
    this.view = view;
  }

  /**
   * Method that is used to map the stocks to the model using a hashmap.Also tells the user that the
   * stock was added successfully, it is currently in draft.
   *
   * @return model after adding the stocks.
   * @throws IOException invalid stock details.
   */
  @Override
  public Portfolio addStock() throws IOException {
    model.addStockInPortfolio(stocksImplModel);
    append("Successfully added the stock in draft portfolio");
    appendNewLine();
    HashMap<String, StocksImpl> portfolioEntries = model.getPortfolio();
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
        if(model.getIsFlexible()) {
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
  public void controllerToViewHelper(HashMap<String, StocksImpl> portfolioEntries) {
    AtomicBoolean displayHeaders = new AtomicBoolean(true);
    portfolioEntries.forEach((k, v) -> {
      StocksImpl currentStock = v;
      try {
        view.displayPortfolio(displayHeaders.get(), currentStock.getCompany(),
                currentStock.getDate(),
                currentStock.getOpen(), currentStock.getHigh(), currentStock.getLow(),
                currentStock.getClose(), currentStock.getVolume(), currentStock.getShares(),
                currentStock.getCommisionFee());
        displayHeaders.set(false);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }

}
