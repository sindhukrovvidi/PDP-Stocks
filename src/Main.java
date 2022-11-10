import controller.MainControllerImpl;
import java.io.IOException;

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
  public static void main(String[] args) throws IOException {
    MainControllerImpl helperController = new MainControllerImpl();
    helperController.programStartsHere();
  }
}
