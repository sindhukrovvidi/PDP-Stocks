package controller;

import java.util.HashMap;
import model.Portfolio;
import model.Stocks;
import view.PortfolioView;

public class PortfolioController {

  private Portfolio model;
  private PortfolioView view;

  public PortfolioController(Portfolio model, PortfolioView view) {
    this.model = model;
    this.view = view;
  }

  public void updateSavedStocks(Stocks stocksModel) {
    model.saveStocksData(stocksModel.getCompany(), stocksModel.getSharesValue(),
        stocksModel.getPriceValue(), stocksModel.getLatestDay());
  }

  public void viewPortfolio() {
    view.displayPortfolio(model.getPortfolioData());
  }
}
