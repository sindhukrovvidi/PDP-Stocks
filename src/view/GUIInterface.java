package view;

import controller.Features;

public interface GUIInterface {

  void addFeatures(Features features);

//  void setValue(String s);

//  String getValue();

  void setText(String s);

  String getText();

  void clearText();
}
