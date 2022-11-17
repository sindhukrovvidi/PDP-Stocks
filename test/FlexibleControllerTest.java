import controller.PortfolioControllerImpl;
import java.io.IOException;
import model.Portfolio;
import model.PortfolioImpl;
import model.StocksImpl;
import org.junit.Test;
import view.PortfolioViewImpl;

import static org.junit.Assert.assertEquals;


public class FlexibleControllerTest {

  public class FlexiblePortfolioControllerImplTest extends PortfolioControllerImpl {

    private StringBuilder log;

    public FlexiblePortfolioControllerImplTest(StocksImpl stocksImpl, Portfolio portfolioImpl,
        PortfolioViewImpl
            portfolioViewImpl) throws IOException {
      super(stocksImpl, portfolioImpl, portfolioViewImpl);
    }

    public FlexiblePortfolioControllerImplTest(PortfolioImpl portfolioImpl, PortfolioViewImpl
        portfolioViewImpl) throws IOException {
      super(portfolioImpl, portfolioViewImpl);
    }

    @Override
    public Portfolio viewSpeculate(String input) throws IOException {
      return null;
    }
  }

  @Test
  public void testBuyStocksForParticularDate() {
    StringBuilder modelLog = new StringBuilder();
  }
}
