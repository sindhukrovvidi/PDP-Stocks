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
    MainControllerImpl helperController = new MainControllerImpl();
    helperController.programStartsHere();
  }
}
