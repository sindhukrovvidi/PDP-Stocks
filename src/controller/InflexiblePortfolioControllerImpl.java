package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.FileAccessorsImpl;
import model.ListOfStocksImpl;
import model.Portfolio;
import model.PortfolioImpl;
import model.StocksImpl;
import view.PortfolioViewImpl;

import static model.Input.takeStringInput;
import static model.Output.append;
import static model.Output.appendNewLine;

public class InflexiblePortfolioControllerImpl extends PortfolioControllerImpl {
  public InflexiblePortfolioControllerImpl(StocksImpl stocksImpl, Portfolio portfolioImpl,
                                           PortfolioViewImpl
                                                   portfolioViewImpl) throws IOException {
    super(stocksImpl, portfolioImpl, portfolioViewImpl);
  }

  public InflexiblePortfolioControllerImpl(Portfolio portfolioImpl,
                                           PortfolioViewImpl
                                                   portfolioViewImpl) throws IOException {
    super(portfolioImpl, portfolioViewImpl);
  }

  @Override
  public Portfolio viewSpeculate(String input) throws IOException {
    try {
      FileAccessorsImpl fileAccessorsImpl = new FileAccessorsImpl();
      if (!fileAccessorsImpl.isFileExists(input, "portfolios/inflexible")) {
        throw new FileNotFoundException(input);
      }

      HashMap<String, StocksImpl> portfolios = fileAccessorsImpl.viewFile(input, "portfolios" +
              "/inflexible");
      for (String s : portfolios.keySet()) {
        updateListOfStocks(s);
      }
      model.setPortfolio(portfolios);
      controllerToViewHelper(portfolios);
      String currInput = takeStringInput("Would you like to speculate your " +
              "portfolio?(YES/NO)");
      if (currInput.equals("YES")) {
        boolean isValidDate = viewSpeculateHelper(input, getStockList());
        if (!isValidDate) {
          isValidDate = viewSpeculateHelper(input, getStockList());
        }
      }
    } catch (FileNotFoundException e) {
      append("Portfolio doesn't exists");
      appendNewLine();
    } catch (IllegalArgumentException e) {
      append(e.getMessage());
    }
    return model;
  }

  private boolean viewSpeculateHelper(String fileName, ListOfStocksImpl listOfStocksImpl)
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


}
