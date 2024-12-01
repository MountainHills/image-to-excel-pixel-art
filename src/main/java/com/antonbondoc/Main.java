package com.antonbondoc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. Opening the image
        File imageFile = new File("images/lebron-james.jpg");
        BufferedImage image = ImageIO.read(imageFile);

        // 2. Transform the image to pixel art
        int blockSize = 20; // Adjusting the pixel size is going to make it more blurry
        BufferedImage pixelatedImage = pixelateUsingScaling(image, blockSize);

        // 3. Store he output to a separate directory

        File outputFileScaling = new File("output/image-output-scaling.jpg");
        ImageIO.write(pixelatedImage, "jpg", outputFileScaling);
    }

    public static BufferedImage pixelateUsingScaling(BufferedImage inputImage, int blockSize) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        // Calculate scaled dimensions
        int scaledWidth = Math.max(1, width / blockSize);
        int scaledHeight = Math.max(1, height / blockSize);

        // Create a scaled-down version
        Image tempImage = inputImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_AREA_AVERAGING);

        // Create a BufferedImage to scale it back up
        BufferedImage pixelatedImage = new BufferedImage(width, height, inputImage.getType());
        Graphics2D g2d = pixelatedImage.createGraphics();

        // Scale back up
        g2d.drawImage(tempImage, 0, 0, width, height, null);
        g2d.dispose();

        return pixelatedImage;
    }
}