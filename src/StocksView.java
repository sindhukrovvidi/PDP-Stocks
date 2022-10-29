import java.sql.SQLOutput;

public class StocksView {
  public void displaySelectedStockDetails(String company, float open,float high, float low,
      float price, String date, float previousClose ) {
    System.out.println("Below are the details of the stock for company " + company );
    System.out.println("Open: " + open);
    System.out.println("High: " + high);
    System.out.println("Low: " + low);
    System.out.println("Price: " + price);
    System.out.println("Date: " + date);
    System.out.println("Previous Closing Value: " + previousClose + "\n\n");
  }

}
