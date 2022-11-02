/**
 *
 */
package controller;

import java.io.IOException;
import model.ListOfStocksImpl;

/**
 *
 */
public interface MainController {

  /**
   *
   * @return
   * @throws IOException
   */
  ListOfStocksImpl preprocessStocksData() throws IOException;

  /**
   *
   * @throws IOException
   */
  void go() throws IOException;

  /**
   *
   * @param option
   * @throws IOException
   */
  void getInitialController(int option) throws IOException;

}
