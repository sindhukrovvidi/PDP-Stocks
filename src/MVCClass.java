import java.util.InputMismatchException;
import java.util.Scanner;

public class MVCClass {

  public static void main(String[] args) {

    System.out.println();
    Stocks model = new Stocks();

    StocksView view = new StocksView();

    StocksController controller = new StocksController(model, view);

    System.out.println("Enter the ticker symbol");
    String yesNo = "NO";

    do {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter the ticker valid value.");
      String tickerValue = sc.nextLine().toString();
      controller.getTickerSymbol(tickerValue);

      System.out.println("Do you want add shares for this task?");

      yesNo = sc.nextLine().toString();
      if (yesNo.equals("YES")) {
        System.out.println("Please enter the number of shares.");
        try {
          Integer shares = sc.nextInt();
          controller.setSharesValue(shares);
        } catch (InputMismatchException e) {
          System.out.println("Please enter a valid integer, it should not contain any decimals.");
        }


      }
      System.out.println("Do you want add one more company? If so please type 'YES'.");
      sc.nextLine();
      yesNo = sc.nextLine().toString();
    } while (yesNo.equals("YES"));
  }

}
