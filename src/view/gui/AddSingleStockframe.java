package view.gui;

import javax.swing.JFrame;

public class AddSingleStockframe  {

  public javax.swing.JButton addStockButton;
  public javax.swing.JLabel tickerValueLabel;
  public javax.swing.JLabel dateLabel;
  public javax.swing.JLabel stocksLabel;
  public javax.swing.JLabel commissionFeeLabel;
  public javax.swing.JPanel panel;
  public javax.swing.JTextField tickerValuetextField;
  public javax.swing.JTextField dateTextField;
  public javax.swing.JTextField stocksTextField;
  public javax.swing.JTextField commissionFeeTextField;

  protected JFrame addSingleStockFrame;
  /**
   * Creates new form AddSingleStockframe
   */
  public AddSingleStockframe() {
    addSingleStockFrame = new JFrame();
    initComponents();
  }

  public JFrame getFrame() {
    return addSingleStockFrame;
  }

  private void initComponents() {

    panel = new javax.swing.JPanel();
    tickerValuetextField = new javax.swing.JTextField();
    dateTextField = new javax.swing.JTextField();
    stocksTextField = new javax.swing.JTextField();
    commissionFeeTextField = new javax.swing.JTextField();
    addStockButton = new javax.swing.JButton();
    tickerValueLabel = new javax.swing.JLabel();
    dateLabel = new javax.swing.JLabel();
    stocksLabel = new javax.swing.JLabel();
    commissionFeeLabel = new javax.swing.JLabel();

    addSingleStockFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);



    addStockButton.setText("Add");

    tickerValueLabel.setText("Enter ticker value");

    dateLabel.setText("Date (yyyy-mm-dd)");

    stocksLabel.setText("Enter no.of Stocks");

    commissionFeeLabel.setText("Enter commission fee");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(panel);
    panel.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tickerValueLabel)
                            .addComponent(commissionFeeLabel)
                            .addComponent(dateLabel)
                            .addComponent(stocksLabel))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(commissionFeeTextField,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stocksTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tickerValuetextField,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(addStockButton)))
                .addContainerGap(132, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tickerValuetextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tickerValueLabel))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateLabel))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stocksTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stocksLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(commissionFeeTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(commissionFeeLabel))
                .addGap(37, 37, 37)
                .addComponent(addStockButton)
                .addContainerGap(41, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout =
        new javax.swing.GroupLayout(addSingleStockFrame.getContentPane());
    addSingleStockFrame.getContentPane().setLayout(layout);
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

    addSingleStockFrame.pack();
  }

  private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }


}

