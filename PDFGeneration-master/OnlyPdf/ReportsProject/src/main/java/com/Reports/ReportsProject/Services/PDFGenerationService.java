package com.Reports.ReportsProject.Services;

import com.Reports.ReportsProject.Entities.MulVal;
import com.Reports.ReportsProject.Entities.SensorData;
import com.Reports.ReportsProject.Services.MulValChartService;
import com.Reports.ReportsProject.Entities.SampleData;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PDFGenerationService {

    @Autowired
    private SampleDataService sampleDataService;

    @Autowired
    private ChartGenerationService chartGenerationService;
    @Autowired
    private SensorDataService sensorDataService;

    @Autowired TableGenerationService tableGenerationService;

    @Autowired
    private LineChartService lineChartService;

    @Autowired
    private MulValChartService mulValChartService;

    public ByteArrayOutputStream generatePDF() throws DocumentException, IOException {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        Paragraph title = new Paragraph("SAMPLE DATA FROM LOCAL DB", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph("\n"));

        List<SampleData> sampleDataList = sampleDataService.getAllSampleData();

        PdfPTable table = tableGenerationService.createDataTable(sampleDataList);
        document.add(table);

        ByteArrayOutputStream chartImage = chartGenerationService.generateGenderChart(sampleDataList);

        Image chartPdfImage = Image.getInstance(chartImage.toByteArray());
        chartPdfImage.setAlignment(Element.ALIGN_CENTER);
        document.add(chartPdfImage);


        ByteArrayOutputStream lineImage = chartGenerationService.generateLineChart(sampleDataList);

        Image linePdfImage = Image.getInstance(lineImage.toByteArray());
        chartPdfImage.setAlignment(Element.ALIGN_CENTER);
        document.add(linePdfImage);

        List<SensorData> sensorDataList = sensorDataService.getAllSensorData();
        ByteArrayOutputStream sensorLineImage = lineChartService.generateLineChart(sensorDataList);
        Image sensorLinePdfImage = Image.getInstance(sensorLineImage.toByteArray());
        sensorLinePdfImage.setAlignment(Element.ALIGN_CENTER);
        document.add(sensorLinePdfImage);


//        List<MulVal> mulValList = mulValChartService.repository.findAll();
//        if (!mulValList.isEmpty()) {
//            byte[] mulValChartImageBytes = mulValChartService.generateLineChart();
//            Image mulValChartImage = Image.getInstance(mulValChartImageBytes);
//            mulValChartImage.setAlignment(Element.ALIGN_CENTER);
//            document.add(mulValChartImage);
//        } else {
//            Paragraph noDataMessage = new Paragraph("No MulVal data available to generate the chart.",
//                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.RED));
//            noDataMessage.setAlignment(Element.ALIGN_CENTER);
//            document.add(noDataMessage);
//        }


        List<MulVal> mulValList = mulValChartService.repository.findAll();

        Image mulValChartImage = Image.getInstance(mulValChartService.generateLineChart());
        mulValChartImage.setAlignment(Element.ALIGN_CENTER);
        document.add(mulValChartImage);



        document.close();
        return byteArrayOutputStream;


    }
}
