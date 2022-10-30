import controller.PortfolioController;
import controller.StocksController;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Portfolio;
import model.Stocks;
import view.PortfolioView;
import view.StocksView;

public class MVCClass {

  public static void main(String[] args) {

//    Stocks MVC
    Stocks stocksModel = new Stocks();
    StocksView stocksView = new StocksView();
    StocksController stocksController = new StocksController(stocksModel, stocksView);

//    Portfolio MVC
    Portfolio portfolioModel = new Portfolio();
    PortfolioView portfolioView = new PortfolioView();
    PortfolioController createPortfolioController = new PortfolioController(portfolioModel,
        portfolioView);

    String yesNo = "NO";

    do {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter the ticker valid symbol.");
      String tickerValue = sc.nextLine().toString();
      boolean isValidData = stocksController.getTickerSymbol(tickerValue);
      if (isValidData) {
        System.out.println("Do you want add shares for this task?");
        yesNo = sc.nextLine().toString();

        if (yesNo.equals("YES")) {
          System.out.println("Please enter the number of shares.");
          try {
            Integer shares = sc.nextInt();
            stocksController.setSharesValue(shares);
            sc.nextLine();
          } catch (InputMismatchException e) {
            System.out.println("Please enter a valid integer, it should not contain any decimals.");
          }
        }
        // should be able to save
        System.out.println("Do you want to invest on this company?");
        yesNo = sc.nextLine().toString();
        if (yesNo.equals("YES")) {
          createPortfolioController.updateSavedStocks(stocksModel);
          System.out.println("Viewing the potfolio");
          createPortfolioController.viewPortfolio();
        }
      } else {
        System.out.println("The entered ticker symbol is not valid! Please try again.");
      }
      System.out.println();
      System.out.println("Do you want add a company? If so please type 'YES'.");

      yesNo = sc.nextLine().toString();
    } while (yesNo.equals("YES"));
  }

}
