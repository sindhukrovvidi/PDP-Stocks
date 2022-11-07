package model;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

public class FileAccessorsImplTest {

  @Test
  public void readCSV() {

  }

  @Test
  public void writeIntoCSVFile() {
  }

  @Test
  public void returningTrueIfFileExists() {
    FileAccessors fa = new FileAccessorsImpl();
    assertEquals(true,fa.isFileExists("hello"));

  }
  @Test
  public void returningFalseIfFileExists(){
    FileAccessors fa1 = new FileAccessorsImpl();
    assertEquals(false,fa1.isFileExists("me"));

  }

  @Test
  public void viewFile() throws IOException {

  }

  @Test
  public void listOfPortfolioFiles() {
  }
}