import controller.MainControllerImpl;

import java.io.IOException;
import java.text.ParseException;

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

  public static void main(String[] args) throws IOException, ParseException {
    MainControllerImpl helperController = new MainControllerImpl();
    helperController.programStartsHere();
  }
}
