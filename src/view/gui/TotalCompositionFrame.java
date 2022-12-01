package view.gui;

/**
 * Class that extends the java swings and contains all the methods to that are responsible for
 * implementation of the total composition frame.
 */
public class TotalCompositionFrame extends javax.swing.JFrame {

  public javax.swing.JButton calculateCompositionButton;
  public javax.swing.JLabel choosePortfolioLabel;
  public javax.swing.JLabel dateLabel;
  public javax.swing.JTextField dateTextField;
  public javax.swing.JLabel displayText;
  public javax.swing.JPanel jPanel1;
  public javax.swing.JScrollBar jScrollBar1;
  public javax.swing.JScrollPane jScrollPane1;
  public javax.swing.JComboBox<String> portfolioFilesDropDown;
  public javax.swing.JTable viewPortfolioTable;

  /**
   * Constructor that creates new form TotalCompositionFrame.
   */
  public TotalCompositionFrame() {
    initComponents();
  }

  private void initComponents() {

    jScrollBar1 = new javax.swing.JScrollBar();
    jPanel1 = new javax.swing.JPanel();
    portfolioFilesDropDown = new javax.swing.JComboBox<>();
    jScrollPane1 = new javax.swing.JScrollPane();
    viewPortfolioTable = new javax.swing.JTable();
    choosePortfolioLabel = new javax.swing.JLabel();
    dateTextField = new javax.swing.JTextField();
    dateLabel = new javax.swing.JLabel();
    displayText = new javax.swing.JLabel();
    calculateCompositionButton = new javax.swing.JButton();

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

    choosePortfolioLabel.setText("Choose portfolio:");

    dateLabel.setText("Enter date (yyyy-mm-dd):");

    displayText.setText("Displays the composition of the portfolio for selected date");

    calculateCompositionButton.setText("Calculate Composition");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                            jPanel1Layout.createSequentialGroup()
                                .addComponent(choosePortfolioLabel,
                                    javax.swing.GroupLayout.PREFERRED_SIZE, 134,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(
                                    javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(portfolioFilesDropDown,
                                    javax.swing.GroupLayout.PREFERRED_SIZE, 171,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                    107, Short.MAX_VALUE)
                                .addComponent(dateLabel)
                                .addGap(18, 18, 18)
                                .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                    121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(displayText)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(295, 295, 295)
                .addComponent(calculateCompositionButton)
                .addGap(0, 0, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(portfolioFilesDropDown,
                            javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(choosePortfolioLabel)
                        .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dateLabel))
                .addGap(18, 18, 18)
                .addComponent(calculateCompositionButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16,
                    Short.MAX_VALUE)
                .addComponent(displayText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
