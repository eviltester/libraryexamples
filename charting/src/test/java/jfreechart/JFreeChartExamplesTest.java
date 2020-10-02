package jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.svg.SVGGraphics2D;
import org.jfree.svg.SVGUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;


public class JFreeChartExamplesTest {

    /*
        Useful links:
        - http://www.jfree.org/forum
        - http://www.jfree.org/jfreechart/
        - https://github.com/jfree/jfreechart
        - https://github.com/jfree/jfree-demos
     */

    static TimeSeries series = new TimeSeries("Values");

    static File outputPath;

    @BeforeAll
    static void dataSetup(){
        // JFreeChart will order dates, xChart displays data in the order provided
        for(int sample=1; sample < 10; sample++){
            LocalDate localDate = LocalDate.now().withDayOfMonth(sample);
            int year  = localDate.getYear();
            int month = localDate.getMonthValue();
            int day   = localDate.getDayOfMonth();
            int value = Long.valueOf(1000 + Math.round(Math.random()*100)).intValue();

            System.out.println(String.format("%d/%02d/%02d - %d", year, month, day, value));

            series.add(new Day(day, month, year),
                    Integer.valueOf(value).doubleValue());

        }

        outputPath = new File(System.getProperty("user.dir"), "/output/images");
        outputPath.mkdirs();

    }

    @Test
    void exampleXYGraph() throws IOException {

        TimeSeriesCollection dataset = new TimeSeriesCollection();

        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Values at Date",
                "Date", "Values", dataset);


        SVGGraphics2D g2 = new SVGGraphics2D(600, 400);
        g2.setRenderingHint(JFreeChart.KEY_SUPPRESS_SHADOW_GENERATION, true);
        Rectangle r = new Rectangle(0, 0, 600, 400);
        chart.draw(g2, r);

        File svg = new File(outputPath, "jfreechartxychart.svg");
        SVGUtils.writeToSVG(svg, g2.getSVGElement());

        BufferedImage image = new BufferedImage(600, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics2D bmp = image.createGraphics();

        bmp.setRenderingHint(JFreeChart.KEY_SUPPRESS_SHADOW_GENERATION, true);
        r = new Rectangle(0, 0, 600, 400);
        chart.draw(bmp, r);

        File png = new File(outputPath, "jfreechartxychart.png");
        ChartUtils.saveChartAsPNG(png, chart, 600, 400);
    }

    @Test
    void exampleBarGraph() throws IOException {

        TimeSeriesCollection dataset = new TimeSeriesCollection();

        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYBarChart(
                "Values at Date",
                "Date", true, "Values", dataset);


        SVGGraphics2D g2 = new SVGGraphics2D(600, 400);
        g2.setRenderingHint(JFreeChart.KEY_SUPPRESS_SHADOW_GENERATION, true);
        Rectangle r = new Rectangle(0, 0, 600, 400);
        chart.draw(g2, r);

        File svg = new File(outputPath, "jfreechartbarchart.svg");
        SVGUtils.writeToSVG(svg, g2.getSVGElement());


        BufferedImage image = new BufferedImage(600, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics2D bmp = image.createGraphics();

        bmp.setRenderingHint(JFreeChart.KEY_SUPPRESS_SHADOW_GENERATION, true);
        r = new Rectangle(0, 0, 600, 400);
        chart.draw(bmp, r);

        File png = new File(outputPath, "jfreechartbarchart.png");
        ChartUtils.saveChartAsPNG(png, chart, 600, 400);
    }

}
