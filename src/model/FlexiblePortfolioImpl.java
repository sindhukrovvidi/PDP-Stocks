package model;

import java.nio.file.FileAlreadyExistsException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;
import java.text.SimpleDateFormat;

public class FlexiblePortfolioImpl {

  HashMap<String, TreeMap<Date, StocksImpl>> entriesInPortfolio = new HashMap<>();

  /**
   * Method used to add stock to the portfolio.
   *
   * @param data data to be added.
   */
  public void addStockInPortfolio(StocksImpl data) throws ParseException {
    String company = data.getCompany();
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
    TreeMap<Date, StocksImpl> currStockData = new TreeMap<>();
    Date newDate = sdformat.parse(data.getDate());

    if (entriesInPortfolio.containsKey(company)) {
      currStockData = entriesInPortfolio.get(company);

      if (currStockData.containsKey(newDate)) {
        StocksImpl currStock = (entriesInPortfolio.get(company)).get(newDate);
        data.updateStockValues(currStock.getShares() + data.getShares());
        currStockData.put(newDate, data);
      } else {
        (entriesInPortfolio.get(company)).put(newDate, data);
      }
    } else {
      currStockData.put(newDate, data);
      entriesInPortfolio.put(data.getCompany(), currStockData);
    }
  }

  public void testWritingInFile() {
    entriesInPortfolio.forEach((k, v) -> {
      TreeMap<Date, StocksImpl> currStockData = v;
      v.forEach((key, value) -> {
        // enter data here
      });
    });
  }

  public ArrayList<TreeMap<Date, StocksImpl>> getValues() {
    return (ArrayList<TreeMap<Date, StocksImpl>>) entriesInPortfolio.values();
  }

  public HashMap<String, TreeMap<Date, StocksImpl>> getPortfolio() {
    return entriesInPortfolio;
  }

  public void setPortfolio(HashMap<String, TreeMap<Date, StocksImpl>> portfolio) {
    this.entriesInPortfolio = portfolio;
  }

  /**
   * Method used to get portfolio.
   *
   * @return portfolio.
   */
//    @Override
//    public HashMap<String, StocksImpl> getPortfolio() {
//      return entriesInPortfolio;
//    }

  /**
   * Method to create a portfolio.
   *
   * @param portfolio mapping of the portfolio.
   */
//    @Override
//    public void setPortfolio(HashMap<String, StocksImpl> portfolio) {
//      this.entriesInPortfolio = portfolio;
//
//    }

  /**
   * Method used to get company name.
   *
   * @return the list of company names.
   */
//    @Override
//    public ArrayList<StocksImpl> getCompanyNames() {
//      return new ArrayList<StocksImpl>(entriesInPortfolio.values());
//    }

  /**
   * Method used to check if file is saved or not.
   *
   * @return checks whether the file is saved or not.
   */
//    @Override
//    public boolean isSaved() {
//      return saved;
//    }

  /**
   * Readers and saves the portfolio in file.
   */
//    @Override
//    public void save(String path) {
//      fileAccessor.writeIntoCSVFile(portfolioName, entriesInPortfolio, path);
//      this.saved = true;
//    }

  /**
   * Method used to check if the file is already created.
   *
   * @param name sets the name of the portfolio.
   * @throws FileAlreadyExistsException file doesn't exist.
   */
//    @Override
//    public void setPortfolioName(String name) throws FileAlreadyExistsException {
//      portfolioName = name;
//    }

//    @Override
//    public void setIsFlexible(boolean isFlexible) {
//      this.isFlexible = isFlexible;
//    }

//    @Override
//    public boolean getIsFlexible() {
//      return isFlexible;
//    }

}


