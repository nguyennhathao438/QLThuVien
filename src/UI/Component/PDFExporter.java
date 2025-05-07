/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Component;

/**
 *
 * @author Nghia0605
 */
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PDFExporter {

    public static void exportTableToPDF(JComponent parent, JTable table, String titleText, String reportAuthor) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file PDF");

        int userSelection = fileChooser.showSaveDialog(parent);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getAbsolutePath().endsWith(".pdf")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
            }

            try {
                Document document = new Document(PageSize.A4, 36, 36, 54, 36);
                PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
                document.open();

                // Font Unicode hỗ trợ tiếng Việt
                BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font fontTitle = new Font(baseFont, 18, Font.BOLD);
                Font fontCell = new Font(baseFont, 12);
                Font fontFooter = new Font(baseFont, 12, Font.ITALIC);

                // Tiêu đề
                if (titleText != null && !titleText.trim().isEmpty()) {
                    Paragraph title = new Paragraph(titleText, fontTitle);
                    title.setAlignment(Element.ALIGN_CENTER);
                    title.setSpacingAfter(20f);
                    document.add(title);
                }

                // Bảng dữ liệu
                PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
                pdfTable.setWidthPercentage(100);
                pdfTable.setHorizontalAlignment(Element.ALIGN_CENTER);

                // Tiêu đề cột
                for (int i = 0; i < table.getColumnCount(); i++) {
                    PdfPCell cell = new PdfPCell(new Phrase(table.getColumnName(i), fontCell));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    pdfTable.addCell(cell);
                }

                // Dữ liệu
                for (int row = 0; row < table.getRowCount(); row++) {
                    for (int col = 0; col < table.getColumnCount(); col++) {
                        Object value = table.getValueAt(row, col);
                        PdfPCell cell = new PdfPCell(new Phrase(value != null ? value.toString() : "", fontCell));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        pdfTable.addCell(cell);
                    }
                }

                document.add(pdfTable);

                // Chữ ký và ngày xuất
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);

                String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

                Paragraph rightAlign = new Paragraph("Ngày xuất: " + currentDate, fontFooter);
                rightAlign.setAlignment(Element.ALIGN_RIGHT);
                rightAlign.setSpacingBefore(20f);
                document.add(rightAlign);

                if (reportAuthor != null && !reportAuthor.isEmpty()) {
                    Paragraph signature = new Paragraph("Người lập báo cáo: " + reportAuthor, fontFooter);
                    signature.setAlignment(Element.ALIGN_RIGHT);
                    document.add(signature);
                }

                document.close();
                JOptionPane.showMessageDialog(parent, "Xuất PDF thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(parent, "Lỗi khi xuất PDF!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}