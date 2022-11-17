import java.nio.file.FileAlreadyExistsException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;
import model.Portfolio;
import model.StocksImpl;

public class MockPortfolioModel implements Portfolio {

  private StringBuilder log;

  private HashMap<String, TreeMap<Date, StocksImpl>> testReturn = new HashMap<>();

  public MockPortfolioModel(StringBuilder log) {
    this.log = log;
    TreeMap<Date, StocksImpl> treeMap = new TreeMap<>();
    treeMap.put(new Date(), new StocksImpl());
    testReturn.put("goog", treeMap);
  }
  @Override
  public void addStockInPortfolio(StocksImpl data) throws ParseException {
      log.append("Adds data to portfolio");
  }

  @Override
  public HashMap<String, TreeMap<Date, StocksImpl>> getPortfolio() {
    log.append("Return portfolio");
    return testReturn;
  }

  @Override
  public void setPortfolio(HashMap<String, TreeMap<Date, StocksImpl>> portfolio) {
    log.append("Set portfolio");
  }

  @Override
  public TreeMap getCompanyNames() {
    return (TreeMap) testReturn.values();
  }

  @Override
  public boolean isSaved() {
    return false;
  }

  @Override
  public void save(String path) {

  }

  @Override
  public void setPortfolioName(String name) throws FileAlreadyExistsException {

  }

  @Override
  public void setIsFlexible(boolean isFlexible) {

  }

  @Override
  public boolean getIsFlexible() {
    return false;
  }

  @Override
  public void setBuy(boolean buy) {

  }

  @Override
  public boolean getBuy() {
    return false;
  }

  @Override
  public void setIsCostBasis(boolean isCostBasis) {

  }

  @Override
  public boolean getIsCostBasis() {
    return false;
  }
}
