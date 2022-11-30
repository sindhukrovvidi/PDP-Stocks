//import java.awt.*;
//import java.awt.event.*;
//import java.awt.geom.*;
//import javax.swing.*;
//import javax.swing.event.*;
//
//public class LiveGraph
//{
//  public static void main(String[] args)
//  {
//    GraphPanel panel = new GraphPanel();
//    JFrame f = new JFrame();
//    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    f.getContentPane().add(panel.action.getUIPanel(), "North");
//    f.getContentPane().add(panel);
//    f.setSize(400,400);
//    f.setLocation(200,200);
//    f.setVisible(true);
//  }
//}
//
//class GraphPanel extends JPanel
//{
//  GraphAction action;
//  Ellipse2D circle;
//  Rectangle2D square;
//  boolean firstTime;
//
//  public GraphPanel()
//  {
//    firstTime = true;
//    action = new GraphAction(this);
//    setBackground(Color.white);
//  }
//
//  protected void paintComponent(Graphics g)
//  {
//    super.paintComponent(g);
//    Graphics2D g2 = (Graphics2D)g;
//    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//        RenderingHints.VALUE_ANTIALIAS_ON);
//    if(firstTime)
//      initializeShapes();
//    g2.setPaint(Color.green);
//    g2.fill(square);
//    g2.setPaint(Color.yellow);
//    g2.fill(circle);
//  }
//
//  private void initializeShapes()
//  {
//    int w = getWidth();
//    int h = getHeight();
//    int diameter = Math.min(w,h)/4;
//    int side = diameter * 2;
//    System.out.println("diameter = " + diameter);
//    int cx = w/2;
//    int cy = h/2;
//    circle = new Ellipse2D.Double(cx - diameter/2, cy - diameter/2,
//        diameter, diameter);
//    square = new Rectangle2D.Double(cx - side/2, cy - side/2, side, side);
//    action.circleModel.setValue(new Integer(diameter));
//    action.squareModel.setValue(new Integer(side));
//    firstTime = false;
//  }
//
//  public void setDiameter(int d)
//  {
//    int cx = getWidth()/2;
//    int cy = getHeight()/2;
//    circle.setFrameFromCenter(cx, cy, cx - d/2, cy - d/2);
//    repaint();
//  }
//
//  public void setSide(int s)
//  {
//    int cx = getWidth()/2;
//    int cy = getHeight()/2;
//    square.setFrameFromCenter(cx, cy, cx - s/2, cy - s/2);
//    repaint();
//  }
//}
//
//class GraphAction
//{
//  GraphPanel graphPanel;
//  SpinnerNumberModel circleModel, squareModel;
//
//  public GraphAction(GraphPanel gp)
//  {
//    graphPanel = gp;
//  }
//
//  public JPanel getUIPanel()
//  {
//    circleModel = new SpinnerNumberModel(0, 0, 100, 5);
//    final JSpinner circleSpinner = new JSpinner(circleModel);
//    squareModel = new SpinnerNumberModel(0, 0, 200, 5);
//    final JSpinner squareSpinner = new JSpinner(squareModel);
//    ChangeListener l = new ChangeListener()
//    {
//      public void stateChanged(ChangeEvent e)
//      {
//        JSpinner spinner = (JSpinner)e.getSource();
//        int value = ((Integer)spinner.getValue()).intValue();
//        if(spinner == circleSpinner)
//          graphPanel.setDiameter(value);
//        if(spinner == squareSpinner)
//          graphPanel.setSide(value);
//      }
//    };
//    circleSpinner.addChangeListener(l);
//    squareSpinner.addChangeListener(l);
//    JPanel panel = new JPanel(new GridBagLayout());
//    GridBagConstraints gbc = new GridBagConstraints();
//    gbc.insets = new Insets(2,2,2,2);
//    gbc.weightx = 1.0;
//    gbc.anchor = gbc.EAST;
//    panel.add(new JLabel("diameter"), gbc);
//    gbc.anchor = gbc.WEST;
//    panel.add(circleSpinner, gbc);
//    gbc.anchor = gbc.EAST;
//    panel.add(new JLabel("side"), gbc);
//    gbc.anchor = gbc.WEST;
//    panel.add(squareSpinner, gbc);
//    return panel;
//  }
//}