package view.gui;

/**
 * Class that contains all the methods to perform actions after adding the stocks in the user
 * interface.
 */
public class AfterAddingStockFrame extends javax.swing.JFrame {


  public javax.swing.JButton addAnotherStock;
  public javax.swing.JPanel jPanel2;
  public javax.swing.JButton saveCurrentPortfolio;

  /**
   * Creates new form AfterAddingStockFrame.
   */
  public AfterAddingStockFrame() {
    initComponents();
  }

  private void initComponents() {

    jPanel2 = new javax.swing.JPanel();
    addAnotherStock = new javax.swing.JButton();
    saveCurrentPortfolio = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    addAnotherStock.setText("Add another stock");

    saveCurrentPortfolio.setText("saveCurrentPortfolio");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(addAnotherStock, javax.swing.GroupLayout.DEFAULT_SIZE,
                    205, Short.MAX_VALUE)
                .addContainerGap(307, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                jPanel2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(saveCurrentPortfolio,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(173, 173, 173))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(addAnotherStock)
                .addGap(46, 46, 46)
                .addComponent(saveCurrentPortfolio)
                .addContainerGap(380, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
    );

    pack();
  }
}

