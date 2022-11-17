import controller.FlexibleStockControllerImpl;
import controller.MainController;
import controller.MainControllerImpl;
import controller.PortfolioControllerImpl;
import java.io.IOException;
import java.text.ParseException;
import model.Portfolio;
import model.PortfolioImpl;
import model.StocksImpl;
import org.junit.Test;
import view.PortfolioViewImpl;
import view.StockViewImpl;

import static org.junit.Assert.assertEquals;


public class FlexibleControllerTest {

  @Test
  public void testPurchaseStocks() throws IOException, ParseException {
    StringBuilder log = new StringBuilder(); //log for mock model

    MainController mainController = new MainControllerImpl();
    mainController.programStartsHere();
    MockStocksModel stocksController = new FlexibleStockControllerImpl(new MockStocksModel(log),
        new StockViewImpl());

  }

  @Test
  public void test
}
