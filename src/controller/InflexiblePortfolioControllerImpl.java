package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import model.FileAccessorsImpl;
import model.ListOfStocksImpl;
import model.Portfolio;
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

      HashMap<String, TreeMap<Date, StocksImpl>> portfolios = fileAccessorsImpl.viewFile(input,
          "portfolios" +
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
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
    return model;
  }

  private boolean viewSpeculateHelper(String fileName, ListOfStocksImpl listOfStocksImpl)
      throws IOException {
    Map.Entry<String, ArrayList<StocksImpl>> entry = (Map.Entry<String, ArrayList<StocksImpl>>)
        listOfStocksImpl.getLStocksMap()
            .entrySet().iterator().next();
    AtomicReference<ArrayList<StocksImpl>> currentStock = new AtomicReference<>(
        (ArrayList<StocksImpl>) listOfStocksImpl.getLStocksMap()
            .get(entry.getKey()));
    String firstStockDate = currentStock.get().get(0).getDate();
    String lastStockDate = currentStock.get().get(currentStock.get().size() - 1).getDate();
    String input =
        takeStringInput("Enter the date between " + lastStockDate + " and "
            + firstStockDate);
    AtomicReference<Float> total_value = new AtomicReference<>((float) 0);

    HashMap<String, TreeMap<Date, StocksImpl>> stocksData = model.getPortfolio();
    for (Entry<String, TreeMap<Date, StocksImpl>> e : stocksData.entrySet()) {
      String k = e.getKey();
      TreeMap<Date, StocksImpl> v = e.getValue();
      AtomicBoolean dateExist = new AtomicBoolean(false);
      v.forEach((key, val) -> {
        currentStock.set((ArrayList<StocksImpl>) listOfStocksImpl.getLStocksMap()
            .get(val.getCompany()));

        for (StocksImpl stock : currentStock.get()) {
          if (stock.getDate().equals(input)) {
            dateExist.set(true);
            total_value.updateAndGet(
                v1 -> v1 + stock.getClose() * val.getShares());
          }

        }
      });
      if (!dateExist.get()) {
        append("The entered date does not exist, please enter a valid date.\n");
        return false;
      }
    }
    append("Total price of portfolio is " + String.valueOf(total_value.get()) + ".\n");
    return true;
  }


}
