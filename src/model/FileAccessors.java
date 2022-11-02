package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;

import static model.Output.append;
import static model.Output.appendNewLine;
import static model.Output.appendTabSpace;

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
    File newFile = new File("portfolios/" + filename + ".csv");
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

  public void writeIntoCSVFile(String filename, HashMap<String, Stocks> entriesInPortfolio) {
    try {
      FileWriter outputFile = new FileWriter("portfolios/" + filename + ".csv");
      BufferedWriter bwr = new BufferedWriter(outputFile);
      entriesInPortfolio.forEach((k, v) -> {
        Stocks currentStock = (Stocks) v;
        try {
//          String company, String date, float open, float high, float low,
//          float close, float volume,
//          int shares
          bwr.write(currentStock.getCompany());
          bwr.write(",");
          bwr.write(currentStock.getDate());
          bwr.write(",");
          bwr.write(String.valueOf(currentStock.getOpen())); // opening price on that day
          bwr.write(",");
          bwr.write(String.valueOf(currentStock.getHigh())); // high value
          bwr.write(",");
          bwr.write(String.valueOf(currentStock.getLow())); // low value
          bwr.write(",");
          bwr.write(String.valueOf(currentStock.getClose())); // closing price on that day
          bwr.write(",");
          bwr.write(String.valueOf(currentStock.getVolume())); // no shares invested on that day
          bwr.write(",");
          bwr.write(String.valueOf(currentStock.getShares())); // no shares invested on that day
          bwr.write(",");
          bwr.write(String.valueOf(currentStock.getClose() * currentStock.getShares())); // total
          // price for that company
          bwr.write("\n");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      });
      bwr.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean isFileExists(String filename) {
    File f = new File("portfolios/" + filename + ".csv");
    return f.exists() && !f.isDirectory();
  }
  public HashMap<String,Stocks> viewFile(String portfolioName) throws IOException {
    HashMap<String,Stocks> portfolio = new HashMap<>();
    String line;
    BufferedReader reader = readCSV("portfolios/" + portfolioName + ".csv");
    while ((line = reader.readLine()) != null && !line.isEmpty()) {
      String[] fields = line.split(",");
     for(String field:fields){
       append(field);
       appendTabSpace();
     }
     Stocks stocks = new Stocks();
     stocks.setCurrentStock(
             fields[0],
             fields[1],
             Float.parseFloat(fields[2]),
             Float.parseFloat(fields[3]),
             Float.parseFloat(fields[4]),
             Float.parseFloat(fields[5]),
             Float.parseFloat(fields[6]),
             Integer.parseInt(fields[7])
     );
     portfolio.put(fields[0],stocks);
      appendNewLine();
    }
    reader.close();
    return portfolio;
  }
}
