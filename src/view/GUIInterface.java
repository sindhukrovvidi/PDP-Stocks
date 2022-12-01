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

  /**
   * Displays the starter menu of the UI.
   */

  void displayStarterMenu();

  /**
   * Function to sell the specified stocks.
   */
  void sellStocks();

  /**
   * Renders the GUI part of displaying the composition of the portfolio.
   *
   * @param isCostBasis true if user selects to view cost basis.
   */
  void calculateComposition(boolean isCostBasis);

  /**
   * Renders the view portfolio frame.
   */
  void viewPortfolioFrame();

  /**
   * Helper funtion to generate the dropdown.
   *
   * @param dropDown from different frames to render the inputs.
   * @param items    list of items to display in drop down.
   */
  void generateDropDown(DefaultComboBoxModel dropDown, String[] items);

  /**
   * Views the create portfolio frame.
   *
   * @throws IOException while reading a file.
   */
  void createPortfolioFile() throws IOException;

  /**
   * Helper to enter the ticker value.
   */
  void enterTheTickerValue();

  /**
   * Renders the frame after selecting a stock.
   */
  void callAddAnotherStock();

  /**
   * Populates the table form array list.
   *
   * @param stockList list of data to be rendered in the table.
   * @param model     table model to which the data has to be attached.
   */
  void populateTableFromArrayList(ArrayList<StocksImpl> stockList, DefaultTableModel model);

  /**
   * Generates the table from hashmap.
   *
   * @param portfolio hashmap data.
   * @param model     table model to which the data has to be attached.
   */
  void populateTable(HashMap<String, TreeMap<Date, StocksImpl>> portfolio, DefaultTableModel model);

  /**
   * Populates the table from treemap.
   *
   * @param portfolio treemap data.
   * @param model     table model to which the data has to be attached.
   */
  void populateTableFromTreeMap(TreeMap<Date, StocksImpl> portfolio, DefaultTableModel model);

  /**
   * Frame to view the portfolio.
   */
  void viewPortfolio();
}
