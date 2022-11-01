package model;

import java.io.BufferedReader;
import java.io.IOException;

public interface FileAccessorsInterface {

  BufferedReader readCSV(String file) throws IOException;

  void createCSV(String file);
}
