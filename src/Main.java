import controller.MainControllerImpl;
import java.io.IOException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * Main class which starts the program.
 */
public class Main {

  /**
   * Initialises the main controller.
   *
   * @param args arguments.
   * @throws IOException invalid input.
   */

  private String apiKey = "CAAPHOM3N94LAFJL";
  //  private String stockSymbol = "GOOG";
  private URL url = null;
  public static void main(String[] args) throws IOException {

    String apiKey = "CAAPHOM3N94LAFJL";
    String stockSymbol = "GOOG"; //ticker symbol for Google IBM
    URL url = null;

    MainControllerImpl helperController = new MainControllerImpl();
    helperController.programStartsHere();
//    url = new URL("https://www.alphavantage"
//        + ".co/query?function=TIME_SERIES_DAILY"
//        + "&outputsize=full"
//        + "&symbol"
//        + "=" + stockSymbol + "&apikey="+apiKey+"&datatype=csv");
//    InputStream in = null;
//    StringBuilder output = new StringBuilder();
//    in = url.openStream();
//    int b;
//
//    while ((b=in.read())!=-1) {
//      output.append((char) b);
//      while ((b = in.read()) != -1) {
//        output.append((char) b);
//      }
//    }
//    System.out.println(output);
  }
}
