package controller;

import java.io.IOException;
import model.Portfolio;
import model.Stocks;
import view.PortfolioView;
import view.StockView;

public class PortfolioController extends MainController{


  private Portfolio model;
  private PortfolioView view;

  private Stocks stocksModel;

  public PortfolioController(Portfolio portfolio, PortfolioView portfolioView) throws IOException {
    super();
    this.model = portfolio;
    this.view = portfolioView;
  }

  public PortfolioController(Stocks model, Portfolio portfolio, PortfolioView view) throws IOException {
    super();
    this.stocksModel = model;
    this.model = portfolio;
    this.view = view;
  }

  public void addStock() throws IOException {
    model.addStockInPortfolio(stocksModel);
    this.out.append("Successfully added the stock in portfolio").append("\n");
    this.out.append("Draft portfolio!!!!!");
    view.displayCurrentPortfolio(model.getPortfolio());
    afterAddingStock(model);
  }

  public void afterAddingStock(Portfolio model) throws IOException {
    this.out.append("Successfully added the stock in portfolio").append("\n");
    int input = takeIntegerInput("Choose from below options.\n1."
        + " Add another stock\n2. Save this portfolio. (You can not edit it after saving!!!)\n3. "
        + "Back to main menu.\n4. Exit.");
    switch (input) {
      case 1:
        StockController stockController = new StockController(new Stocks(), new StockView());
        stockController.getTickerValue();
        break;
      case 2:
        // write it to a file
      case 3:
        go();
        break;
      case 4:
        System.exit(0);
      default:
        this.out.append("Invalid input");
        System.exit(0);
    }
  }

}
