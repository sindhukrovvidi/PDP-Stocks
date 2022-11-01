package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FileAccessors extends BufferedReader implements FileAccessorsInterface {


  public FileAccessors(Reader in) {
    super(in);
  }

  @Override
  public FileAccessorsInterface readCSV() throws IOException {
    StringBuilder output = new StringBuilder();
    return this;
  }

}
