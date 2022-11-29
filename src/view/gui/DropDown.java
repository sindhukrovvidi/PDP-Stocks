package view.gui;

/**
 *
 * @author sindhukrovvidi
 */
public class DropDown extends javax.swing.JFrame {

  public javax.swing.JComboBox<String> jComboBox1;
  public javax.swing.JSplitPane jSplitPane1;
  public javax.swing.JPanel leftMenu;
  public javax.swing.JPanel mainPanel;
  public javax.swing.JPanel rightPanel;

  /**
   * Creates new form testframe
   */
  public DropDown() {
    initComponents();
  }

  private void initComponents() {

    mainPanel = new javax.swing.JPanel();
    jSplitPane1 = new javax.swing.JSplitPane();
    leftMenu = new javax.swing.JPanel();
    rightPanel = new javax.swing.JPanel();
    jComboBox1 = new javax.swing.JComboBox<>();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    javax.swing.GroupLayout leftMenuLayout = new javax.swing.GroupLayout(leftMenu);
    leftMenu.setLayout(leftMenuLayout);
    leftMenuLayout.setHorizontalGroup(
        leftMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
    );
    leftMenuLayout.setVerticalGroup(
        leftMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 483, Short.MAX_VALUE)
    );

    jSplitPane1.setLeftComponent(leftMenu);

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
//    jComboBox1.addActionListener(new java.awt.event.ActionListener() {
//      public void actionPerformed(java.awt.event.ActionEvent evt) {
//        jComboBox1ActionPerformed(evt);
//      }
//    });

    javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
    rightPanel.setLayout(rightPanelLayout);
    rightPanelLayout.setHorizontalGroup(
        rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(719, Short.MAX_VALUE))
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(719, Short.MAX_VALUE))

    );
    rightPanelLayout.setVerticalGroup(
        rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(421, Short.MAX_VALUE))
    );

    jSplitPane1.setRightComponent(rightPanel);

    javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
    mainPanel.setLayout(mainPanelLayout);
    mainPanelLayout.setHorizontalGroup(
        mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
    );

    pack();
  }// </editor-fold>

  private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }


  // Variables declaration - do not modify

  // End of variables declaration
}

