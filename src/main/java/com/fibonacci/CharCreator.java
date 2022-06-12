package com.fibonacci;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.BorderFactory;
import javax.swing.JFrame;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

public class CharCreator extends JFrame{
    private int howMany;

    public int getHowMany() {
        return howMany;
    }

    public void setHowMany(int howMany) {
        this.howMany = howMany;
    }

    public CharCreator(int howMany, boolean first) {
        this.setHowMany(howMany);
        if(first){
            initUI(first);
        }
        if(!first){
            initUI(first);
        }
        
    }
    

    public void initUI(boolean first) {

        if(first){
            XYDataset dataset = createDataset(); 
            JFreeChart chart = createChart(dataset);
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            chartPanel.setBackground(Color.white);
            add(chartPanel);
            pack();
            setTitle("Line chart");
            setLocationRelativeTo(null);
           // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           System.out.println("\nAlmost done!");
        }
        
        if(!first){
            XYDataset secondDataSet = secondDataset();
            JFreeChart secondChart = createChart(secondDataSet);
            ChartPanel chartPanel2 = new ChartPanel(secondChart);
            chartPanel2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            chartPanel2.setBackground(Color.LIGHT_GRAY);
            add(chartPanel2);
            pack();
            setTitle("Line chart");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            System.out.println("\n Getting the charts together");
        }
        

        
    }
    public XYDataset secondDataset(){
        XYSeries recursive = new XYSeries("Recursive"); 
        long[] temp = new long[getHowMany()];
        double[] timeTable = new double[getHowMany()];
        for(int i = 0; i<getHowMany(); i++){
            try{
            long startTime = System.nanoTime();
            temp[i]=App.fibrecl(i);
            long startTime2 = System.nanoTime();
            timeTable[i]=((startTime2 - startTime) / Math.pow(10, 9));
            recursive.add(temp[i], timeTable[i]);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        XYSeriesCollection secondDataSet = new XYSeriesCollection();
        secondDataSet.addSeries(recursive);
        return secondDataSet;
    }
    public XYDataset createDataset() {
        XYSeries series = new XYSeries("Iterative");
        long[] temp = new long[getHowMany()];
        double[] timeTable = new double[getHowMany()];
        for(int i = 0; i<getHowMany(); i++){
            try{
            long startTime = System.nanoTime();
            temp[i]=App.fibiter(i);
            long startTime2 = System.nanoTime();
            timeTable[i]=((startTime2 - startTime) / Math.pow(10, 9));
            series.add(temp[i],timeTable[i]);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    public JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Time to Calculate Fibonacci Function",
                "Fibonacci number",
                "time in seconds",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Time to Calculate Fibonacci Function",
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;
    }
}

