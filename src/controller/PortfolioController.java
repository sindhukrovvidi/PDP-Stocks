package controller;

import java.io.IOException;

import model.FileAccessors;
import model.Portfolio;
import model.Stocks;
import view.PortfolioView;
import view.StockView;

import static model.Input.takeIntegerInput;
import static model.Output.append;
import static model.Output.appendNewLine;

public class PortfolioController extends MainController {


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

  public Portfolio addStock() throws IOException {
    model.addStockInPortfolio(stocksModel);
//    FileAccessors files =  new FileAccessors();
//    files.writeIntoCSVFile(fileName);
    append("Successfully added the stock in portfolio");
    appendNewLine();
    append("Draft portfolio!!!!!");
    view.displayCurrentPortfolio(model.getPortfolio());
    return afterAddingStock(model);
   // return model;
  }

  public Portfolio afterAddingStock(Portfolio model) throws IOException {
    append("Successfully added the stock in portfolio");
    appendNewLine();
    int input = takeIntegerInput("Choose from below options.\n 1."
            + " Add another stock\n2. Save this portfolio. (You can not edit it after saving!!!)\n3. "
            + "Back to main menu.\n4. Exit.");
    switch (input) {
      case 1:

        break;
      case 2:
        // write it to a file
      case 3:
        model = null;
        break;
      case 4:
        System.exit(0);
      default:
        append("Invalid input");
        System.exit(0);
    }
    return model;
  }

}
