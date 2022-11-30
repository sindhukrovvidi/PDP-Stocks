package view.gui;

/**
 * Class that extends the java swings and contains all the methods to that are responsible for
 * implementation of the selling the stocks frame.
 */
public class SellStocksFrame extends javax.swing.JFrame {


  // Variables declaration - do not modify
  public javax.swing.JLabel choosePortfolioLabel;
  public javax.swing.JLabel chooseTickerLabel;
  public javax.swing.JTextField dateTextField;
  public javax.swing.JLabel enterDateLabel;
  public javax.swing.JTextField feeTextField;
  public javax.swing.JComboBox<String> jComboBox1;
  public javax.swing.JLabel jLabel1;
  public javax.swing.JLabel jLabel2;
  public javax.swing.JLabel jLabel3;
  public javax.swing.JPanel jPanel1;
  public javax.swing.JScrollBar jScrollBar1;
  public javax.swing.JScrollPane jScrollPane1;
  public javax.swing.JComboBox<String> portfolioFilesDropDown;
  public javax.swing.JButton sellStocks;
  public javax.swing.JTextField stocksTextField;
  public javax.swing.JTable viewPortfolioTable;

  /**
   * Constructor to create new form SellStocksFrame.
   */
  public SellStocksFrame() {
    initComponents();
  }

  private void initComponents() {

    jScrollBar1 = new javax.swing.JScrollBar();
    jPanel1 = new javax.swing.JPanel();
    portfolioFilesDropDown = new javax.swing.JComboBox<>();
    jScrollPane1 = new javax.swing.JScrollPane();
    viewPortfolioTable = new javax.swing.JTable();
    choosePortfolioLabel = new javax.swing.JLabel();
    sellStocks = new javax.swing.JButton();
    chooseTickerLabel = new javax.swing.JLabel();
    jComboBox1 = new javax.swing.JComboBox<>();
    enterDateLabel = new javax.swing.JLabel();
    dateTextField = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    stocksTextField = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    feeTextField = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    portfolioFilesDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{}));

    viewPortfolioTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][]{
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null}
        },
        new String[]{
            "Company", "Date", "Open", "High", "Low", "Close", "Volume", "Shares invested",
            "Total Value", "Commission fee"
        }
    ));
    jScrollPane1.setViewportView(viewPortfolioTable);

    choosePortfolioLabel.setText("Choose portfolio:");

    sellStocks.setText("Sell stocks");

    chooseTickerLabel.setText("Choose ticker:");

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{}));

    enterDateLabel.setText("Enter date:");

    jLabel1.setText("Enter stocks to sell:");

    jLabel2.setText("Enter commission fee:");

    jLabel3.setText("Stocks are not yet sold!! Please fill the above information.");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(
                                    javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(225, 225, 225)
                                    .addComponent(choosePortfolioLabel)
                                    .addGap(29, 29, 29)
                                    .addComponent(portfolioFilesDropDown,
                                        javax.swing.GroupLayout.PREFERRED_SIZE, 121,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(38, 38, 38)
                                    .addGroup(jPanel1Layout.createParallelGroup(
                                            javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(
                                                    javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel1)
                                                .addComponent(chooseTickerLabel))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel1Layout.createParallelGroup(
                                                    javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jComboBox1, 0, 152, Short.MAX_VALUE)
                                                .addComponent(stocksTextField))
                                            .addGap(110, 110, 110)
                                            .addGroup(jPanel1Layout.createParallelGroup(
                                                    javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(enterDateLabel,
                                                    javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel2,
                                                    javax.swing.GroupLayout.Alignment.TRAILING))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel1Layout.createParallelGroup(
                                                    javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(dateTextField)
                                                .addComponent(feeTextField,
                                                    javax.swing.GroupLayout.DEFAULT_SIZE, 109,
                                                    Short.MAX_VALUE))))))
                            .addGap(32, 32, 32))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(298, 298, 298)
                            .addComponent(sellStocks)
                            .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(portfolioFilesDropDown,
                            javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(choosePortfolioLabel))
                .addGap(17, 17, 17)
                .addGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chooseTickerLabel)
                        .addComponent(enterDateLabel)
                        .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(stocksTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(feeTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(sellStocks)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18,
                    Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 471,
                    javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
  }
}

