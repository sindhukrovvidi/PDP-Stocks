package view;

import java.util.HashMap;
import model.Portfolio;
import model.Stocks;

public class PortfolioView {

  public void displayPortfolio(HashMap currentData) {
    System.out.println("Inside display portfolio");
    for (Object i : currentData.keySet()) {
      Portfolio currentStock = (Portfolio) currentData.get(i);
      System.out.println(i + ": ");
      System.out.println("Date invested: " + currentStock.getDateInvested() + "... " + i);
      System.out.println("Price on that day: " + currentStock.getPriceOnThatDay() + "... " + i);
      System.out.println(
          "Number of shares invested: " + currentStock.getSharesInvested() + "... " + i);
      System.out.println("Cost according to date " + currentStock.getDateInvested() + ": "
          + currentStock.getTotalAmountInvested());
//    for (String j : i.) {
//      System.out.println(i);
//    }
    }

  }

}
