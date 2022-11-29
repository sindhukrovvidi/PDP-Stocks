package view;

import controller.Features;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.text.ParseException;
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
import view.gui.MainFrame;
import view.gui.TableView;
import view.gui.ViewPortfolioFrame;
import view.gui.TotalCompositionFrame;

public class JFrameStocksView extends JFrame implements GUIInterface {

  protected CreatePortfolioFrame createPortfolioFrame;
  protected AddSingleStockframe addSingleStockframe;

  protected DollarCostAveraging dollarCostAveraging;

  protected JFrame createPortfolio;
  protected JFrame singleStockAddFrame;
  protected JFrame dollarCostAveragingFrame;

  protected AfterAddingStockFrame afterAddingStockFrame = new AfterAddingStockFrame();

  protected MainFrame mainFrame = new MainFrame();

  protected TableView tableView = new TableView();

  protected ViewPortfolioFrame viewPortfolioFrame = new ViewPortfolioFrame();

  protected TotalCompositionFrame totalCompositionFrame = new TotalCompositionFrame();

  protected BuyStocksFrame buyStocksFrame = new BuyStocksFrame();


  protected String inputText;
  protected Features features;

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
    System.out.println("initialising framessssssssss");
    createPortfolioFrame = new CreatePortfolioFrame();
    addSingleStockframe = new AddSingleStockframe();
    dollarCostAveraging = new DollarCostAveraging();
    tableView = new TableView();
    viewPortfolioFrame = new ViewPortfolioFrame();
    totalCompositionFrame = new TotalCompositionFrame();
    buyStocksFrame = new BuyStocksFrame();

    createPortfolio = createPortfolioFrame.getCreatePortfolioFrame();
    singleStockAddFrame = addSingleStockframe.getFrame();
    dollarCostAveragingFrame = dollarCostAveraging.getFrame();
//    mainFrame = new MainFrame();

  }

  public void displayStarterMenu() throws IOException {

    mainFrame.setVisible(true);

    // create portfolio
    mainFrame.createPortfolioButton.addActionListener(e -> {
      try {
        createPortfolioFile();
      } catch (FileAlreadyExistsException ex) {
        throw new RuntimeException(ex);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    // view portfolio
    mainFrame.viewPortfolioButton.addActionListener(e -> {
      viewPortfolioFrame();
      viewPortfolioFrame.portfolioFilesDropDown.addActionListener(event -> {
        String portfolio = (String) viewPortfolioFrame.portfolioFilesDropDown.getSelectedItem();
        if (portfolio != null) {
          System.out.println("Got the portfolio name from dropdown...." + portfolio);
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

    // buy stocks
    mainFrame.buyStocksButton.addActionListener(e-> {
      initialLiseFrames();
      generateDropDown(
          (DefaultComboBoxModel) buyStocksFrame.portfolioFilesDropDown.getModel(),
          features.getPortfolioNames());
      mainFrame.jSplitPane1.setRightComponent(buyStocksFrame.jPanel1);
      buyStocksFrame.portfolioFilesDropDown.addActionListener(selected -> {
        String portfolio = (String) buyStocksFrame.portfolioFilesDropDown.getSelectedItem();
        try {
          System.out.println("Rendering view for portfolio...." + portfolio);
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

    // total composition
    mainFrame.totalCompositionButton.addActionListener((e -> {
      calculateComposition(false);
    }));


    // total cost basis
    mainFrame.costBasisButton.addActionListener((e -> {
      calculateComposition(true);
    }));


  }

  private void calculateComposition(boolean isCostBasis) {
    initialLiseFrames();
    generateDropDown(
        (DefaultComboBoxModel) totalCompositionFrame.portfolioFilesDropDown.getModel(),
        features.getPortfolioNames());
    mainFrame.jSplitPane1.setRightComponent(totalCompositionFrame.jPanel1);
    AtomicReference<String> portfolio = new AtomicReference<>("");

    totalCompositionFrame.calculateCompositionButton.addActionListener(cal -> {
      String date = totalCompositionFrame.dateTextField.getText();
      portfolio.set((String) totalCompositionFrame.portfolioFilesDropDown.getSelectedItem());
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
                "Total composition of the portfolio is 0 as you do not have any stocks purchased by "
                    + "then.");
            DefaultTableModel model =
                (DefaultTableModel) totalCompositionFrame.viewPortfolioTable.getModel();
            model.setRowCount(0);
          } else {
            totalCompositionFrame.displayText.setText(
                "Total value till " + date + " is: " + map.get("final_total_value") + "\n" +
                    ". Following is the list of stocks till date: " + date + "\n");

            populateTableFromArrayList((ArrayList<StocksImpl>) map.get("inDateList"),
                (DefaultTableModel) totalCompositionFrame.viewPortfolioTable.getModel());
          }
        }
      } catch (ParseException ex) {
        throw new RuntimeException(ex);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
  }

  private void viewPortfolioFrame() {
    generateDropDown((DefaultComboBoxModel) viewPortfolioFrame.portfolioFilesDropDown.getModel(),
        features.getPortfolioNames());
    mainFrame.jSplitPane1.setRightComponent(viewPortfolioFrame.jPanel1);
  }

  private void generateDropDown(DefaultComboBoxModel dropDown, String[] items) {
    dropDown.removeAllElements();
//    dropDown.addElement("-- Portfolios --");
    for (String item : items) {
      dropDown.addElement(item);
    }
  }

  private void createPortfolioFile() throws IOException {
    System.out.println("createPortfolioFile is called");
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

  private void enterTheTickerValue() {
    initialLiseFrames();
    mainFrame.jSplitPane1.setRightComponent(createPortfolioFrame.panel);
//    initialLiseFrames();
    System.out.println("enterTheTickerValue is called");

    createPortfolioFrame.singleStockButton.addActionListener(e -> {

      System.out.println("Calliong createPortfolioFrame.singleStockButton");
      mainFrame.jSplitPane1.setRightComponent(addSingleStockframe.panel);

      addSingleStockframe.addStockButton.addActionListener(event -> {

        try {
          String tickerValue = addSingleStockframe.tickerValuetextField.getText();
          String date = addSingleStockframe.dateTextField.getText();
          String stocks = addSingleStockframe.stocksTextField.getText();
          String commissionFee = addSingleStockframe.commissionFeeTextField.getText();

          if (features.validateSingleStocksData(tickerValue, date, Integer.parseInt(stocks),
              Float.parseFloat(commissionFee))) {
            System.out.println("printing ticker value " + tickerValue);
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

        //     float investedAmount, String weightage, int fee,
        //      String lowerDate, String upperDate, int frequency
        try {

          boolean areValid = features.isValidateInputForMultiStocks(ticker,
              Float.parseFloat(amount),
              weightage,
              Float.parseFloat(fee), startDate, endDate, Integer.parseInt(frequency));
          if (areValid) {
            // save them to portfolio
            features.addDollarCostAveragingStocks(startDate, endDate, Integer.parseInt(frequency),
                ticker, Float.parseFloat(amount), weightage, Float.parseFloat(fee));
            // show main menu
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

      System.out.println("inside multi stock function");
    });
  }

  private void callAddAnotherStock() {
    System.out.println("Calledddd");
    afterAddingStockFrame.addAnotherStock.addActionListener(addEvent -> {
      enterTheTickerValue();
//              afterAddingStockFrame.setVisible(false);
    });
  }
//  public void modifyKeyListeners(JFrame frame, JTextField textfield) {
//    System.out.println("modifyKeyListeners");
//    frame.addKeyListener(new KeyAdapter() {
//      @Override
//      public void keyPressed(KeyEvent e) {
//        super.keyPressed(e);
//        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB) {
//          System.out.println("Trying to print tickervalue" + textfield.getText());
//          String data = textfield.getText();
//          setText(data);
//        }
//      }
//    });
//
//  }

  @Override
  public void addFeatures(Features features) {
//    createPortfolioFrame.singleStockButton.addActionListener(e -> {
//      System.out.println("xfgchgjkkhgfxcjhk");
//    });

  }

  @Override
  public void setText(String s) {
    inputText = s;
  }

  @Override
  public String getText() {
    return inputText;
  }

  @Override
  public void clearText() {
    setText("");
  }

  private void populateTableFromArrayList(ArrayList<StocksImpl> stockList,
      DefaultTableModel model) {
    model.setRowCount(0);
    stockList.forEach(stock -> {
      Object[] row = new Object[10];
      row[0] = stock.getCompany();
      row[1] = stock.getDate();
      row[2] = stock.getOpen();
      row[3] = stock.getHigh();
      row[4] = stock.getLow();
      row[5] = stock.getClose();
      row[6] = stock.getVolume();
      row[7] = stock.getShares();
      row[8] = stock.getShares() * stock.getClose();
      row[9] = stock.getCommisionFee();
      model.addRow(row);
    });
  }

  private void populateTable(HashMap<String, TreeMap<Date, StocksImpl>> portfolio,
      DefaultTableModel model) {
//    DefaultTableModel model = (DefaultTableModel) tableView.portfolioTable.getModel();
    model.setRowCount(0);
    portfolio.forEach((comp, stockList) -> {
      stockList.forEach((date, stock) -> {
        Object[] row = new Object[10];
        row[0] = stock.getCompany();
        row[1] = stock.getDate();
        row[2] = stock.getOpen();
        row[3] = stock.getHigh();
        row[4] = stock.getLow();
        row[5] = stock.getClose();
        row[6] = stock.getVolume();
        row[7] = stock.getShares();
        row[8] = stock.getShares() * stock.getClose();
        row[9] = stock.getCommisionFee();
        model.addRow(row);
      });
    });
  }

  private void viewPortfolio() {
    HashMap<String, TreeMap<Date, StocksImpl>> portfolio = features.getPortfolio();
    populateTable(portfolio, (DefaultTableModel) tableView.portfolioTable.getModel());
    mainFrame.jSplitPane1.setRightComponent(tableView.jScrollPane1);
  }

}
