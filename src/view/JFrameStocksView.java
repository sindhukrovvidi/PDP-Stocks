package view;

import controller.Features;

import java.awt.FlowLayout;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import java.util.concurrent.atomic.AtomicReference;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.StocksImpl;
import view.gui.AddSingleStockframe;
import view.gui.AfterAddingStockFrame;
import view.gui.BuyStocksFrame;
import view.gui.CreatePortfolioFrame;
import view.gui.DollarCostAveraging;
import view.gui.LineChart;
import view.gui.MainFrame;
import view.gui.TableView;
import view.gui.ViewPortfolioFrame;
import view.gui.TotalCompositionFrame;
import view.gui.SellStocksFrame;
import view.gui.PerformanceFrame;

/**
 * Class that implements all the methods of GUIInterface that are responsible for the user
 * interface.
 */
public class JFrameStocksView extends JFrame implements GUIInterface {

  protected CreatePortfolioFrame createPortfolioFrame;
  protected AddSingleStockframe addSingleStockframe;

  protected DollarCostAveraging dollarCostAveraging;

  protected JFrame createPortfolio;
  protected JFrame singleStockAddFrame;
  protected JFrame dollarCostAveragingFrame;

  protected AfterAddingStockFrame afterAddingStockFrame = new AfterAddingStockFrame();

  protected MainFrame mainFrame = new MainFrame();

  protected TableView tableView;

  protected ViewPortfolioFrame viewPortfolioFrame;

  protected TotalCompositionFrame totalCompositionFrame;

  protected BuyStocksFrame buyStocksFrame;

  protected SellStocksFrame sellStocksFrame;

  protected PerformanceFrame performanceFrame;


  protected Features features;

  /**
   * Method that is used to display the main features page on the UI.
   *
   * @param features all the available features.
   */
  public JFrameStocksView(Features features) {
    super("Stocks!!!!!!!!!!");

    initialLiseFrames();

    this.features = features;

    setSize(800, 800);
    setLocation(200, 200);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new FlowLayout());


  }

  private void initialLiseFrames() {
    createPortfolioFrame = new CreatePortfolioFrame();
    addSingleStockframe = new AddSingleStockframe();
    dollarCostAveraging = new DollarCostAveraging();
    tableView = new TableView();
    viewPortfolioFrame = new ViewPortfolioFrame();
    totalCompositionFrame = new TotalCompositionFrame();
    buyStocksFrame = new BuyStocksFrame();
    sellStocksFrame = new SellStocksFrame();
    performanceFrame = new PerformanceFrame();

    createPortfolio = createPortfolioFrame.getCreatePortfolioFrame();
    singleStockAddFrame = addSingleStockframe.getFrame();
    dollarCostAveragingFrame = dollarCostAveraging.getFrame();

  }

  @Override
  public void displayStarterMenu() {

    mainFrame.setVisible(true);

    mainFrame.createPortfolioButton.addActionListener(e -> {
      try {
        createPortfolioFile();
      } catch (FileAlreadyExistsException ex) {
        throw new RuntimeException(ex);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    mainFrame.viewPortfolioButton.addActionListener(e -> {
      viewPortfolioFrame();
      viewPortfolioFrame.portfolioFilesDropDown.addActionListener(event -> {
        String portfolio = (String) viewPortfolioFrame.portfolioFilesDropDown.getSelectedItem();
        if (portfolio != null) {
          try {
            populateTable(features.renderTheSelectedPortfolio(portfolio),
                (DefaultTableModel) viewPortfolioFrame.viewPortfolioTable.getModel());
          } catch (IOException er) {
            throw new RuntimeException(er);
          } catch (ParseException er) {
            throw new RuntimeException(er);
          }
        }

      });
    });

    mainFrame.buyStocksButton.addActionListener(e -> {
      initialLiseFrames();
      generateDropDown(
          (DefaultComboBoxModel) buyStocksFrame.portfolioFilesDropDown.getModel(),
          features.getPortfolioNames());
      mainFrame.jSplitPane1.setRightComponent(buyStocksFrame.jPanel1);
      buyStocksFrame.portfolioFilesDropDown.addActionListener(selected -> {
        String portfolio = (String) buyStocksFrame.portfolioFilesDropDown.getSelectedItem();
        try {
          populateTable(features.renderTheSelectedPortfolio(portfolio),
              (DefaultTableModel) buyStocksFrame.viewPortfolioTable.getModel());
          buyStocksFrame.addStocks.addActionListener(cliked -> {
            enterTheTickerValue();
          });
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        } catch (ParseException ex) {
          throw new RuntimeException(ex);
        }
      });
    });

    mainFrame.sellStocksButton.addActionListener(e -> {
      sellStocks();
    });

    mainFrame.totalCompositionButton.addActionListener((e -> {
      calculateComposition(false);
    }));

    mainFrame.costBasisButton.addActionListener((e -> {
      calculateComposition(true);
    }));

    mainFrame.performanceButton.addActionListener(e -> {
      renderChart();
    });


  }

  /**
   * Renders the portfolio performance using line chart.
   */
  public void renderChart() {
    initialLiseFrames();
    generateDropDown(
        (DefaultComboBoxModel) performanceFrame.portfoliosDropdown.getModel(),
        features.getPortfolioNames());
    mainFrame.jSplitPane1.setRightComponent(performanceFrame.mainPanel);

    performanceFrame.getPerformanceButton.addActionListener(e -> {
      String portfolio = (String) performanceFrame.portfoliosDropdown.getSelectedItem();
      String date1 = performanceFrame.startDateField.getText();
      String date2 = performanceFrame.endDateField.getText();
      boolean areValidDates = features.areValidPerformanceDate(date1, date2);
      try {
        features.renderTheSelectedPortfolio(portfolio);
        TreeMap<LocalDate, Integer> portfolioData = features.getPerformanceData(date1, date2);
        LineChart lineChart = new LineChart();
        lineChart.updateData(portfolioData);

      } catch (IOException ex) {
        throw new RuntimeException(ex);
      } catch (ParseException ex) {
        throw new RuntimeException(ex);
      }
    });
  }

  @Override
  public void sellStocks() {
    initialLiseFrames();
    generateDropDown(
        (DefaultComboBoxModel) sellStocksFrame.portfolioFilesDropDown.getModel(),
        features.getPortfolioNames());
    mainFrame.jSplitPane1.setRightComponent(sellStocksFrame.jPanel1);
    sellStocksFrame.portfolioFilesDropDown.addActionListener(file -> {
      String portfolio = (String) sellStocksFrame.portfolioFilesDropDown.getSelectedItem();

      HashMap<String, TreeMap<Date, StocksImpl>> portfolioEntries = null;
      try {
        AtomicReference<String> tickerSelected = new AtomicReference<>("");
        portfolioEntries = features.renderTheSelectedPortfolio(portfolio);
        String[] dropdownValues = portfolioEntries.keySet().toArray(new String[0]);

        generateDropDown((DefaultComboBoxModel) sellStocksFrame.jComboBox1.getModel(),
            dropdownValues);
        populateTable(features.getPortfolio(),
            (DefaultTableModel) sellStocksFrame.viewPortfolioTable.getModel());

        sellStocksFrame.jComboBox1.addActionListener(ticker -> {
          tickerSelected.set((String) sellStocksFrame.jComboBox1.getSelectedItem());
          sellStocksFrame.jLabel3.setText("Below is the data for " + tickerSelected.get() + " from "
              + "your current portfolio");

          populateTableFromTreeMap((features.getPortfolio()).get(tickerSelected.get()),
              (DefaultTableModel) sellStocksFrame.viewPortfolioTable.getModel());


        });
        sellStocksFrame.sellStocks.addActionListener(selling -> {

          String date = sellStocksFrame.dateTextField.getText();
          String shares = sellStocksFrame.stocksTextField.getText().length() > 0
              ? (String) sellStocksFrame.stocksTextField.getText() : "0";
          String fee = sellStocksFrame.feeTextField.getText().length() > 0
              ? (String) sellStocksFrame.feeTextField.getText() : "0";
          try {
            int validInputs = features.validateSellStocks(date, Float.parseFloat(shares),
                Float.parseFloat(fee));
            switch (validInputs) {
              case 1:
                JOptionPane.showMessageDialog(null, "Shares should be greater than zero.",
                    "Invalid shares", JOptionPane.ERROR_MESSAGE);
                break;
              case 2:
                JOptionPane.showMessageDialog(null, "Fee should be greater than zero.",
                    "Invalid fee", JOptionPane.ERROR_MESSAGE);
                break;
              case 3:
                JOptionPane.showMessageDialog(null, "Entered invalid date, please check.",
                    "Invalid date", JOptionPane.ERROR_MESSAGE);
                break;
              default:
                float stocksSold =
                    features.sellTheStocks((features.getPortfolio()).get(tickerSelected.get()),
                        date,
                        Float.parseFloat(shares), Float.parseFloat(fee));
                if (stocksSold != 0) {
                  sellStocksFrame.jLabel3.setText("Your portfolio lacks " + stocksSold + " they "
                      + "are not sold! The updated portfolio is: ");
                } else {
                  sellStocksFrame.jLabel3.setText("Stocks have been successfully sold. The "
                      + "updated portfolio is: ");
                }
                populateTable(features.getPortfolio(),
                    (DefaultTableModel) sellStocksFrame.viewPortfolioTable.getModel());
                break;
            }

          } catch (ParseException ex) {
            throw new RuntimeException(ex);
          }
        });
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      } catch (ParseException ex) {
        throw new RuntimeException(ex);
      }

    });
  }

  @Override
  public void calculateComposition(boolean isCostBasis) {
    initialLiseFrames();
    generateDropDown(
        (DefaultComboBoxModel) totalCompositionFrame.portfolioFilesDropDown.getModel(),
        features.getPortfolioNames());
    mainFrame.jSplitPane1.setRightComponent(totalCompositionFrame.jPanel1);
    AtomicReference<String> portfolio = new AtomicReference<>("");

    totalCompositionFrame.calculateCompositionButton.addActionListener(cal -> {
      String date = totalCompositionFrame.dateTextField.getText();
      portfolio.set((String) totalCompositionFrame.portfolioFilesDropDown.getSelectedItem());
      boolean isValid = features.isValidDate(date);
      if (isValid) {
        try {
          HashMap map = features.getCompositionOfThePortfolio(portfolio.get(), date, isCostBasis);
          if (map == null) {
            totalCompositionFrame.displayText.setText("The entered date is greater than the "
                + "current date. So the composition can not be calculated.");
            DefaultTableModel model =
                (DefaultTableModel) totalCompositionFrame.viewPortfolioTable.getModel();
            model.setRowCount(0);
          } else {
            ArrayList<StocksImpl> inDateList = (ArrayList<StocksImpl>) map.get("inDateList");
            if (inDateList.size() == 0) {
              totalCompositionFrame.displayText.setText(
                  "Total composition of the portfolio is 0 as the given date is either a "
                      + "holiday or you do not have any stocks purchased by then or .");
              DefaultTableModel model =
                  (DefaultTableModel) totalCompositionFrame.viewPortfolioTable.getModel();
              model.setRowCount(0);
            } else {
              totalCompositionFrame.displayText.setText(
                  "Total value till " + date + " is: " + map.get("final_total_value") + "\n"
                      + ". Following is the list of stocks till date: " + date + "\n");

              populateTableFromArrayList((ArrayList<StocksImpl>) map.get("inDateList"),
                  (DefaultTableModel) totalCompositionFrame.viewPortfolioTable.getModel());
            }
          }
        } catch (ParseException ex) {
          throw new RuntimeException(ex);
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      } else {
        JOptionPane.showMessageDialog(null, "You have entered invalid date, please check.",
            "Invalid date", JOptionPane.ERROR_MESSAGE);
      }
    });
  }

  @Override
  public void viewPortfolioFrame() {
    generateDropDown((DefaultComboBoxModel) viewPortfolioFrame.portfolioFilesDropDown.getModel(),
        features.getPortfolioNames());
    mainFrame.jSplitPane1.setRightComponent(viewPortfolioFrame.jPanel1);
  }

  @Override
  public void generateDropDown(DefaultComboBoxModel dropDown, String[] items) {
    dropDown.removeAllElements();
    for (String item : items) {
      dropDown.addElement(item);
    }
  }

  @Override
  public void createPortfolioFile() throws IOException {
    String filename = JOptionPane.showInputDialog("Enter the portfolio name.");
    boolean isFileExists = features.createPortfolio(filename);
    if (isFileExists) {
      enterTheTickerValue();
    } else {
      JOptionPane.showMessageDialog(null, "File already exists, enter another name.", "Invalid"
          + " ticker", JOptionPane.ERROR_MESSAGE);
      enterTheTickerValue();
    }
  }

  @Override
  public void enterTheTickerValue() {
    initialLiseFrames();
    mainFrame.jSplitPane1.setRightComponent(createPortfolioFrame.panel);

    createPortfolioFrame.singleStockButton.addActionListener(e -> {

      mainFrame.jSplitPane1.setRightComponent(addSingleStockframe.panel);

      addSingleStockframe.addStockButton.addActionListener(event -> {

        try {
          String tickerValue = addSingleStockframe.tickerValuetextField.getText();
          String date = addSingleStockframe.dateTextField.getText();
          String stocks = addSingleStockframe.stocksTextField.getText();
          String commissionFee = addSingleStockframe.commissionFeeTextField.getText();

          if (features.validateSingleStocksData(tickerValue, date, Integer.parseInt(stocks),
              Float.parseFloat(commissionFee))) {
            features.addStockToPortfolio(tickerValue, date, Integer.parseInt(stocks),
                Float.parseFloat(commissionFee));
            mainFrame.jSplitPane1.setRightComponent(afterAddingStockFrame.jPanel2);
            afterAddingStockFrame.addAnotherStock.addActionListener(addEvent -> {
              enterTheTickerValue();
            });
            afterAddingStockFrame.saveCurrentPortfolio.addActionListener(saveEvent -> {
              features.saveCurrentPortfolio();
              viewPortfolio();
            });

          } else {
            JOptionPane.showMessageDialog(null,
                "Few inputs are invalid, please check the inputs",
                "Invalid inputs while adding a stock", JOptionPane.ERROR_MESSAGE);
          }
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        } catch (ParseException ex) {
          throw new RuntimeException(ex);
        }


      });

    });
    createPortfolioFrame.dollarCostAveragingButton.addActionListener(e -> {

      mainFrame.jSplitPane1.setRightComponent(dollarCostAveraging.jPanel2);

      dollarCostAveraging.addMultiStockButton.addActionListener(addMulStock -> {
        String ticker = dollarCostAveraging.tickerListTextField.getText();
        String startDate = dollarCostAveraging.startDateTextField.getText();
        String endDate = dollarCostAveraging.endDateTextField.getText();
        String amount = dollarCostAveraging.amountInvestedTextField.getText();
        String weightage = dollarCostAveraging.weightageTextField.getText();
        String fee = dollarCostAveraging.commissionFeeTextField.getText();
        String frequency = dollarCostAveraging.frequencyTextField.getText();

        try {

          boolean areValid = features.isValidateInputForMultiStocks(ticker,
              Float.parseFloat(amount),
              weightage,
              Float.parseFloat(fee), startDate, endDate, Integer.parseInt(frequency));
          if (areValid) {

            features.addDollarCostAveragingStocks(startDate, endDate, Integer.parseInt(frequency),
                ticker, Float.parseFloat(amount), weightage, Float.parseFloat(fee));

            mainFrame.jSplitPane1.setRightComponent(afterAddingStockFrame.jPanel2);
            callAddAnotherStock();
          } else {
            JOptionPane.showMessageDialog(null,
                "Few inputs are invalid, please check the inputs",
                "Invalid inputs while adding a stock", JOptionPane.ERROR_MESSAGE);
          }
        } catch (ParseException ex) {
          throw new RuntimeException(ex);
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      });

    });
  }

  @Override
  public void callAddAnotherStock() {
    afterAddingStockFrame.addAnotherStock.addActionListener(addEvent -> {
      enterTheTickerValue();
    });
  }

  @Override
  public void populateTableFromArrayList(ArrayList<StocksImpl> stockList,
      DefaultTableModel model) {
    model.setRowCount(0);
    stockList.forEach(stock -> {
      Object[] row = new Object[10];
      row[0] = stock.getCompany();
      row[1] = stock.getDate();
      row[2] = stock.getIsFuture() ? "Yet to update" : stock.getOpen();
      row[3] = stock.getIsFuture() ? "Yet to update" : stock.getHigh();
      row[4] = stock.getIsFuture() ? "Yet to update" : stock.getLow();
      row[5] = stock.getIsFuture() ? "Yet to update" : stock.getClose();
      row[6] = stock.getIsFuture() ? "Yet to update" : stock.getVolume();
      row[7] = stock.getIsFuture() ? "Yet to update" : stock.getShares();
      row[8] = stock.getIsFuture() ? "Yet to update" : stock.getShares() * stock.getClose();
      row[9] = stock.getCommisionFee();
      model.addRow(row);
    });
  }

  @Override
  public void populateTable(HashMap<String, TreeMap<Date, StocksImpl>> portfolio,
      DefaultTableModel model) {
    model.setRowCount(0);
    portfolio.forEach((comp, stockList) -> {
      stockList.forEach((date, stock) -> {
        Object[] row = new Object[10];
        row[0] = stock.getCompany();
        row[1] = stock.getDate();
        row[2] = stock.getIsFuture() ? "Yet to update" : stock.getOpen();
        row[3] = stock.getIsFuture() ? "Yet to update" : stock.getHigh();
        row[4] = stock.getIsFuture() ? "Yet to update" : stock.getLow();
        row[5] = stock.getIsFuture() ? "Yet to update" : stock.getClose();
        row[6] = stock.getIsFuture() ? "Yet to update" : stock.getVolume();
        row[7] = stock.getIsFuture() ? "Yet to update" : stock.getShares();
        row[8] = stock.getIsFuture() ? "Yet to update" : stock.getShares() * stock.getClose();
        row[9] = stock.getCommisionFee();
        model.addRow(row);
      });
    });
  }

  @Override
  public void populateTableFromTreeMap(TreeMap<Date, StocksImpl> portfolio,
      DefaultTableModel model) {
    model.setRowCount(0);
    portfolio.forEach((comp, stock) -> {
      Object[] row = new Object[10];
      row[0] = stock.getCompany();
      row[1] = stock.getDate();
      row[2] = stock.getIsFuture() ? "Yet to update" : stock.getOpen();
      row[3] = stock.getIsFuture() ? "Yet to update" : stock.getHigh();
      row[4] = stock.getIsFuture() ? "Yet to update" : stock.getLow();
      row[5] = stock.getIsFuture() ? "Yet to update" : stock.getClose();
      row[6] = stock.getIsFuture() ? "Yet to update" : stock.getVolume();
      row[7] = stock.getIsFuture() ? "Yet to update" : stock.getShares();
      row[8] = stock.getIsFuture() ? "Yet to update" : stock.getShares() * stock.getClose();
      row[9] = stock.getCommisionFee();
      model.addRow(row);
    });
  }

  @Override
  public void viewPortfolio() {
    HashMap<String, TreeMap<Date, StocksImpl>> portfolio = features.getPortfolio();
    populateTable(portfolio, (DefaultTableModel) tableView.portfolioTable.getModel());
    mainFrame.jSplitPane1.setRightComponent(tableView.jScrollPane1);
  }

}
