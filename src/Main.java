import controller.GUIMainController;
import controller.MainControllerImpl;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

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
    System.out.println("Choose the interface.");
    System.out.println("1. Select text-based interface.");
    System.out.println("2. Select GUI");
    Scanner scan = new Scanner(System.in);
    int option = scan.nextInt();
    if (option == 1) {
      MainControllerImpl helperController = new MainControllerImpl();
      helperController.programStartsHere();
    } else {
      GUIMainController guiMainController = new GUIMainController();
      guiMainController.programStartsHere();
    }
  }
}
