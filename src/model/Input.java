package model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;

import static model.Output.append;
import static model.Output.appendNewLine;

public class Input {
  private static final Readable in = new InputStreamReader(System.in);

  public static Integer takeIntegerInput(String question) throws IOException {
    // Objects.requireNonNull(this);
    append(question);
    //appendNewLine();
    Scanner scan = new Scanner(in);
    Integer input = scan.nextInt();
    scan.close();
    return input;
  }

  public static String takeStringInput(String question) throws IOException {
    // Objects.requireNonNull(this);
    append(question);
   // appendNewLine();
    Scanner scan = new Scanner(in);
    String input = scan.nextLine();
    scan.close();
    return input;
  }
}
