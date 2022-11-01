package model;

import java.io.IOException;

public class Output {
  private static final Appendable out = System.out;
  public static void append(String s) throws IOException {
    out.append(s);
  }
  public static void appendNewLine() throws IOException {
    out.append("/n");
  }
}
