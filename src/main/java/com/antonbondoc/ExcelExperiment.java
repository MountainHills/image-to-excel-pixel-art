package com.antonbondoc;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class ExcelExperiment {
    private static final int CHARACTER_WIDTH = 256;

    public static void main(String[] args) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Pixel Art");

        int width = 40;
        int height = 46;

        // Create cell style
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Creating the cells
        for (int i = 0; i < height; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < width; j++) {
                row.createCell(j).setCellStyle(style);
            }
        }

        for (int i = 0; i < width; i++) {
            sheet.setColumnWidth(i, 3 * CHARACTER_WIDTH);
        }

        try (OutputStream output = new FileOutputStream("output/excel-pixel-art.xlsx")) {
          workbook.write(output);
        } catch (Exception e) {
            // ignored
        }
    }
}
