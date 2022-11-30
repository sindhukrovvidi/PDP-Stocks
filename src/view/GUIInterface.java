package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import model.StocksImpl;

/**
 * Interface that contains all the methods that are responsible for the graphic user interface.
 */
public interface GUIInterface {

  void displayStarterMenu();

  void sellStocks();

  void calculateComposition(boolean isCostBasis);

  void viewPortfolioFrame();

  void generateDropDown(DefaultComboBoxModel dropDown, String[] items);

  void createPortfolioFile() throws IOException;

  void enterTheTickerValue();

  void callAddAnotherStock();

  void populateTableFromArrayList(ArrayList<StocksImpl> stockList, DefaultTableModel model);

  void populateTable(HashMap<String, TreeMap<Date, StocksImpl>> portfolio, DefaultTableModel model);

  void populateTableFromTreeMap(TreeMap<Date, StocksImpl> portfolio, DefaultTableModel model);

  void viewPortfolio();
}
