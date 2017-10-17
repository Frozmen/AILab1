import java.util.ArrayList;
import java.util.List;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ClustersChart extends JFrame {
	
	List<Cluster> clusters;
	
	public ClustersChart(List<Cluster> clusters){
		this.clusters = clusters;
		initUI();
	}
	
	private void initUI() {

        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Clusters");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private XYDataset createDataset() {

    	List<XYSeries> allSeries = new ArrayList<>();
        for(Cluster cluster : clusters){
        	XYSeries series = new XYSeries("Cluster "  + cluster.id);
        	for(PokemonModel model : cluster.datas){
        		series.add(model.getX(), model.getY());
        	}
        	allSeries.add(series);
        	
        	XYSeries seriesMas = new XYSeries("Clus"  + cluster.id + " mas");
        	seriesMas.add(cluster.centerX, cluster.centerY);
        	allSeries.add(seriesMas);
        }
        

        XYSeriesCollection dataset = new XYSeriesCollection();
        for(XYSeries series : allSeries){
        	dataset.addSeries(series);
        }

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYAreaChart(
                "Clusters", 
                "Speed atack", 
                "Speed deff", 
                dataset, 
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false 
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesLinesVisible(1, false);
        renderer.setSeriesLinesVisible(2, false);
        renderer.setSeriesLinesVisible(3, false);
        renderer.setSeriesLinesVisible(4, false);
        renderer.setSeriesLinesVisible(5, false);
        renderer.setSeriesLinesVisible(6, false);
        renderer.setSeriesLinesVisible(7, false);

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);


        chart.setTitle(new TextTitle("Clusters",
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;

    }
    
    public void draw(){
    	SwingUtilities.invokeLater(() -> {
            this.setVisible(true);
        });
    }

}
