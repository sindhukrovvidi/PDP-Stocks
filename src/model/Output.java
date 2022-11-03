package model;

import java.io.IOException;

/**
 * Class used to format and handle the output.
 */
public class Output {

  private static final Appendable out = System.out;

  /**
   * Method used to append the string to the output.
   *
   * @param s string to be appended in the output.
   * @throws IOException invalid string or input.
   */
  public static void append(String s) throws IOException {
    out.append(s);
  }

  /**
   * Method used to append a new line to the output.
   *
   * @throws IOException invalid input.
   */
  public static void appendNewLine() throws IOException {
    out.append("\n");
  }

}
