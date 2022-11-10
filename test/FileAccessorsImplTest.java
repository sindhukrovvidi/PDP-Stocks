import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import model.FileAccessors;
import model.FileAccessorsImpl;
import model.StocksImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Testing model.
 */
public class FileAccessorsImplTest {

  @Test
  public void returningTrueIfFileExists() {
    FileAccessors fa = new FileAccessorsImpl();
    assertEquals(true, fa.isFileExists("hello"));

  }

  @Test
  public void returningFalseIfFileExists() {
    FileAccessors fa1 = new FileAccessorsImpl();
    assertEquals(false, fa1.isFileExists("me"));

  }

  @Test
  public void filePersistenceTest() throws IOException {
    FileAccessors fa1 = new FileAccessorsImpl();

    HashMap<String, StocksImpl> d = fa1.viewFile("d");
    fa1.writeIntoCSVFile("c", d);
    Path currentPath = Paths.get(System.getProperty("user.dir"));
    Path fp = Paths.get(currentPath.toString(), "portfolios");
    File theDir = new File(fp.toString());
    if (!theDir.exists()) {
      theDir.mkdirs();
    }
    File f = new File(fp.toString() + "/c.csv");
    BufferedReader reader = fa1.readCSV(f.getPath());
    assertEquals("GOOG,2022-10-28,92.53,96.86,92.3225,96.58,3.5696924E7,5,482.90002",
        reader.readLine());
  }
}