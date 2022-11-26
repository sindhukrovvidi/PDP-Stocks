//package view;
//
//import controller.MainController;
//import java.awt.FlowLayout;
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.Formatter;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JTextField;
//import javax.swing.Box;
//
//public class JFrameStocksView extends JFrame implements StockView{
//
//  private JLabel display;
//  private JButton echoButton, exitButton, toggleButton;
//  private JTextField input;
////private Box box = Box.createVerticalBox();
//  public JFrameStocksView() {
//    super("Welcpme to Stock Market!!!!");
//
//    setSize(500, 300);
//    setLocation(200, 200);
//    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    // this.setResizable(false);
//    // this.setMinimumSize(new Dimension(300,300));
//
//
//    this.setLayout(new FlowLayout());
//
////    display = new JLabel("To be displayed");
//    //label = new JLabel(new ImageIcon("Jellyfish.JPG"));
//
//
////    this.add(display);
//
//    //the textfield
////    input = new JTextField(10);
////    this.add(input);
////
////    //echobutton
////    echoButton = new JButton("Echo");
////    echoButton.setActionCommand("Echo Button");
////    this.add(echoButton);
////
////    //toggle color button
////    toggleButton = new JButton("Toggle color");
////    toggleButton.setActionCommand("Toggle color");
////    this.add(toggleButton);
////
////    //exit button
////    exitButton = new JButton("Exit");
////    exitButton.setActionCommand("Exit Button");
////    this.add(exitButton);
//
//
////    pack();
////    setVisible(true);
//  }
//
//  @Override
//  public void displayListOfDates(boolean displayHeaders, String company, String date,
//      float open, float high,
//      float low, float close, float volume) throws IOException {
//
//    Formatter fmt = new Formatter();
//    if (displayHeaders) {
//      fmt.format("Following are the stock details of the company " + company + ".\n");
//      fmt.format("%15s %15s %15s %15s %15s %15s\n", "Date", "Open", "High", "Low",
//          "close", "Volume");
//    }
//    fmt.format("%15s %15s %15s %15s %15s %15s\n", date, open, high, low,
//        close, volume);
//    System.out.println(fmt.toString());
////    this.out.append(fmt.toString());
//  }
//
//  @Override
//  public void displayStarterMenu(MainController features) throws IOException {
//    JButton option1;
//    JButton option2;
//    JButton option3;
//
//    setSize(500, 300);
//    setLocation(200, 200);
//
//    option1 = new JButton("Create a portfolio");
//    option2 = new JButton("View & Speculate existing portfolio");
//    option3 = new JButton("Exit");
//    Box box = Box.createVerticalBox();
//
//    box.add(option1);
//    box.add(option2);
//    box.add(option3);
//
//    this.add(box);
//
//    option1.addActionListener(evt -> {
//      try {
//        features.getInitialController(1);
//      } catch (IOException e) {
//        throw new RuntimeException(e);
//      } catch (ParseException e) {
//        throw new RuntimeException(e);
//      }
//    });
////    this.add(option1);
////    this.add(option2);
////    this.add(option3);
//
//    pack();
//    setVisible(true);
//
//
//  }
//
//  @Override
//  public void addFeature(MainController features) {
//
//  }
//}
