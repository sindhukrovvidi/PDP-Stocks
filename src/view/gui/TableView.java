package view.gui;

import javax.swing.ScrollPaneConstants;

public class TableView extends javax.swing.JFrame {


  public javax.swing.JScrollBar jScrollBar1;
  public javax.swing.JScrollPane jScrollPane1;
  public javax.swing.JTable portfolioTable;

  /**
   * Creates new form TableView
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
//      Class[] types = new Class[]{
//          java.lang.String.class, java.lang.String.class, java.lang.Float.class,
//          java.lang.Float.class, java.lang.Float.class, java.lang.Float.class,
//          java.lang.Float.class, java.lang.Integer.class, java.lang.Float.class,
//          java.lang.Float.class
//      };
      boolean[] canEdit = new boolean[]{
          false, false, false, false, false, false, false, false, false, false
      };

//      public Class getColumnClass(int columnIndex) {
//        return types[columnIndex];
//      }

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

