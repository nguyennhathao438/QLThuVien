/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EXCEL;

import Model.Sach;
import Model.TheLoai;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author tung7
 */
public class ExportExcel {
     public static void xuatExcel(ArrayList<Sach> ds, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Sách");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Mã sách");
            header.createCell(1).setCellValue("Tên sách");
            header.createCell(2).setCellValue("Năm xuất bản");
            header.createCell(3).setCellValue("Số lượng");
            header.createCell(4).setCellValue("Đơn giá");
            header.createCell(5).setCellValue("Mã tác giả");
            header.createCell(6).setCellValue("Mã thể loại");

            int rowIndex = 1;
            for (Sach s : ds) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(s.getMaSach());
                row.createCell(1).setCellValue(s.getTenSach());
                row.createCell(2).setCellValue(s.getNamXuatBan());
                row.createCell(3).setCellValue(s.getSoLuong());
                row.createCell(4).setCellValue(s.getDonGia());
                row.createCell(5).setCellValue(s.getMaTacGia());
                row.createCell(6).setCellValue(s.getMaTheLoai());
            }

            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    public static ArrayList<Sach> docExcel(String filePath)
    {
        ArrayList<Sach> dsSach = new ArrayList<>();
        try(FileInputStream fis = new FileInputStream(filePath);
                Workbook wr = new XSSFWorkbook(fis))
        {
            Sheet sheet = wr.getSheetAt(0);
            for(int i=1; i<= sheet.getLastRowNum(); i++)
            {
                Sach s = new Sach();
                Row row = sheet.getRow(i);
                s.setMaSach(row.getCell(0).getStringCellValue());
                s.setTenSach(row.getCell(1).getStringCellValue());
                s.setNamXuatBan((int)row.getCell(2).getNumericCellValue());
                s.setSoLuong((int)row.getCell(3).getNumericCellValue());
                s.setDonGia(row.getCell(4).getNumericCellValue());
                s.setMaTacGia(row.getCell(5).getStringCellValue());
                s.setMaTheLoai(row.getCell(6).getStringCellValue());
                s.setTrangThai(1);
                dsSach.add(s);
            }
        } catch (IOException ex) {
             Logger.getLogger(ExportExcel.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        return dsSach;
    }
}
