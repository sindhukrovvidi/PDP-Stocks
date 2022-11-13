//import static org.junit.Assert.assertEquals;
//
//import controller.MainController;
//import controller.MainControllerImpl;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.Reader;
//import java.io.StringReader;
//import model.FileAccessorsImpl;
//import model.ListOfStocksImpl;
//import org.junit.Test;
//
//public class ControllerTest {
//
//  class MainControllertest1 implements MainController {
//
//    private StringBuilder log;
//
//    public MainControllertest1(StringBuilder log) {
//      this.log = log;
//    }
//
//    @Override
//    public ListOfStocksImpl preprocessStocksData() throws IOException {
//      log.append("Preprocessing the data");
//      FileAccessorsImpl reader = new FileAccessorsImpl();
//      BufferedReader output = reader.readCSV("stocksdata/stocks_data.csv");
//      return (new ListOfStocksImpl(output));
//    }
//
//    /**
//     * This method is used to display the main menu.
//     *
//     * @throws IOException when the given input is not in the list.
//     */
//    public void programStartsHere() throws IOException {
//      log.append("The program started here");
//    }
//
//    ;
//
//    /**
//     * It calls the related controller based on the choice from the main menu.
//     *
//     * @param option that is chosen by the user.
//     * @throws IOException when the user chooses an invalid or unlisted option.
//     */
//    public void getInitialController(int option) throws IOException {
//      log.append("Inside getInitialController");
//
//    }
//  }
//
//  @Test
//  public void testPreprocessData() throws IOException {
//    StringBuilder modelLog = new StringBuilder();
//    MainControllertest1 mainObj = new MainControllertest1(modelLog);
//    mainObj.preprocessStocksData();
//    assertEquals("Preprocessing the data", modelLog.toString());
//  }
//}
