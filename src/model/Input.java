package model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.InputMismatchException;

import static model.Output.append;
import static model.Output.appendNewLine;

/**
 * Class used to handle the input functionalities for the model.
 */
public class Input {

  /**
   * Method used to take an option input which has a question related to operation to be performed.
   *
   * @param question action selection to be performed.
   * @return input from the user.
   * @throws IOException invalid or unlisted input.
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
   * Method used to take an input which has a question related to operation to be performed.
   *
   * @param question action selection to be performed.
   * @return input from the user.
   * @throws IOException invalid or unlisted input.
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

  public static float takeFloatInput(String question) throws IOException {
    Readable in = new InputStreamReader(System.in);
    append(question);
    appendNewLine();
    Scanner scan = new Scanner(in);
    Float input = scan.nextFloat();
    return input;
  }
}
