package view.gui;

/**
 * Class that extends the java swings and contains all the methods to that are responsible for
 * implementation of the viewing portfolio frame.
 */
public class ViewPortfolioFrame extends javax.swing.JFrame {

  public javax.swing.JLabel choosePortfolioLabel;
  public javax.swing.JPanel jPanel1;
  public javax.swing.JScrollBar jScrollBar1;
  public javax.swing.JScrollPane jScrollPane1;
  public javax.swing.JComboBox<String> portfolioFilesDropDown;
  public javax.swing.JTable viewPortfolioTable;

  /**
   * Constructor that creates new form ViewPortfolioFrame.
   */
  public ViewPortfolioFrame() {
    initComponents();
  }

  private void initComponents() {

    jScrollBar1 = new javax.swing.JScrollBar();
    jPanel1 = new javax.swing.JPanel();
    portfolioFilesDropDown = new javax.swing.JComboBox<>();
    jScrollPane1 = new javax.swing.JScrollPane();
    viewPortfolioTable = new javax.swing.JTable();
    choosePortfolioLabel = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    portfolioFilesDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(
        new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

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

    choosePortfolioLabel.setText("Choose portfolio");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                jPanel1Layout.createSequentialGroup()
                    .addContainerGap(161, Short.MAX_VALUE)
                    .addComponent(choosePortfolioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(portfolioFilesDropDown, javax.swing.GroupLayout.PREFERRED_SIZE,
                        171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(191, 191, 191))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(portfolioFilesDropDown,
                            javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(choosePortfolioLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35,
                    Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 478,
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
