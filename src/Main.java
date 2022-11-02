import controller.MainControllerImpl;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    MainControllerImpl helperController = new MainControllerImpl();
    helperController.go();
  }
}
