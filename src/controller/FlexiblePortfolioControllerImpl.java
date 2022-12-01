package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;

import model.FileAccessorsImpl;
import model.Portfolio;
import model.PortfolioImpl;
import model.StocksImpl;
import view.GUIInterface;
import view.StockView;

import static model.Input.takeFloatInput;
import static model.Input.takeIntegerInput;
import static model.Input.takeStringInput;
import static model.Output.append;
import static model.Output.appendNewLine;

/**
 * Class that contains all operations that are specific to the flexible portfolio and extends the
 * abstract class PortfolioControllerImpl.
 */
public class FlexiblePortfolioControllerImpl extends PortfolioControllerImpl {

  /**
   * Constructor that takes the parameters and initialize them.
   *
   * @param stocksImpl    list of stocks.
   * @param portfolioImpl portfolio.
   * @param view          view of the portfolio.
   * @throws IOException when the data is invalid.
   */
  public FlexiblePortfolioControllerImpl(StocksImpl stocksImpl, Portfolio portfolioImpl,
      StockView view) throws IOException {
    super(stocksImpl, portfolioImpl, view);
  }

  public FlexiblePortfolioControllerImpl(StocksImpl stocksImpl, Portfolio portfolioImpl,
      GUIInterface view) throws IOException {
    super(stocksImpl, portfolioImpl, view);
  }

  /**
   * Constructor that takes the parameters and initialize them.
   *
   * @param portfolioImpl     object for portfolioImpl.
   * @param portfolioViewImpl view for portfolio.
   * @throws IOException given invalid data.
   */
  public FlexiblePortfolioControllerImpl(PortfolioImpl portfolioImpl, StockView
      portfolioViewImpl) throws IOException {
    super(portfolioImpl, portfolioViewImpl);
  }

  /**
   * COntructor to initialise the view and stock, portfolio models.
   *
   * @param stocksImpl        stock modal.
   * @param portfolioImpl     portfolio modal.
   * @param portfolioViewImpl portfolio view modal.
   * @param controller        stock controller.
   * @throws IOException when the data is invalid.
   */
  public FlexiblePortfolioControllerImpl(StocksImpl stocksImpl, Portfolio portfolioImpl,
      StockView portfolioViewImpl, StockController controller)
      throws IOException {
    super(stocksImpl, portfolioImpl, portfolioViewImpl, controller);
  }

  /**
   * Method used to view and speculate a flexible portfolio.
   *
   * @param input file name.
   * @return the chosen portfolio.
   * @throws IOException given invalid data.
   */
  @Override
  public Portfolio viewSpeculate(String input) throws IOException {
    try {
      FileAccessorsImpl fileAccessorsImpl = new FileAccessorsImpl();
      if (!fileAccessorsImpl.isFileExists(input, "portfolios/flexible")) {
        throw new FileNotFoundException(input);
      }

      HashMap<String, TreeMap<Date, StocksImpl>> portfolios = fileAccessorsImpl.viewFile(input,
          "portfolios"
              + "/flexible");
      for (String s : portfolios.keySet()) {
        updateListOfStocks(s);
      }

      SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");

      HashMap stockMap = getStockList().getLStocksMap();

      portfolios.forEach((tickerValue, data) -> {
        TreeMap<Date, StocksImpl> currData = data;
        data.forEach((date, stock) -> {
          boolean isFuture = stock.getIsFuture();
          String stockDate = stock.getDate();
          Date newDate1 = null;

          try {
            newDate1 = sdformat.parse(stockDate);
          } catch (ParseException e) {
            throw new RuntimeException(e);
          }

          LocalDate dateLower = newDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
          if (isFuture && dateLower.compareTo(LocalDate.now()) <= 0) {
            ArrayList currentTickerData = (ArrayList) stockMap.get(tickerValue);
            for (Object currentTickerDatum : currentTickerData) {
              StocksImpl curStock = (StocksImpl) currentTickerDatum;
              String curStockDate = curStock.getDate();
              if (Objects.equals(curStockDate, dateLower.toString())) {
                currData.put(newDate1, new StocksImpl(
                    tickerValue,
                    curStockDate,
                    curStock.getOpen(),
                    curStock.getHigh(),
                    curStock.getLow(),
                    curStock.getClose(),
                    curStock.getVolume(),
                    stock.getPercentage() / curStock.getClose(),
                    curStock.getCommisionFee(),
                    stock.getPercentage(),
                    false
                ));
              }
            }
          }
        });
        portfolios.put(tickerValue, currData);
      });

      model.setPortfolioName(input);
      model.setPortfolio(portfolios);
      model.save("portfolios/flexible");

      controllerToViewHelper(portfolios);

      view.isSpeculateMenu();
      String currInput = takeStringInput();
      if (currInput.equals("YES")) {
        model = speculateMenu();
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

  /**
   * Method used to display the speculation menu for the flexible portfolio.
   *
   * @return the required portfolio.
   * @throws IOException    given invalid data.
   * @throws ParseException invalid date.
   */
  public Portfolio speculateMenu() throws IOException, ParseException {
    view.displayFlexibleViewMenu();
    int input = takeIntegerInput();
    switch (input) {
      case 1:
        model.setBuy(true);
        break;
      case 2:
        sellTheStocks();
        break;
      case 3:
        model.setIsCostBasis(true);
        getCompositionOfPortfolio();
        break;
      case 4:
        model.setIsCostBasis(false);
        getCompositionOfPortfolio();
        break;
      case 5:
        performanceOverTime();
        break;
      case 6:
        System.exit(0);
        break;
      default:
        /* no default case. */
    }
    return model;
  }

  /**
   * Method that is responsible to perform the sell operations on the stocks.
   *
   * @return model that has updated stocks after selling the stocks.
   * @throws IOException    when the data is invalid.
   * @throws ParseException when the data is not parsable.
   */
  public Portfolio sellTheStocks() throws IOException, ParseException {
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");

    view.enterTheTickerValue();
    String tickerValue = takeStringInput();

    HashMap<String, TreeMap<Date, StocksImpl>> checklist = model.getPortfolio();
    if (checklist.containsKey(tickerValue)) {
      viewDatesByCompany(checklist, tickerValue);

      view.enterTheDate();
      String date = takeStringInput();

      view.enterTheStocksToSell();
      int numberOfSellingStocks = takeIntegerInput();

      view.enterTheCommissionFee();
      float fee = takeFloatInput();

      if (numberOfSellingStocks <= 0 || fee <= 0) {
        view.printSellErrorMessage();
        speculateMenu();
      }

      float stocksSold = model.sellTheStocks(checklist.get(tickerValue), sdformat.parse(date),
          numberOfSellingStocks, fee);

      if (stocksSold != 0) {
        view.printLackStocks(stocksSold);
      }
      model.save("portfolios/flexible");
    } else {
      view.printInvalidTicker();
      sellTheStocks();
    }

    return model;
  }

  private void performanceOverTime() throws IOException, ParseException {
    view.enterLowerLimitDate();
    String date1 = takeStringInput();

    view.enterUpperLimitDate();
    String date2 = takeStringInput();

    TreeMap<LocalDate, Integer> barData = model.calculatePerformaceOverTime(date1, date2);
    String performanceData = model.getScaleValue(barData, date1, date2);

    if (performanceData == null) {
      view.performanceDateErrorMessage();
      performanceOverTime();
    } else {
      view.displayThePerformance(performanceData);
    }
  }

  private void viewDatesByCompany(HashMap<String, TreeMap<Date, StocksImpl>> portfolioEntries,
      String company) {
    AtomicBoolean displayHeaders = new AtomicBoolean(true);
    TreeMap<Date, StocksImpl> v = portfolioEntries.get(company);
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
  }

  /**
   * Method used to compute the composition of a desired portfolio.
   *
   * @throws IOException    invalid data.
   * @throws ParseException invalid date.
   */
  public void getCompositionOfPortfolio() throws IOException, ParseException {
    view.enterDateforComposition();
    String input = takeStringInput();

    HashMap currData = model.getCompostion(getStockList().getLStocksMap(), input);

    if (currData == null) {
      view.greaterDateMessage();
    } else {
      AtomicBoolean displayHeaders = new AtomicBoolean(true);
      ArrayList<StocksImpl> inDateList = (ArrayList<StocksImpl>) currData.get("inDateList");
      float final_total_value = (float) currData.get("final_total_value");
      if (inDateList.size() == 0) {
        view.zeroComposition();
      } else {
        inDateList.forEach(val -> {
          try {
            view.viewCompositionOfPortfolio(displayHeaders.get(), val.getCompany(), val.getDate(),
                val.getOpen(),
                val.getClose(), val.getShares(), val.getCommisionFee(), final_total_value,
                input);
            displayHeaders.set(false);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        });
      }
    }
  }
}

