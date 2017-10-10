/*
 * CSCI 392
 * HW07 - Histogram Class
 * Peter Nguyen
 * Due: Oct. 10, 2017
 */


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InvertImage {
    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }

        // Check if file extension is ".jpg"
        if (!args[0].endsWith(".jpg")) {
            System.out.println("Error: File must be a JPEG");
        }

        Path filePath = Paths.get(args[0]);
        File input = new File(filePath.toString());
        BufferedImage image;

        // Try to read image
        try {
            image = ImageIO.read(input);
        } catch (Exception e) {
            System.out.println("Error: could not read file " + args[0]);
            return;
        }

        // For each pixel in image
        for (int i = 0, width = image.getWidth(); i < width; ++i) {
            for (int j = 0, height = image.getHeight(); j < height; ++j) {
                Color inverted = new Color(image.getRGB(i, j)); // Get color of pixel

                // Invert color
                inverted = new Color(
                        255 - inverted.getRed(),
                        255 - inverted.getGreen(),
                        255 - inverted.getBlue()
                );

                image.setRGB(i, j, inverted.getRGB()); // Set pixel to inverted color
            }
        }

        // Prefix "inv_" to filename
        String outputPath = filePath.getParent() + File.separator + "inv_" + filePath.getFileName();

        // Try to save file
        try {
            System.out.println(outputPath);
            File output = new File(outputPath);
            ImageIO.write(image, "jpg", output);
        } catch (Exception e) {
            System.out.println("Error: could not write file " + outputPath);
        }
    }

    private static void printUsage() {
        System.out.println("Usage: java InvertImage pathto.jpg");
    }
}
