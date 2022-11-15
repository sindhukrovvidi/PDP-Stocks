package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import model.FileAccessorsImpl;
import model.ListOfStocksImpl;
import model.Portfolio;
import model.PortfolioImpl;
import model.StocksImpl;
import view.PortfolioViewImpl;

import static model.Input.takeIntegerInput;
import static model.Input.takeStringInput;
import static model.Output.append;
import static model.Output.appendNewLine;

public class FlexiblePortfolioControllerImpl extends PortfolioControllerImpl {

  public FlexiblePortfolioControllerImpl(StocksImpl stocksImpl, Portfolio portfolioImpl,
                                         PortfolioViewImpl
                                                 portfolioViewImpl) throws IOException {
    super(stocksImpl, portfolioImpl, portfolioViewImpl);
  }

  public FlexiblePortfolioControllerImpl(PortfolioImpl portfolioImpl, PortfolioViewImpl
          portfolioViewImpl) throws IOException {
    super(portfolioImpl, portfolioViewImpl);
  }

  @Override
  public Portfolio viewSpeculate(String input) throws IOException {
    try {
      FileAccessorsImpl fileAccessorsImpl = new FileAccessorsImpl();
      if (!fileAccessorsImpl.isFileExists(input, "portfolios/flexible")) {
        throw new FileNotFoundException(input);
      }

      HashMap<String, StocksImpl> portfolios = fileAccessorsImpl.viewFile(input, "portfolios" +
              "/flexible");
      for (String s : portfolios.keySet()) {
        updateListOfStocks(s);
      }
      model.setPortfolio(portfolios);
      model.setPortfolioName(input);
      controllerToViewHelper(portfolios);
      String currInput = takeStringInput("Would you like to speculate your " +
              "portfolio?(YES/NO)");
      if (currInput.equals("YES")) {
        speculateMenu();
      }

    } catch (FileNotFoundException e) {
      append("Portfolio doesn't exists");
      appendNewLine();
    } catch (IllegalArgumentException e) {
      append(e.getMessage());
    }
    return model;
  }

  public boolean speculateMenu() throws IOException {
    int input = takeIntegerInput("Choose from the below options:\n 1.Buy stocks.\n " +
            "2.Sell the stocks\n. 3.Get the total cost basis for the portfolio.\n 4.Get the " +
            "composition of the portfolio.\n 5.Get the portfolio performance over time.\n 6.Exit");
    switch (input) {
      case 1:

    }
    return false;
  }

}
