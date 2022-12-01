package view.gui;

/**
 * Renders the performance frame.
 */
public class PerformanceFrame extends javax.swing.JFrame {

  public javax.swing.JLabel changePortfolioLabel;
  public javax.swing.JTextField endDateField;
  public javax.swing.JLabel endDateLabel;
  public javax.swing.JButton getPerformanceButton;
  public javax.swing.JPanel mainPanel;
  public javax.swing.JComboBox<String> portfoliosDropdown;
  public javax.swing.JTextField startDateField;
  public javax.swing.JLabel startDateLabel;

  /**
   * Creates new form PerformanceFrame.
   */
  public PerformanceFrame() {
    initComponents();
  }

  private void initComponents() {

    mainPanel = new javax.swing.JPanel();
    portfoliosDropdown = new javax.swing.JComboBox<>();
    changePortfolioLabel = new javax.swing.JLabel();
    startDateLabel = new javax.swing.JLabel();
    startDateField = new javax.swing.JTextField();
    endDateLabel = new javax.swing.JLabel();
    endDateField = new javax.swing.JTextField();
    getPerformanceButton = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    portfoliosDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(
        new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

    changePortfolioLabel.setText("Choose portfolio:");

    startDateLabel.setText("Start date (yyyy-mm-dd):");

    endDateLabel.setText("End date (yyyy-mm-dd):");

    getPerformanceButton.setText("Get performance");

    javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
    mainPanel.setLayout(mainPanelLayout);
    mainPanelLayout.setHorizontalGroup(
        mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(
                    mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                            .addGroup(mainPanelLayout.createParallelGroup(
                                    javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(mainPanelLayout.createSequentialGroup()
                                    .addGap(131, 131, 131)
                                    .addComponent(changePortfolioLabel,
                                        javax.swing.GroupLayout.PREFERRED_SIZE, 113,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(mainPanelLayout.createSequentialGroup()
                                    .addGap(165, 165, 165)
                                    .addGroup(mainPanelLayout.createParallelGroup(
                                            javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(startDateLabel)
                                        .addComponent(endDateLabel))))
                            .addGap(18, 18, 18)
                            .addGroup(mainPanelLayout.createParallelGroup(
                                    javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(portfoliosDropdown, 0, 128, Short.MAX_VALUE)
                                .addComponent(startDateField)
                                .addComponent(endDateField)))
                        .addGroup(mainPanelLayout.createSequentialGroup()
                            .addGap(206, 206, 206)
                            .addComponent(getPerformanceButton)))
                .addContainerGap(138, Short.MAX_VALUE))
    );
    mainPanelLayout.setVerticalGroup(
        mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(
                    mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(portfoliosDropdown, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(changePortfolioLabel))
                .addGroup(
                    mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10,
                                10)
                            .addComponent(endDateLabel))
                        .addGroup(mainPanelLayout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(mainPanelLayout.createParallelGroup(
                                    javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(startDateField,
                                    javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(startDateLabel))
                            .addComponent(endDateField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(getPerformanceButton)
                .addContainerGap(64, Short.MAX_VALUE))
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
  }

}
