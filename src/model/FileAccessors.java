package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class FileAccessors implements FileAccessorsInterface {


//  public FileAccessors(Reader in) {
//    super(in);
//  }

  @Override
  public BufferedReader readCSV(String file) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    StringBuilder output = new StringBuilder();
    return reader;
  }

  @Override
  public void createCSV(String filename) {
    File newFile = new File("portfolios/"+filename+".csv");
    boolean isFileCreated = false;
    try {
      isFileCreated = newFile.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (isFileCreated) {
      System.out.println("New blank file created.");
    } else {
      System.out.println("File already exists");
    }
//    try {
      // create FileWriter object with file as parameter
//      FileWriter outputfile = new FileWriter(file);
//      File file = new File("portfolios/"+filename+".csv");
//    }
//    catch ( e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
  }

  public void writeIntoCSVFile(String filename) {
    try {
      FileWriter outputfile = new FileWriter("portfolios/"+filename+".csv");
      BufferedWriter bwr = new BufferedWriter(outputfile);
      bwr.write("James");
      bwr.write("\n");
      bwr.write("Hobert");
      bwr.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
