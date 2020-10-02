package xchart;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.knowm.xchart.*;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XchartExamplesTest {

    /*
    Useful Links:

    - https://knowm.org/open-source/xchart/
    - https://github.com/knowm/XChart
    - https://knowm.org/open-source/xchart/xchart-example-code/

     */
    static List<Date> xData = new ArrayList<Date>();
    static List<Integer> yData = new ArrayList<Integer>();

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

            xData.add(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            yData.add(value);
        }

        outputPath = new File(System.getProperty("user.dir"), "/output/images");
        outputPath.mkdirs();

    }

    @Test
    void exampleXYGraph() throws IOException {

        XYChart xchart = new XYChartBuilder().
                width(600).height(400).
                title("Values at Date").
                xAxisTitle("Date").yAxisTitle("Values").
                build();

        xchart.getStyler().setLegendVisible(false);
        xchart.getStyler().setDatePattern("dd-MMM");

        XYSeries xseries = xchart.addSeries("Values", xData, yData);
        xseries.setMarker(SeriesMarkers.CIRCLE);
        xseries.setLineStyle(SeriesLines.SOLID);

        File svg = new File(outputPath, "xchartxychart.svg");
        VectorGraphicsEncoder.saveVectorGraphic(
                xchart,
                svg.getAbsolutePath(),
                VectorGraphicsEncoder.VectorGraphicsFormat.SVG);

        File png = new File(outputPath, "xchartxychart.png");
        BitmapEncoder.saveBitmap(
                xchart,
                png.getAbsolutePath(),
                BitmapEncoder.BitmapFormat.PNG);

    }

    @Test
    void exampleBarGraph() throws IOException {

        CategoryChart barchart = new CategoryChartBuilder().
                width(600).height(400).
                title("Values at Date")
                .xAxisTitle("Date").yAxisTitle("Values").
                        build();

        // Customize Chart
        barchart.getStyler().setLegendVisible(false);
        barchart.getStyler().setHasAnnotations(true);
        barchart.getStyler().setDatePattern("dd-MMM");

        // Series
        barchart.addSeries("values",xData, yData);

        File svg = new File(outputPath, "xchartbarchart.svg");
        VectorGraphicsEncoder.saveVectorGraphic(
                barchart,
                svg.getAbsolutePath(),
                VectorGraphicsEncoder.VectorGraphicsFormat.SVG);

        File png = new File(outputPath, "xchartbarchart.png");
        BitmapEncoder.saveBitmap(
                barchart,
                png.getAbsolutePath(),
                BitmapEncoder.BitmapFormat.PNG);
    }

}
