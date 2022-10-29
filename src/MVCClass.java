import controller.StocksController;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Stocks;
import view.StocksView;

public class MVCClass {

  public static void main(String[] args) {

    System.out.println();
    Stocks model = new Stocks();

    StocksView view = new StocksView();

    StocksController controller = new StocksController(model, view);

    String yesNo = "NO";

    do {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter the ticker valid symbol.");
      String tickerValue = sc.nextLine().toString();
      boolean isValidData = controller.getTickerSymbol(tickerValue);
      if (isValidData) {
        System.out.println("Do you want add shares for this task?");

        yesNo = sc.nextLine().toString();
        if (yesNo.equals("YES")) {
          System.out.println("Please enter the number of shares.");
          try {
            Integer shares = sc.nextInt();
            controller.setSharesValue(shares);
            sc.nextLine();
          } catch (InputMismatchException e) {
            System.out.println("Please enter a valid integer, it should not contain any decimals.");
          }


        }
      }
      System.out.println("The entered ticker symbol is not valid! Please try again.");

      System.out.println("Do you want add a company? If so please type 'YES'.");

      yesNo = sc.nextLine().toString();
    } while (yesNo.equals("YES"));
  }

}
