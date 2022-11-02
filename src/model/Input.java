package model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.InputMismatchException;

import static model.Output.append;
import static model.Output.appendNewLine;

/**
 *
 */
public class Input {

  /**
   *
   * @param question
   * @return
   * @throws IOException
   */
  public static Integer takeIntegerInput(String question) throws IOException {
      Readable in = new InputStreamReader(System.in);
      append(question);
      appendNewLine();
      Scanner scan = new Scanner(in);
      Integer input = scan.nextInt();
      return input;
  }

  /**
   *
   * @param question
   * @return
   * @throws IOException
   */
  public static String takeStringInput(String question) throws IOException {
    String input = "";
    try {
      Readable in = new InputStreamReader(System.in);
      append(question);
      appendNewLine();
      Scanner scan = new Scanner(in);
      input = scan.next();
    } catch (InputMismatchException e) {
      append("You have entered invalid input.");
    }
    return input;
  }
}
