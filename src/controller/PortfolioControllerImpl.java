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
 *
 */
public class PortfolioControllerImpl implements PortfolioController{


  private PortfolioImpl model;
  private PortfolioViewImpl view;

  private StocksImpl stocksImplModel;

  /**
   *
   * @param portfolioImpl
   * @param portfolioViewImpl
   * @throws IOException
   */
  public PortfolioControllerImpl(PortfolioImpl portfolioImpl, PortfolioViewImpl portfolioViewImpl)
      throws IOException {
    super();
    this.model = portfolioImpl;
    this.view = portfolioViewImpl;
  }

  /**
   *
   * @param model
   * @param portfolioImpl
   * @param view
   * @throws IOException
   */
  public PortfolioControllerImpl(StocksImpl model, PortfolioImpl portfolioImpl, PortfolioViewImpl view)
      throws IOException {
    super();
    this.stocksImplModel = model;
    this.model = portfolioImpl;
    this.view = view;
  }

  /**
   *
   * @return
   * @throws IOException
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
   *
   * @param model
   * @return
   * @throws IOException
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
      case 3:
        model = null;
        break;
      case 4:
        System.exit(0);
      default:
        append("Invalid input");
        System.exit(0);
    }
    return model;
  }

  /**
   *
   * @param input
   * @param listOfStocksImpl
   * @throws IOException
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
      String currInput = takeStringInput("Would you like to speculate your portfolio?(YES/NO)");
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
   *
   * @param fileName
   * @param listOfStocksImpl
   * @return
   * @throws IOException
   */
  @Override
  public boolean viewSpeculateHelper(String fileName, ListOfStocksImpl listOfStocksImpl)
      throws IOException {
    Map.Entry<String, ArrayList<StocksImpl>> entry = (Map.Entry<String, ArrayList<StocksImpl>>) listOfStocksImpl.getLStocksMap()
        .entrySet().iterator().next();
    ArrayList<StocksImpl> currentStock = (ArrayList<StocksImpl>) listOfStocksImpl.getLStocksMap()
        .get(entry.getKey());
    String firstStockDate = currentStock.get(0).getDate();
    String lastStockDate = currentStock.get(currentStock.size() - 1).getDate();
    String input =
        takeStringInput("Enter the date between " + firstStockDate + " and " + lastStockDate);
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
   *
   * @param portfolioEntries
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
