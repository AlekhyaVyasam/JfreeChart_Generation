package com.Reports.ReportsProject.Services;

import com.Reports.ReportsProject.Entities.SampleData;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ChartGenerationService {

    public ByteArrayOutputStream generateGenderChart(List<SampleData> sampleDataList) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int maleCount = 0;
        int femaleCount = 0;

        for (SampleData data : sampleDataList) {
            if ("Male".equalsIgnoreCase(data.getGender())) {
                maleCount++;
            } else if ("Female".equalsIgnoreCase(data.getGender())) {
                femaleCount++;
            }
        }
        dataset.addValue(maleCount, "Gender", "Male");
        dataset.addValue(femaleCount, "Gender", "Female");
        JFreeChart chart = ChartFactory.createBarChart(
                "Gender Distribution",
                "Gender",
                "Count",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        BufferedImage chartImage = chart.createBufferedImage(500, 300, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            javax.imageio.ImageIO.write(chartImage, "PNG", byteArrayOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream;
    }

    public ByteArrayOutputStream generateLineChart(List<SampleData> sampleDataList) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int maleCount = 0;
        int femaleCount = 0;

        for (SampleData data : sampleDataList) {
            if ("Male".equalsIgnoreCase(data.getGender())) {
                maleCount++;
            } else if ("Female".equalsIgnoreCase(data.getGender())) {
                femaleCount++;
            }
        }
        dataset.addValue(maleCount, "Gender", "Male");
        dataset.addValue(femaleCount, "Gender", "Female");

        // Create the chart
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Gender Count",  // Title
                "Gender",               // X-axis Label
                "Count",                // Y-axis Label
                dataset,                // Dataset
                PlotOrientation.VERTICAL, // Orientation
                true,                   // Include legend
                true,                   // Include tooltips
                false                   // URLs
        );

        // Customize the chart
        // 1. Set background color for the chart
        lineChart.setBackgroundPaint(Color.white);

        // 2. Customize the plot
        CategoryPlot plot = lineChart.getCategoryPlot();
        plot.setBackgroundPaint(Color.pink); // Set plot background
        plot.setRangeGridlinePaint(Color.black); // Gridline color

        // 3. Customize axis
        plot.getDomainAxis().setLabelPaint(Color.BLUE); // X-axis label color
        plot.getDomainAxis().setTickLabelPaint(Color.DARK_GRAY); // X-axis tick color
        plot.getRangeAxis().setLabelPaint(Color.RED); // Y-axis label color
        plot.getRangeAxis().setTickLabelPaint(Color.DARK_GRAY); // Y-axis tick color

        // 4. Customize line and shape renderer
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE); // Line color for the first series
        renderer.setSeriesShapesVisible(0, true); // Show shapes for the first series
        plot.setRenderer(renderer);

        // Convert the chart to an image
        BufferedImage chartImage = lineChart.createBufferedImage(500, 300, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            javax.imageio.ImageIO.write(chartImage, "PNG", byteArrayOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream;
    }


    public ByteArrayOutputStream generateGenderPieChart(List<SampleData> sampleDataList) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        int maleCount = 0;
        int femaleCount = 0;

        for (SampleData data : sampleDataList) {
            if ("Male".equalsIgnoreCase(data.getGender())) {
                maleCount++;
            } else if ("Female".equalsIgnoreCase(data.getGender())) {
                femaleCount++;
            }
        }

        // Populate dataset
        dataset.setValue("Male", maleCount);
        dataset.setValue("Female", femaleCount);

        // Generate Pie Chart
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Gender Count ", // Chart Title
                dataset,               // Dataset
                true,                  // Include Legend
                true,
                false
        );

        // Convert to ByteArrayOutputStream
        BufferedImage chartImage = pieChart.createBufferedImage(500, 300, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            javax.imageio.ImageIO.write(chartImage, "PNG", byteArrayOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream;
    }

}
