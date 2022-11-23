package model;

import org.junit.Test;

import java.nio.file.FileAlreadyExistsException;
import java.text.ParseException;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;

import view.PortfolioView;
import view.PortfolioViewImpl;

import static org.junit.Assert.*;

public class PortfolioTest {

  Portfolio portfolio = new PortfolioImpl();
  StocksImpl s = new StocksImpl("2022-11-16", 121, 23, 23, 34, 45,
      9);

  StocksImpl s1 = new StocksImpl("2022-11-16", 111, 323, 23, 254,
      25, 3);

  StocksImpl s2 = new StocksImpl("2022-11-16", 168, 723, 23, 334,
      85, 1);

  StocksImpl s3 = new StocksImpl("2022-11-16", 78, 123, 23, 94,
      65, 2);

  HashMap<String, TreeMap<Date, StocksImpl>> entriesInPortfolio = new HashMap<>();
  TreeMap<Date, StocksImpl> treeMaps = new TreeMap<>();
  boolean saved = true;
  private String portfolioName = "flex1";
  boolean isFlexible = false;

  private final FileAccessorsImpl fileAccessor = new FileAccessorsImpl();
  PortfolioViewImpl view = new PortfolioViewImpl();
  private boolean buy = true;

  private boolean isCostBasis = false;

  @Test
  public void testCreateInFlexiblePortfolio() throws ParseException, FileAlreadyExistsException {
    s.setCurrentStock("GOOGGLE INC", "2022-11-16", 121, 23, 23, 34,
        45, 2, 0);
    s1.setCurrentStock("Kaggle", "2022-11-16", 121, 23, 23, 34,
        45, 4, 0);
    treeMaps.put(new Date(), s);
    entriesInPortfolio.put(portfolioName, treeMaps);
    portfolio.addStockInPortfolio(s);
    portfolio.addStockInPortfolio(s1);
    portfolio.setBuy(buy);
    portfolio.setIsFlexible(isFlexible);
    portfolio.setPortfolioName(portfolioName);
    portfolio.setIsCostBasis(isCostBasis);
    portfolio.save("portfolios/inflexible");
    HashMap<String, TreeMap<Date, StocksImpl>> entries = portfolio.getPortfolio();
    StringBuilder result = new StringBuilder();
    AtomicBoolean displayHeaders = new AtomicBoolean(true);
    Formatter fmt = new Formatter();

    fmt.format("%15s %15s %15s %15s %15s %15s %15s %15s %15s %15s\n", "Company", "Date", "Open",
        "High", "Low",
        "Close", "Volume", "Shares Invested", "Total Value", "Commission fee"
            + "\n");
    //this.out.append(fmt.toString());
    entries.forEach((k, v) -> {
      v.forEach((key, value) -> {

        fmt.format("%15s %15s %15s %15s %15s %15s %15s %15s %15s %15s\n",
            value.getCompany(),
            value.getDate(),
            value.getOpen(), value.getHigh(), value.getLow(),
            value.getClose(), value.getVolume(), value.getShares(),
            value.getShares() * value.getClose(),
            value.getCommisionFee());
        displayHeaders.set(false);
      });
    });
    assertEquals("        Company            Date            Open          " +
            "  High           " +
            "  Low           Close          Volume Shares Invested     " +
            "Total Value Commission fee\n" +
            "\n" +
            "    GOOGGLE INC      2022-11-16           121.0            23.0          " +
            "  23.0         " +
            "   34.0            45.0               2            68.0             0.0\n" +
            "         Kaggle      2022-11-16           121.0            23.0           " +
            " 23.0         " +
            "   34.0            45.0               4           136.0             0.0\n",
        fmt.toString());
  }


  @Test
  public void testCreateFlexiblePortfolio() throws ParseException, FileAlreadyExistsException {
    s2.setCurrentStock("GOOGGLE INC", "2022-11-16", 121, 23, 23, 34,
        45, 2, 0);
    s3.setCurrentStock("GOOGGLE INC", "2022-11-12", 121, 23, 23, 34,
        45, 4, 0);
    treeMaps.put(new Date(), s2);
    entriesInPortfolio.put(portfolioName, treeMaps);
    portfolio.addStockInPortfolio(s2);
    portfolio.addStockInPortfolio(s3);
    portfolio.setBuy(buy);
    portfolio.setIsFlexible(isFlexible);
    portfolio.setPortfolioName(portfolioName);
    portfolio.setIsCostBasis(isCostBasis);
    portfolio.save("portfolios/inflexible");
    HashMap<String, TreeMap<Date, StocksImpl>> entries = portfolio.getPortfolio();
    StringBuilder result = new StringBuilder();
    AtomicBoolean displayHeaders = new AtomicBoolean(true);
    Formatter fmt = new Formatter();

    fmt.format("%15s %15s %15s %15s %15s %15s %15s %15s %15s %15s\n", "Company", "Date", "Open",
        "High", "Low",
        "Close", "Volume", "Shares Invested", "Total Value", "Commission fee"
            + "\n");
    //this.out.append(fmt.toString());
    entries.forEach((k, v) -> {
      v.forEach((key, value) -> {

        fmt.format("%15s %15s %15s %15s %15s %15s %15s %15s %15s %15s\n",
            value.getCompany(),
            value.getDate(),
            value.getOpen(), value.getHigh(), value.getLow(),
            value.getClose(), value.getVolume(), value.getShares(),
            value.getShares() * value.getClose(),
            value.getCommisionFee());
        displayHeaders.set(false);
      });
    });
    assertEquals("        Company            Date            Open            High       " +
            "      Low           Close          Volume Shares Invested  " +
            "   Total Value Commission fee\n" +
            "\n" +
            "    GOOGGLE INC      2022-11-12           121.0            23.0          " +
            "  23.0            34.0            45.0               4           136.0       "
            + "      0.0\n" +
            "    GOOGGLE INC      2022-11-16           121.0            23.0           " +
            " 23.0            34.0            45.0               2            68.0    " +
            "         0.0\n",
        fmt.toString());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNegativeCommission() throws ParseException, FileAlreadyExistsException {
    s2.setCurrentStock("GOOGGLE INC", "2022-11-16", 121, 23, 23, 34,
        45, 2, -23);
    s3.setCurrentStock("GOOGGLE INC", "2022-11-12", 121, 23, 23, 34,
        45, 4, 0);
    treeMaps.put(new Date(), s2);
    entriesInPortfolio.put(portfolioName, treeMaps);
    portfolio.addStockInPortfolio(s2);
    portfolio.addStockInPortfolio(s3);
    portfolio.setBuy(buy);
    portfolio.setIsFlexible(isFlexible);
    portfolio.setPortfolioName(portfolioName);
    portfolio.setIsCostBasis(isCostBasis);
    HashMap<String, TreeMap<Date, StocksImpl>> entries = portfolio.getPortfolio();

  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDateFormat() throws ParseException, FileAlreadyExistsException {
    s2.setCurrentStock("GOOGGLE INC", "11-2022-16", 121, 23, 23, 34,
        45, 2, -23);
    s3.setCurrentStock("GOOGGLE INC", "2022-11-12", 121, 23, 23, 34,
        45, 4, 0);
    treeMaps.put(new Date(), s2);
    entriesInPortfolio.put(portfolioName, treeMaps);
    portfolio.addStockInPortfolio(s2);
    portfolio.addStockInPortfolio(s3);
    portfolio.setBuy(buy);
    portfolio.setIsFlexible(isFlexible);
    portfolio.setPortfolioName(portfolioName);
    portfolio.setIsCostBasis(isCostBasis);
    HashMap<String, TreeMap<Date, StocksImpl>> entries = portfolio.getPortfolio();

  }

}