package view.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class that contains all the methods for creating a portfolio on a GUI.
 */
public class CreatePortfolioFrame extends JPanel {
  protected JFrame createPortfolio;

  // Variables declaration - do not modify
  public javax.swing.JButton singleStockButton;
  public javax.swing.JButton dollarCostAveragingButton;
  public javax.swing.JPanel panel;

  /**
   * Method that is used to
   */
  public CreatePortfolioFrame() {

    createPortfolio = new JFrame("Add stocks in your portfolio.");

    initComponents();
  }

  public JFrame getCreatePortfolioFrame() {
    return createPortfolio;
  }

  private void initComponents() {

    panel = new javax.swing.JPanel();
    singleStockButton = new javax.swing.JButton();
    dollarCostAveragingButton = new javax.swing.JButton();

    createPortfolio.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    singleStockButton.setText("Add single stock");


    dollarCostAveragingButton.setText("Add through dollar-cost averaging ");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(panel);
    panel.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(131, 131, 131)
                                            .addComponent(singleStockButton))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(76, 76, 76)
                                            .addComponent(dollarCostAveragingButton)))
                            .addContainerGap(81, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addComponent(singleStockButton)
                            .addGap(47, 47, 47)
                            .addComponent(dollarCostAveragingButton)
                            .addContainerGap(146, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(createPortfolio.getContentPane());
    createPortfolio.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
    );

    createPortfolio.pack();
  }
}