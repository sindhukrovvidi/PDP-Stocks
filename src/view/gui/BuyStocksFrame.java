package view.gui;

/**
 * Class that contains all the methods to perform buying action on stocks in the user interface.
 */
public class BuyStocksFrame extends javax.swing.JFrame {

  public javax.swing.JButton addStocks;
  public javax.swing.JLabel choosePortfolioLabel;
  public javax.swing.JPanel jPanel1;
  public javax.swing.JScrollBar jScrollBar1;
  public javax.swing.JScrollPane jScrollPane1;
  public javax.swing.JComboBox<String> portfolioFilesDropDown;
  public javax.swing.JTable viewPortfolioTable;

  /**
   * Creates new form BuyStocksFrame.
   */
  public BuyStocksFrame() {
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">
  private void initComponents() {

    jScrollBar1 = new javax.swing.JScrollBar();
    jPanel1 = new javax.swing.JPanel();
    portfolioFilesDropDown = new javax.swing.JComboBox<>();
    jScrollPane1 = new javax.swing.JScrollPane();
    viewPortfolioTable = new javax.swing.JTable();
    choosePortfolioLabel = new javax.swing.JLabel();
    addStocks = new javax.swing.JButton();

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
                    "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7",
                    "Title 8", "Title 9", "Title 10"
            }
    ));
    jScrollPane1.setViewportView(viewPortfolioTable);

    choosePortfolioLabel.setText("Choose portfolio:");

    addStocks.setText("Add stocks");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(choosePortfolioLabel,
                                    javax.swing.GroupLayout.PREFERRED_SIZE, 134,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(portfolioFilesDropDown,
                                    javax.swing.GroupLayout.PREFERRED_SIZE, 171,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                    170,
                                    Short.MAX_VALUE)
                            .addComponent(addStocks)
                            .addGap(85, 85, 85))
    );
    jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addGroup(
                                    jPanel1Layout.createParallelGroup
                                                    (javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(portfolioFilesDropDown,
                                                    javax.swing.GroupLayout.PREFERRED_SIZE,
                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(choosePortfolioLabel)
                                            .addComponent(addStocks))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                    55,
                                    Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                    458,
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
