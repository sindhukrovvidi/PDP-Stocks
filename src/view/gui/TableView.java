package view.gui;

import javax.swing.*;

/**
 * Class that extends the java swings and contains all the methods to that are responsible for
 * implementation of a table view frame.
 */
public class TableView extends javax.swing.JFrame {


  public javax.swing.JScrollBar jScrollBar1;
  public javax.swing.JScrollPane jScrollPane1;
  public javax.swing.JTable portfolioTable;

  /**
   * Constructor to create new form TableView.
   */
  public TableView() {
    initComponents();
  }


  private void initComponents() {

    jScrollBar1 = new javax.swing.JScrollBar();
    jScrollPane1 = new javax.swing.JScrollPane();
    portfolioTable = new javax.swing.JTable();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    portfolioTable.setModel(new javax.swing.table.DefaultTableModel(
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
    ) {

      final boolean[] canEdit = new boolean[]{
              false, false, false, false, false, false, false, false, false, false
      };


      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
      }
    });
    portfolioTable.getTableHeader().setReorderingAllowed(false);
    jScrollPane1.setViewportView(portfolioTable);
    jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 77, Short.MAX_VALUE))
    );

    pack();
  }
}

