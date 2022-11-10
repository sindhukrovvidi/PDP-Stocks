package controller;

import java.io.IOException;

import model.ListOfStocksImpl;

/**
 * This interface contains various methods that are used in the main controller.
 */
public interface MainController {

  /**
   * This method is used to process the stocks data file before using it.
   *
   * @return It returns the ordered list of stocks from the pre-processed data.
   * @throws IOException when there is an invalid input.
   */
  ListOfStocksImpl preprocessStocksData() throws IOException;

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
