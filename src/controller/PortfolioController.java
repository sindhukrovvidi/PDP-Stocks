package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.FileAccessors;
import model.Portfolio;
import model.Stocks;
import model.StocksList;
import view.PortfolioView;
import view.StockView;

import static model.Input.takeIntegerInput;
import static model.Input.takeStringInput;
import static model.Output.append;
import static model.Output.appendNewLine;

public class PortfolioController {


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
        model.save();
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

  public void viewSpeculate(String input, StocksList stocksList) throws IOException {
    try {
      FileAccessors fileAccessors = new FileAccessors();
      if (!fileAccessors.isFileExists(input)) {
        throw new FileNotFoundException(input);
      }

      model.setPortfolio(view.displaySavedPortfolio(input));
      String currInput = takeStringInput("Would you like to speculate your portfolio?(YES/NO)");
      if (currInput.equals("YES")) {
        boolean isValidDate = viewSpeculateHelper(input, stocksList);
        if (!isValidDate) {
          isValidDate = viewSpeculateHelper(input, stocksList);
        }
      }
//      else {
//        go();
//      }

    } catch (FileNotFoundException e) {
      append("Portfolio doesn't exists");
      appendNewLine();
    } catch (IllegalArgumentException e) {
      append(e.getMessage());
      viewSpeculate(input, stocksList);
    }
  }

  public boolean viewSpeculateHelper(String fileName, StocksList stocksList) throws IOException {
    Map.Entry<String, ArrayList<Stocks>> entry = (Map.Entry<String, ArrayList<Stocks>>) stocksList.getMap().entrySet().iterator().next();
    ArrayList<Stocks> currentStock = (ArrayList<Stocks>) stocksList.getMap().get(entry.getKey());
    String firstStockDate = currentStock.get(0).getDate();
    String lastStockDate = currentStock.get(currentStock.size() - 1).getDate();
    String input =
            takeStringInput("Enter the date between " + firstStockDate + " and " + lastStockDate);
    float total_value = 0;

    for (Stocks stocks : model.getCompanyNames()) {
      currentStock = (ArrayList<Stocks>) stocksList.getMap().get(stocks.getCompany());
      boolean dateExist = false;
      for (Stocks stock : currentStock) {
        if (stock.getDate().equals(input)) {
          dateExist = true;
          total_value += stock.getClose() * stocks.getShares();
        }

      }
      if (!dateExist) {
        append("The entered date does not exist, please enter a valid date.\n");
        return false;
//        viewSpeculateHelper(stocksList);
//        break;
      }
    }
    append("Total price of portfolio is " + String.valueOf(total_value) + ".\n");
    return true;
  }

}
