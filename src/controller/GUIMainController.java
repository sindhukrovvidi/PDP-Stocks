package controller;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.text.ParseException;
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

public class GUIMainController extends Controller implements Features {

  private Portfolio portfolioModel;

  private StocksImpl stockModel;

  private PortfolioController portfolioController;
  private JFrameStocksView view;

  public GUIMainController() throws IOException {
//    super();
    portfolioModel = new PortfolioImpl();
    stockModel = new StocksImpl();
    view = new JFrameStocksView(this);
    portfolioController = new FlexiblePortfolioControllerImpl(stockModel, portfolioModel, view);

  }

  public void setView(JFrameStocksView v) {
    view = v;
    //provide view with all the callbacks
    view.addFeatures(this);
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

  public boolean createPortfolio(String filename) throws FileAlreadyExistsException {
    boolean checkFile = checkIfFileExists(filename);
    return checkFile;
  }

  public boolean validateSingleStocksData(String tickerName, String date, int stocks, float fee)
      throws IOException {

    String tickerValue = tickerName.toUpperCase();
    updateListOfStocks(tickerValue);
    HashMap map = getStockList().getLStocksMap();
    ArrayList values = (ArrayList) map.get(tickerValue);
    if (!(values == null) && stockModel.isValidDate(date) && (stocks > 0) && (fee > 0)) {
      return true;
    }
    return false;
  }

  public void addStockToPortfolio(String tickerName, String date, int stocks, float fee)
      throws ParseException {
    StocksImpl currStock = stockModel.createAndAddStockInPortfolio(getStockList().getLStocksMap(),
        tickerName, date,
        stocks, fee);
    portfolioModel.addStockInPortfolio(currStock);
//    System.out.println(portfolioModel.getPortfolio());
//    portfolioModel.addStockInPortfolio();

  }

  @Override
  public void saveCurrentPortfolio() {
//    portfolioModel.saveGUIStocks();
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
        return false;//        return null;
      }
    }
    return portfolioModel.validateInputForMultiStocks(investedAmount, weightage, fee, lowerDate,
        upperDate,
        frequency);
//    return false;
  }

  @Override
  public void addDollarCostAveragingStocks(String lowerDate, String upperDate, int frequency,
      String tickerValuesList, float valueInvested, String weightage, float fee)
      throws ParseException {
//    if(tickerValuesList not null)
    String[] tickerList;
    if (tickerValuesList.length() > 0) {
      tickerList = tickerValuesList.split(",");
    } else {
      tickerList = null;
    }
    portfolioModel.addMultipleStocksInPortfolio(getStockList().getLStocksMap(), lowerDate,
        upperDate, frequency, tickerList, valueInvested, weightage, fee);
  }

//  @Override
//  public HashMap<String, TreeMap<Date, StocksImpl>> viewPortfolio() {
//      return portfolioModel.getPortfolio();
//  }

}
