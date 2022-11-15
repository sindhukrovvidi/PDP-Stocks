package controller;

import java.io.IOException;

import model.ListOfStocksImpl;

/**
 * This interface contains various methods that are used in the main controller.
 */
public interface MainController {

  /**
   * This method is used to display the main menu.
   *
   * @throws IOException when the given input is not in the list.
   */
  void programStartsHere() throws IOException;

  /**
   * It calls the related controller based on the choice from the main menu.
   *
   * @param option that is chosen by the user.
   * @throws IOException when the user chooses an invalid or unlisted option.
   */
  void getInitialController(int option) throws IOException;

}
