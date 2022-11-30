package model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import org.junit.Test;

/**
 * Tests for portfolio.
 */
public class PortfolioImplTest {

  PortfolioImpl portfolio = new PortfolioImpl();


  @Test
  public void costBasisTest() throws IOException, ParseException {
    ListOfStocks listOfStocks = new ListOfStocksImpl();
    HashMap list = listOfStocks.getLStocksMap();
    list.put("GOOG", new StocksImpl("2022-11-14", 7, 7, 7, 7, 7, 8));
    HashMap expectedData = portfolio.getCompostion(list, "2022-11-14");
    assertEquals("80", expectedData.get("total_composition"));
    assertEquals("10", expectedData.get("total_comission_fee"));
  }


  @Test
  public void inValidCostBasisTest() throws IOException, ParseException {
    ListOfStocks listOfStocks = new ListOfStocksImpl();
    HashMap list = listOfStocks.getLStocksMap();
    list.put("GOOG", new StocksImpl("2022-11-29", 4, 7, 57, 7, 7, 8));
    HashMap expectedData = portfolio.getCompostion(list, "2022-11-14");
    assertEquals("", expectedData.get("total_composition"));
    assertEquals("", expectedData.get("total_comission_fee"));
  }


}