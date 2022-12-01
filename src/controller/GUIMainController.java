package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import model.FileAccessors;
import model.FileAccessorsImpl;
import model.Portfolio;
import model.PortfolioImpl;
import model.StocksImpl;
import view.JFrameStocksView;

/**
 * Class that extends the controller and contains implementation of all the features.
 */
public class GUIMainController extends Controller implements Features {

  private Portfolio portfolioModel;

  private StocksImpl stockModel;
  private JFrameStocksView view;

  public GUIMainController() throws IOException {
    initialiseModel();

  }

  private void initialiseModel() throws IOException {
    portfolioModel = new PortfolioImpl();
    stockModel = new StocksImpl();
    view = new JFrameStocksView(this);
  }

  public void setView(JFrameStocksView v) {
    view = v;
  }

  public void programStartsHere() throws IOException, ParseException {
    view.displayStarterMenu();
  }

  private boolean checkIfFileExists(String filename) throws FileAlreadyExistsException {
    FileAccessors fileAccessorsImpl = new FileAccessorsImpl();
    if (!fileAccessorsImpl.isFileExists(filename, "portfolios/flexible")) {
      this.portfolioModel.setPortfolioName(filename);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Method that is used to check if a file name already exists or not.
   *
   * @param filename name of the file.
   * @return true if the file exists else false.
   * @throws IOException if the input is invalid.
   */
  public boolean createPortfolio(String filename) throws IOException {
    initialiseModel();
    boolean checkFile = checkIfFileExists(filename);
    return checkFile;
  }

  /**
   * Method that is used to validate the stocks data and check if it is valid or not.
   *
   * @param tickerName the ticker input.
   * @param date       date to be validated.
   * @param stocks     number of stocks.
   * @param fee        commission fee.
   * @return true if the stock data is valid else false.
   * @throws IOException if the input is invalid.
   */
  public boolean validateSingleStocksData(String tickerName, String date, int stocks, float fee)
      throws IOException {

    String tickerValue = tickerName.toUpperCase();
    updateListOfStocks(tickerValue);
    HashMap map = getStockList().getLStocksMap();
    ArrayList values = (ArrayList) map.get(tickerValue);
    return (values != null) && stockModel.isValidDate(date) && (stocks > 0) && (fee > 0);
  }

  /**
   * Method used to add stock stocks to the portfolio.
   *
   * @param tickerName name of the company.
   * @param date       date on which it is to be added.
   * @param stocks     number of stocks.
   * @param fee        commission fee.
   * @throws ParseException if the data is not parsable.
   */
  public void addStockToPortfolio(String tickerName, String date, int stocks, float fee)
      throws ParseException {
    StocksImpl currStock = stockModel.createAndAddStockInPortfolio(getStockList().getLStocksMap(),
        tickerName, date,
        stocks, fee);
    portfolioModel.addStockInPortfolio(currStock);

  }

  @Override
  public void saveCurrentPortfolio() {
    portfolioModel.save("portfolios/flexible");
  }

  @Override
  public HashMap<String, TreeMap<Date, StocksImpl>> getPortfolio() {
    return portfolioModel.getPortfolio();
  }

  @Override
  public boolean isValidateInputForMultiStocks(String stocksInput, float investedAmount,
      String weightage, float fee,
      String lowerDate, String upperDate, int frequency) throws ParseException, IOException {
    String[] tickerValuesList = stocksInput.split(",");
    for (String s : tickerValuesList) {
      updateListOfStocks(s.trim().toUpperCase());
      HashMap map = getStockList().getLStocksMap();
      ArrayList values = (ArrayList) map.get(s.trim().toUpperCase());
      if (values == null) {
        return false;
      }
    }
    return portfolioModel.validateInputForMultiStocks(investedAmount, weightage, fee, lowerDate,
        upperDate,
        frequency,
        tickerValuesList);
  }

  @Override
  public void addDollarCostAveragingStocks(String lowerDate, String upperDate, int frequency,
      String tickerValuesList, float valueInvested, String weightage, float fee)
      throws ParseException {
    String[] tickerList;
    if (tickerValuesList.length() > 0) {
      tickerList = tickerValuesList.split(",");
    } else {
      tickerList = null;
    }
    portfolioModel.addMultipleStocksInPortfolio(getStockList().getLStocksMap(), lowerDate,
        upperDate, frequency, tickerList, valueInvested, weightage, fee);
  }

  @Override
  public String[] getPortfolioNames() {
    return portfolioModel.getPortfolioNames();
  }

  @Override
  public HashMap<String, TreeMap<Date, StocksImpl>> renderTheSelectedPortfolio(String fileName)
      throws IOException, ParseException {
    upDateListOfStocksHelper(fileName);

    return portfolioModel.fetchSelectedPortfolio(fileName, getStockList().getLStocksMap());
  }

  @Override
  public HashMap getCompositionOfThePortfolio(String fileName, String date, boolean isCostBasis)
      throws ParseException, IOException {

    upDateListOfStocksHelper(fileName);
    portfolioModel.setIsCostBasis(isCostBasis);
    return portfolioModel.getCompostion(getStockList().getLStocksMap(), date);


  }

  /**
   * Method used to perform sell operation of the stocks.
   *
   * @param validDatesList        list of valid dates on which we can sell.
   * @param newDate               date on which we need to sell.
   * @param numberOfSellingStocks number of stocks to be sold.
   * @param fee                   commission fee for selling the stocks.
   * @return updated number of stocks after selling.
   */
  @Override
  public float sellTheStocks(TreeMap<Date, StocksImpl> validDatesList, String newDate,
      float numberOfSellingStocks, float fee) throws ParseException {
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
    float stocksSold = portfolioModel.sellTheStocks(validDatesList, sdformat.parse(newDate),
        numberOfSellingStocks,
        fee);
    portfolioModel.save("portfolios/flexible");
    System.out.println(portfolioModel.getPortfolio());
    return stocksSold;

  }

  /**
   * Method used to check if the stocks to be sold are valid or not.
   *
   * @param date   date on which it is to be sold.
   * @param shares number of shares.
   * @param fee    commission fee.
   * @return a value based on the validation.
   */
  @Override
  public int validateSellStocks(String date, float shares, float fee) {
    return portfolioModel.validateSellInputs(date, shares, fee);
  }

  /**
   * Method used to validate the date.
   *
   * @param date date to be validated.
   * @return true if the date is valid else false.
   */
  @Override
  public boolean isValidDate(String date) {
    return portfolioModel.isValidDate(date);
  }

  /**
   * Calculates the performance data for graph.
   *
   * @return data to be rendered in the data.
   */
  @Override
  public TreeMap<LocalDate, Integer> getPerformanceData(String date1, String date2)
      throws IOException, ParseException {
    TreeMap<LocalDate, Integer> performanceData = portfolioModel.calculatePerformaceOverTime(date1,
        date2);
    return performanceData;
  }

  @Override
  public boolean areValidPerformanceDate(String data1, String data2) {
    return portfolioModel.validatePerformanceDate(data1, data2);
  }

  private void upDateListOfStocksHelper(String filename) throws IOException, ParseException {
    FileAccessorsImpl fileAccessorsImpl = new FileAccessorsImpl();
    if (!fileAccessorsImpl.isFileExists(filename, "portfolios/flexible")) {
      throw new FileNotFoundException(filename);
    }

    HashMap<String, TreeMap<Date, StocksImpl>> portfolios = fileAccessorsImpl.viewFile(filename,
        "portfolios"
            + "/flexible");
    for (String s : portfolios.keySet()) {
      updateListOfStocks(s);
    }

    portfolioModel.setPortfolioName(filename);
    portfolioModel.setPortfolio(portfolios);
  }
}
