package com.Reports.ReportsProject.Services;

import com.Reports.ReportsProject.Entities.SampleData;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TableGenerationService {
    public PdfPTable createDataTable(List<SampleData> sampleDataList) {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        PdfPCell headerCell = new PdfPCell(new Phrase("Name", headerFont));
        headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerCell.setPadding(5f);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase("Email", headerFont));
        headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerCell.setPadding(5f);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase("Gender", headerFont));
        headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerCell.setPadding(5f);
        table.addCell(headerCell);

        Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
        for (SampleData data : sampleDataList) {
            table.addCell(createStyledCell(data.getName(), dataFont));
            table.addCell(createStyledCell(data.getEmail(), dataFont));
            table.addCell(createStyledCell(data.getGender(), dataFont));
        }

        return table;
    }
    public PdfPCell createStyledCell(String content, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setPadding(5f);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorderWidth(0.5f);
        return cell;
    }
}
