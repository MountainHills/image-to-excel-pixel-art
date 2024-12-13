package com.antonbondoc;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main {

    private static final int BLOCK_SIZE = 20;   // Adjusting the pixel size is going to make it more blurry
    private static final int COLUMN_SIZE = 3;
    private static final int CHARACTER_WIDTH = 256;

    public static void main(String[] args) throws IOException {
        // Opening the image
        File imageFile = new File("images/lebron-james.jpg");
        BufferedImage image = ImageIO.read(imageFile);

        // Transform the image to pixel art
        BufferedImage pixelatedImage = pixelateImage(image, BLOCK_SIZE);

        // Generate Excel pixel art
        generateExcelPixelArt(pixelatedImage);

        // Scale pixelated image to original size
        BufferedImage outputImage = scaleUpImage(image.getWidth(), image.getHeight(), pixelatedImage);

        // Store the output image to a separate directory
        File outputFile = new File("output/pixel/image-output.jpg");
        ImageIO.write(outputImage, "jpg", outputFile);
    }

    public static BufferedImage pixelateImage(BufferedImage originalImage, int blockSize) {
        int scaledWidth = Math.max(1, originalImage.getWidth() / blockSize);
        int scaledHeight = Math.max(1, originalImage.getWidth() / blockSize);

        // Scale down original image
        Image tempImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_AREA_AVERAGING);

        // Convert image to buffered image to access RGB values of each pixel
        BufferedImage pixelatedImage = new BufferedImage(scaledWidth, scaledHeight, originalImage.getType());
        Graphics2D g2d = pixelatedImage.createGraphics();
        g2d.drawImage(tempImage, 0, 0, null);
        g2d.dispose();

        return pixelatedImage;
    }

    private static BufferedImage scaleUpImage(int width, int height, BufferedImage image) {
        BufferedImage scaledImage = new BufferedImage(width, height, image.getType());

        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();

        return scaledImage;
    }

    private static void generateExcelPixelArt(BufferedImage pixelatedImage) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Pixel Art");

            for (int i = 0; i < pixelatedImage.getHeight(); i++) {
                Row row = sheet.createRow(i);
                for (int j = 0; j < pixelatedImage.getWidth(); j++) {
                    Cell cell = row.createCell(j);

                    Color pixelColor = new Color(pixelatedImage.getRGB(j, i));
                    byte[] rgb = new byte[]{
                            (byte) pixelColor.getRed(),
                            (byte) pixelColor.getGreen(),
                            (byte) pixelColor.getBlue()
                    };

                    CellStyle cellStyle = workbook.createCellStyle();
                    cellStyle.setFillForegroundColor(new XSSFColor(rgb, null));
                    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                    cell.setCellStyle(cellStyle);
                }
            }

            // Set the sizes for the cells
            for (int i = 0; i < pixelatedImage.getWidth(); i++) {
                sheet.setColumnWidth(i, COLUMN_SIZE * CHARACTER_WIDTH);
            }

            // Write the Excel file
            OutputStream output = new FileOutputStream("output/excel-pixel-art.xlsx");
            workbook.write(output);
            output.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}