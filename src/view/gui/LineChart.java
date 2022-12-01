package view.gui;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

/**
 * JPanel design for displaying the stocks.
 */
public class LineChart extends JPanel {


  /**
   * The update data functions updates the ui components or saves the data from the ui components
   * and displays the result in corresponding component.
   *
   * @param portfolioPerformance is an object of the PortfolioPerformance
   */
  public void updateData(TreeMap<LocalDate, Integer> portfolioPerformance) {

    RenderLineChart chart = new RenderLineChart(
        "Performance of the Portfolio",
        "Performance of the Portfolio",
        createDataset(portfolioPerformance));

    chart.pack();
    RefineryUtilities.centerFrameOnScreen(chart);
    chart.setVisible(true);
    chart.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

  private DefaultCategoryDataset createDataset(TreeMap<LocalDate, Integer> data) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    ArrayList<LocalDate> keys = new ArrayList<>(data.keySet());
    Collections.reverse(keys);
    for (LocalDate key : keys) {
      dataset.addValue(data.get(key), "Portfolio", key.toString());
    }
    return dataset;
  }

  /**
   * Renders line chart after loading the data.
   */
  public class RenderLineChart extends JFrame {

    private RenderLineChart(String applicationTitle, String chartTitle,
        DefaultCategoryDataset dataset) {
      super(applicationTitle);
      JFreeChart lineChart = ChartFactory.createLineChart(
          chartTitle,
          "dates", "Portfolio Value", dataset, PlotOrientation.VERTICAL,
          true, true, false);

      ChartPanel chartPanel = new ChartPanel(lineChart);
      chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
      setContentPane(chartPanel);
    }

  }

}