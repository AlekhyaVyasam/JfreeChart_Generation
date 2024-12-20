package com.Reports.ReportsProject.Services;

import com.Reports.ReportsProject.Config.SensorValProperties;
import com.Reports.ReportsProject.Entities.SensorData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

@Service
public class LineChartService {

    @Autowired
    private SensorValProperties sensorValProperties; // Injecting the properties class

    public ByteArrayOutputStream generateLineChart(List<SensorData> sensorDataList) {

        String chartTitle = sensorValProperties.getTitle(); // Getting title from properties
        int width = sensorValProperties.getWidth();
        int height = sensorValProperties.getHeight();
        String backgroundColor = sensorValProperties.getBackgroundColor();
        String xAxisDateFormat = sensorValProperties.getXAxisDateFormat();

        TimeSeries timeSeries = new TimeSeries("Temperature Sensor");

        for (SensorData data : sensorDataList) {
            if ("Temperature Sensor".equalsIgnoreCase(data.getSensorName())) {
                if (data.getTimestamp() != null && data.getValue() != null) {
                    Date date = Date.from(data.getTimestamp().atZone(java.time.ZoneId.systemDefault()).toInstant());
                    timeSeries.add(new Millisecond(date), data.getValue());
                }
            }
        }

        if (timeSeries.isEmpty()) {
            throw new IllegalArgumentException("No data available for the selected sensor type.");
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(timeSeries);

        // Create Chart with dynamic title from properties
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                chartTitle, // Title from properties
                "Time",                   // X-axis label
                "Value",                  // Y-axis label
                dataset,                  // Dataset
                true,                     // Show legend
                true,                     // Show tooltips
                false                     // Show URLs
        );

        XYPlot plot = chart.getXYPlot();

        // Set custom colors and shapes visibility (using properties)
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.decode(sensorValProperties.getSeriesColor())); // Use color from properties
        renderer.setSeriesShapesVisible(0, sensorValProperties.isSeriesShapesVisible()); // Use visibility from properties
        plot.setRenderer(renderer);

        DateAxis xAxis = new DateAxis("Time"); // Customize the x-axis
        xAxis.setDateFormatOverride(new SimpleDateFormat(xAxisDateFormat)); // Use format from properties
        plot.setDomainAxis(xAxis);

        // Set background color (from properties)
        plot.setBackgroundPaint(Color.decode(backgroundColor));
        plot.setDomainGridlinePaint(Color.black);
        plot.setRangeGridlinePaint(Color.black);

        // Convert the chart to a PNG image
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            javax.imageio.ImageIO.write(chart.createBufferedImage(width, height), "PNG", byteArrayOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream;
    }
}
