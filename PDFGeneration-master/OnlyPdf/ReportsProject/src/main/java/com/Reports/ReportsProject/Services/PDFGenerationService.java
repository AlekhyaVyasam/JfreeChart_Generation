package com.Reports.ReportsProject.Services;

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

    @Autowired TableGenerationService tableGenerationService;

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

        document.close();
        return byteArrayOutputStream;


    }
}
