package view.gui;

public class MainFrame extends javax.swing.JFrame {

  public javax.swing.JButton buyStocksButton;
  public javax.swing.JButton costBasisButton;
  public javax.swing.JButton createPortfolioButton;
  public javax.swing.JSplitPane jSplitPane1;
  public javax.swing.JPanel leftMenu;
  public javax.swing.JPanel mainPanel;
  public javax.swing.JButton performanceButton;
  public javax.swing.JPanel rightPanel;
  public javax.swing.JButton sellStocksButton;
  public javax.swing.JButton totalCompositionButton;
  public javax.swing.JButton viewPortfolioButton;

  /**
   * Creates new form MainFrame
   */
  public MainFrame() {
    initComponents();
  }

  // <editor-fold defaultstate="collapsed" desc="Generated Code">
  private void initComponents() {

    mainPanel = new javax.swing.JPanel();
    jSplitPane1 = new javax.swing.JSplitPane();
    leftMenu = new javax.swing.JPanel();
    createPortfolioButton = new javax.swing.JButton();
    viewPortfolioButton = new javax.swing.JButton();
    buyStocksButton = new javax.swing.JButton();
    sellStocksButton = new javax.swing.JButton();
    totalCompositionButton = new javax.swing.JButton();
    costBasisButton = new javax.swing.JButton();
    performanceButton = new javax.swing.JButton();
    rightPanel = new javax.swing.JPanel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    createPortfolioButton.setText("Create Portfolio");
    createPortfolioButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        createPortfolioButtonActionPerformed(evt);
      }
    });

    viewPortfolioButton.setText("View Portfolio");
    viewPortfolioButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        viewPortfolioButtonActionPerformed(evt);
      }
    });

    buyStocksButton.setText("Buy stocks");

    sellStocksButton.setText("Sell stocks");

    totalCompositionButton.setText("Total composition");

    costBasisButton.setText("Cost basis");

    performanceButton.setText("Portfolio performance ");

    javax.swing.GroupLayout leftMenuLayout = new javax.swing.GroupLayout(leftMenu);
    leftMenu.setLayout(leftMenuLayout);
    leftMenuLayout.setHorizontalGroup(
            leftMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftMenuLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(
                                    leftMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(leftMenuLayout.createParallelGroup(
                                                            javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(totalCompositionButton,
                                                            javax.swing.GroupLayout.DEFAULT_SIZE,
                                                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(costBasisButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(sellStocksButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(buyStocksButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(viewPortfolioButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(createPortfolioButton,
                                                            javax.swing.GroupLayout.DEFAULT_SIZE,
                                                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(performanceButton))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    leftMenuLayout.setVerticalGroup(
            leftMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftMenuLayout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(createPortfolioButton)
                            .addGap(38, 38, 38)
                            .addComponent(viewPortfolioButton)
                            .addGap(37, 37, 37)
                            .addComponent(buyStocksButton)
                            .addGap(31, 31, 31)
                            .addComponent(sellStocksButton)
                            .addGap(36, 36, 36)
                            .addComponent(totalCompositionButton)
                            .addGap(39, 39, 39)
                            .addComponent(costBasisButton)
                            .addGap(37, 37, 37)
                            .addComponent(performanceButton)
                            .addContainerGap(64, Short.MAX_VALUE))
    );

    jSplitPane1.setLeftComponent(leftMenu);

    javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
    rightPanel.setLayout(rightPanelLayout);
    rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 554, Short.MAX_VALUE)
    );
    rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 483, Short.MAX_VALUE)
    );

    jSplitPane1.setRightComponent(rightPanel);

    javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
    mainPanel.setLayout(mainPanelLayout);
    mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING,
                            javax.swing.GroupLayout.PREFERRED_SIZE, 1000,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
    );
    mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSplitPane1)
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
    );

    pack();
  }// </editor-fold>

  private void viewPortfolioButtonActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void createPortfolioButtonActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  // Variables declaration - do not modify

  // End of variables declaration
}

