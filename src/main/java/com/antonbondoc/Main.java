package com.antonbondoc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Opening the image
        File imageFile = new File("images/lebron-james.jpg");
        BufferedImage image = ImageIO.read(imageFile);

        // Transform the image to pixel art
        int blockSize = 20; // Adjusting the pixel size is going to make it more blurry
        BufferedImage pixelatedImage = pixelateImage(image, blockSize);

        // Scale pixelated image to original size
        BufferedImage outputImage = scaleUpImage(image.getWidth(), image.getHeight(), pixelatedImage);

        // Generate Excel pixel art
        generateExcelPixelArt(pixelatedImage);

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
        // No-op
    }
}