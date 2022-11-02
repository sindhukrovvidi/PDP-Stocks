package model;

import java.io.IOException;

/**
 *
 */
public class Output {

  private static final Appendable out = System.out;

  /**
   *
   * @param s
   * @throws IOException
   */
  public static void append(String s) throws IOException {
    out.append(s);
  }

  /**
   *
   * @throws IOException
   */
  public static void appendNewLine() throws IOException {
    out.append("\n");
  }

}
