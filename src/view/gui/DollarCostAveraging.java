package view.gui;

import javax.swing.JFrame;

/**
 * Class that contains all the methods to perform the dollar cost averaging plan in a portfolio.
 */
public class DollarCostAveraging {

  public javax.swing.JButton addMultiStockButton;
  public javax.swing.JLabel amountInvestedLabel;
  public javax.swing.JTextField amountInvestedTextField;
  public javax.swing.JLabel commissionFeeLabel;
  public javax.swing.JTextField commissionFeeTextField;
  public javax.swing.JLabel endDateLabel;
  public javax.swing.JTextField endDateTextField;
  public javax.swing.JLabel frequencyLabel;
  public javax.swing.JTextField frequencyTextField;
  public javax.swing.JPanel jPanel2;
  public javax.swing.JLabel startDateLabel;
  public javax.swing.JTextField startDateTextField;
  public javax.swing.JLabel tickerListLabel;
  public javax.swing.JTextField tickerListTextField;
  public javax.swing.JLabel weightageLabel;
  public javax.swing.JTextField weightageTextField;


  public JFrame dollarCostAveragingFrame;

  /**
   * Creates new form test frame.
   */
  public DollarCostAveraging() {
    dollarCostAveragingFrame = new JFrame();
    initComponents();
  }

  public JFrame getFrame() {
    return dollarCostAveragingFrame;
  }


  private void initComponents() {

    jPanel2 = new javax.swing.JPanel();
    tickerListTextField = new javax.swing.JTextField();
    startDateTextField = new javax.swing.JTextField();
    endDateTextField = new javax.swing.JTextField();
    amountInvestedTextField = new javax.swing.JTextField();
    addMultiStockButton = new javax.swing.JButton();
    tickerListLabel = new javax.swing.JLabel();
    startDateLabel = new javax.swing.JLabel();
    endDateLabel = new javax.swing.JLabel();
    amountInvestedLabel = new javax.swing.JLabel();
    weightageLabel = new javax.swing.JLabel();
    weightageTextField = new javax.swing.JTextField();
    commissionFeeLabel = new javax.swing.JLabel();
    commissionFeeTextField = new javax.swing.JTextField();
    frequencyLabel = new javax.swing.JLabel();
    frequencyTextField = new javax.swing.JTextField();

    dollarCostAveragingFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    addMultiStockButton.setText("Add");

    tickerListLabel.setText("Enter ticker value with coma seperated");

    startDateLabel.setText("Start Date (yyyy-mm-dd)");

    endDateLabel.setText("End date (yyyy-mm-dd)");

    amountInvestedLabel.setText("Enter amount to invest");

    weightageLabel.setText("Enter the weightage with coma seperated (should add up to 100!!)");

    commissionFeeLabel.setText("Enter commission fee");

    frequencyLabel.setText("Enter frequency in days");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout
                            .Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(frequencyLabel)
                            .addPreferredGap(javax.swing.LayoutStyle
                                    .ComponentPlacement.RELATED,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                            .addComponent(frequencyTextField,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                200, javax.swing.GroupLayout
                                    .PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(weightageLabel)
                            .addPreferredGap(javax.swing.LayoutStyle
                                    .ComponentPlacement.RELATED,
                                25,
                                Short.MAX_VALUE)
                            .addComponent(weightageTextField,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                200, javax.swing.GroupLayout
                                    .PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(amountInvestedLabel)
                            .addPreferredGap(javax.swing.LayoutStyle
                                    .ComponentPlacement.RELATED,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                            .addComponent(amountInvestedTextField,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                200,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(endDateLabel)
                            .addPreferredGap(javax.swing.LayoutStyle
                                    .ComponentPlacement.RELATED,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                            .addComponent(endDateTextField,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                200,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(startDateLabel)
                            .addPreferredGap(javax.swing.LayoutStyle
                                    .ComponentPlacement.RELATED,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                            .addComponent(startDateTextField,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                200, javax.swing.GroupLayout
                                    .PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(tickerListLabel)
                            .addPreferredGap(javax.swing.LayoutStyle
                                    .ComponentPlacement.RELATED,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                            .addComponent(tickerListTextField,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                200, javax.swing.GroupLayout
                                    .PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(commissionFeeLabel)
                            .addPreferredGap(javax.swing.LayoutStyle
                                    .ComponentPlacement.RELATED,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                            .addComponent(commissionFeeTextField,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                200,
                                javax.swing.GroupLayout
                                    .PREFERRED_SIZE)))
                .addGap(64, 64, 64))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                jPanel2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(addMultiStockButton)
                    .addGap(225, 225, 225))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout
                            .Alignment.BASELINE)
                        .addComponent(tickerListTextField, javax.swing
                                .GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tickerListLabel))
                .addGap(28, 28, 28)
                .addGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout
                            .Alignment.BASELINE)
                        .addComponent(startDateTextField, javax.swing
                                .GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(startDateLabel))
                .addGap(26, 26, 26)
                .addGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout
                            .Alignment.BASELINE)
                        .addComponent(endDateTextField, javax.swing
                                .GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(endDateLabel))
                .addGap(18, 18, 18)
                .addGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout
                            .Alignment.BASELINE)
                        .addComponent(amountInvestedTextField,
                            javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(amountInvestedLabel))
                .addGap(24, 24, 24)
                .addGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout
                            .Alignment.BASELINE)
                        .addComponent(weightageLabel)
                        .addComponent(weightageTextField, javax.swing
                                .GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout
                            .Alignment.BASELINE)
                        .addComponent(commissionFeeLabel)
                        .addComponent(commissionFeeTextField,
                            javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout
                            .Alignment.BASELINE)
                        .addComponent(frequencyLabel)
                        .addComponent(frequencyTextField, javax.swing
                                .GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(addMultiStockButton)
                .addContainerGap(26, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout =
        new javax.swing.GroupLayout(dollarCostAveragingFrame.getContentPane());
    dollarCostAveragingFrame.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    dollarCostAveragingFrame.pack();
  }
}

