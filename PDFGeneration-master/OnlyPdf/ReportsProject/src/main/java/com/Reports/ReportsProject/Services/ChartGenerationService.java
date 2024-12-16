package com.Reports.ReportsProject.Services;

import com.Reports.ReportsProject.Entities.SampleData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;

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

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Gender Distribution",
                "Gender",
                "Count",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        BufferedImage chartImage = lineChart.createBufferedImage(500, 300, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            javax.imageio.ImageIO.write(chartImage, "PNG", byteArrayOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream;
    }
}
