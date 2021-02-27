package com.spring.entity;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class DocumentoReporte {

	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Documento> listDocuments;
    
    public DocumentoReporte(List<Documento> listDocuments) {
        this.listDocuments = listDocuments;
        workbook = new XSSFWorkbook();
    }
    
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);
        
         
        createCell(row, 0, "N° Documento", style);
        createCell(row, 1, "Fecha de Registro", style);
        createCell(row, 2, "Encargados", style);          
        createCell(row, 3, "Nombre Original", style);
        createCell(row, 4, "Estado", style);
         
    }
    
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
    
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);
                 
        for (Documento documets : listDocuments) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, documets.getIddocumento(), style);
            createCell(row, columnCount++, convertDateToString(documets.getFecha_registro()), style);
            createCell(row, columnCount++, documets.getUsers().toString(), style);
            createCell(row, columnCount++, documets.getNombre_original(), style);
            createCell(row, columnCount++, documets.getEstado(), style);
             
        }
    }
    
    public String 
    convertDateToString(Date date) 
    { 
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
        
        // Convert the date into a 
        // string using format() method 
        String dateToString = df.format(date); 
  
        // Return the result 
        return (dateToString); 
    } 
    
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}
