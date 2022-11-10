package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.concurrent.atomic.AtomicBoolean;

import model.FileAccessorsImpl;
import model.PortfolioImpl;
import model.StocksImpl;
import model.ListOfStocksImpl;
import view.PortfolioViewImpl;

import static model.Input.takeIntegerInput;
import static model.Input.takeStringInput;
import static model.Output.append;
import static model.Output.appendNewLine;

/**
 * Class that controls the portfolios and that implements the functions in  portfolio controller.
 */
public class PortfolioControllerImpl implements PortfolioController {


  private PortfolioImpl model;
  private PortfolioViewImpl view;

  private StocksImpl stocksImplModel;

  /**
   * Constructor that takes model and view as the input as parameters and initializes the model and
   * view.
   *
   * @param portfolioImpl     model input.
   * @param portfolioViewImpl view input.
   * @throws IOException invalid input of model or view.
   */
  public PortfolioControllerImpl(PortfolioImpl portfolioImpl, PortfolioViewImpl portfolioViewImpl)
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
  public PortfolioControllerImpl(StocksImpl model, PortfolioImpl portfolioImpl,
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
  public PortfolioImpl addStock() throws IOException {
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
  public PortfolioImpl afterAddingStock(PortfolioImpl model) throws IOException {
    append("Successfully added the stock in portfolio");
    appendNewLine();
    int input = 0;
    try {
      input = takeIntegerInput("Choose from below options.\n 1."
          + " Add another stock\n2. Save this portfolio. (You can not edit it after saving!!!)\n3. "
          + "Back to main menu.\n4. Exit.");
    } catch (Exception e) {
      append("Please enter a valid input.\n");
      afterAddingStock(model);
    }
    switch (input) {
      case 1:
        break;
      case 2:
        model.save();
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
   * Method used to ask the user if he wants to speculate and perform speculation on the selected
   * portfolio.
   *
   * @param input            name of the portfolio which the user chooses for speculation.
   * @param listOfStocksImpl list of stocks present in the portfolio.
   * @throws IOException invalid input.
   */
  @Override
  public void viewSpeculate(String input, ListOfStocksImpl listOfStocksImpl) throws IOException {
    try {
      FileAccessorsImpl fileAccessorsImpl = new FileAccessorsImpl();
      if (!fileAccessorsImpl.isFileExists(input)) {
        throw new FileNotFoundException(input);
      }

      HashMap<String, StocksImpl> portfolios = fileAccessorsImpl.viewFile(input);
      model.setPortfolio(portfolios);
      controllerToViewHelper(portfolios);
      String currInput = takeStringInput("Would you like to speculate your " +
          "portfolio?(YES/NO)");
      if (currInput.equals("YES")) {
        boolean isValidDate = viewSpeculateHelper(input, listOfStocksImpl);
        if (!isValidDate) {
          isValidDate = viewSpeculateHelper(input, listOfStocksImpl);
        }
      }
    } catch (FileNotFoundException e) {
      append("Portfolio doesn't exists");
      appendNewLine();
    } catch (IllegalArgumentException e) {
      append(e.getMessage());
    }
  }

  /**
   * Method to perform speculation operation on the portfolio.
   *
   * @param fileName         name of the file for the speculation.
   * @param listOfStocksImpl list of all the stocks in the file.
   * @return total value of the portfolio.
   * @throws IOException if the file name entered is invalid.
   */
  @Override
  public boolean viewSpeculateHelper(String fileName, ListOfStocksImpl listOfStocksImpl)
      throws IOException {
    Map.Entry<String, ArrayList<StocksImpl>> entry = (Map.Entry<String, ArrayList<StocksImpl>>)
        listOfStocksImpl.getLStocksMap()
            .entrySet().iterator().next();
    ArrayList<StocksImpl> currentStock = (ArrayList<StocksImpl>) listOfStocksImpl.getLStocksMap()
        .get(entry.getKey());
    String firstStockDate = currentStock.get(0).getDate();
    String lastStockDate = currentStock.get(currentStock.size() - 1).getDate();
    String input =
        takeStringInput("Enter the date between " + lastStockDate + " and "
            + firstStockDate);
    float total_value = 0;

    for (StocksImpl stocksImpl : model.getCompanyNames()) {
      currentStock = (ArrayList<StocksImpl>) listOfStocksImpl.getLStocksMap()
          .get(stocksImpl.getCompany());
      boolean dateExist = false;
      for (StocksImpl stock : currentStock) {
        if (stock.getDate().equals(input)) {
          dateExist = true;
          total_value += stock.getClose() * stocksImpl.getShares();
        }

      }
      if (!dateExist) {
        append("The entered date does not exist, please enter a valid date.\n");
        return false;
      }
    }
    append("Total price of portfolio is " + String.valueOf(total_value) + ".\n");
    return true;
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
            currentStock.getClose(), currentStock.getVolume(), currentStock.getShares());
        displayHeaders.set(false);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }

}
