package com.Reports.ReportsProject.Services;

import com.Reports.ReportsProject.Entities.MulVal;
import com.Reports.ReportsProject.Repositories.MulValRepository;
import lombok.RequiredArgsConstructor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
public class MulValChartService {

    final MulValRepository repository;

    @Value("${chart.width}")
    private int chartWidth;

    @Value("${chart.height}")
    private int chartHeight;

    @Value("${chart.series1.color}")
    private String series1Color;

    @Value("${chart.series2.color}")
    private String series2Color;

    @Value("${chart.series3.color}")
    private String series3Color;

    @Value("${chart.series4.color}")
    private String series4Color;

    @Value("${chart.background.color}")
    private String backgroundColor;

    @Value("${chart.series1.shapes.visible}")
    private boolean series1ShapesVisible;

    @Value("${chart.series2.shapes.visible}")
    private boolean series2ShapesVisible;

    @Value("${chart.series3.shapes.visible}")
    private boolean series3ShapesVisible;

    @Value("${chart.series4.shapes.visible}")
    private boolean series4ShapesVisible;

    // Renamed method from generateLineChart to generateChart
    public byte[] generateLineChart() throws IOException {
        List<MulVal> records = repository.findAll();

        if (records.isEmpty()) {
            throw new IOException("No records found in the database.");
        }

        List<MulVal> temp1 = filterBySensor(records, "temp1");
        List<MulVal> temp2 = filterBySensor(records, "temp2");
        List<MulVal> temp3 = filterBySensor(records, "temp3");
        List<MulVal> temp4 = filterBySensor(records, "temp4");

        if (temp1.isEmpty() || temp2.isEmpty() || temp3.isEmpty() || temp4.isEmpty()) {
            throw new IOException("Insufficient data for one or more sensors.");
        }

        String temp1Stats = getStats(temp1);
        String temp2Stats = getStats(temp2);
        String temp3Stats = getStats(temp3);
        String temp4Stats = getStats(temp4);

        TimeSeries series1 = createTimeSeries("Temp1 (" + temp1Stats + ")", temp1);
        TimeSeries series2 = createTimeSeries("Temp2 (" + temp2Stats + ")", temp2);
        TimeSeries series3 = createTimeSeries("Temp3 (" + temp3Stats + ")", temp3);
        TimeSeries series4 = createTimeSeries("Temp4 (" + temp4Stats + ")", temp4);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        dataset.addSeries(series4);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Temperature Readings",
                "Time",
                "Temperature (°C)",
                dataset,
                true,
                true,
                false
        );

        customizeChart(chart);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedImage chartImage = chart.createBufferedImage(chartWidth, chartHeight);
        ImageIO.write(chartImage, "PNG", byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }

    private void customizeChart(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.decode(series1Color));
        renderer.setSeriesShapesVisible(0, series1ShapesVisible);

        renderer.setSeriesPaint(1, Color.decode(series2Color));
        renderer.setSeriesShapesVisible(1, series2ShapesVisible);

        renderer.setSeriesPaint(2, Color.decode(series3Color));
        renderer.setSeriesShapesVisible(2, series3ShapesVisible);

        renderer.setSeriesPaint(3, Color.decode(series4Color));
        renderer.setSeriesShapesVisible(3, series4ShapesVisible);

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.decode(backgroundColor));
        plot.setOutlinePaint(Color.BLACK);
        plot.setOutlineStroke(new BasicStroke(1.5f));
    }

    private List<MulVal> filterBySensor(List<MulVal> records, String sensorName) {
        return records.stream()
                .filter(record -> record.getSensorName().equals(sensorName))
                .collect(Collectors.toList());
    }

    private TimeSeries createTimeSeries(String name, List<MulVal> data) {
        TimeSeries series = new TimeSeries(name);
        data.forEach(record -> {
            java.util.Date date = java.util.Date.from(record.getTimestamp().atZone(java.time.ZoneId.systemDefault()).toInstant());
            series.add(new Second(date), record.getTemperatureValue());
        });
        return series;
    }

    private String getStats(List<MulVal> data) {
        double max = data.stream().mapToDouble(MulVal::getTemperatureValue).max().orElse(Double.NaN);
        double min = data.stream().mapToDouble(MulVal::getTemperatureValue).min().orElse(Double.NaN);
        double avg = data.stream().mapToDouble(MulVal::getTemperatureValue).average().orElse(Double.NaN);

        return String.format("Max: %.2f°C, Min: %.2f°C, Avg: %.2f°C", max, min, avg);
    }
}
