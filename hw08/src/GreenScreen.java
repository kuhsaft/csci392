/*
 * CSCI 392
 * HW08 - Green Screen
 * Peter Nguyen
 * Due: Oct. 25, 2017
 */


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GreenScreen {
    private static double A = 10.0;
    private static double B = 10.0;

    public static void main(String[] args) {
        if (args.length < 2 || args.length == 3 || args.length == 6 || args.length > 7) {
            printUsage();
            return;
        }

        // Check if file extension is ".jpg"
        if (!args[0].endsWith(".jpg") || !args[1].endsWith(".jpg")) {
            System.out.println("Error: File must be a JPEG");
            printUsage();
            return;
        }

        Color key = new Color(0, 255, 0);
        if (args.length >= 5) {
            try {
                int r = Integer.parseInt(args[2]);
                int g = Integer.parseInt(args[3]);
                int b = Integer.parseInt(args[4]);

                key = new Color(r, g, b);
            } catch (NumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
                return;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: RGB values must be in range (0-255)");
                return;
            }
        }

        if (args.length == 4 || args.length == 7) {
            try {
                if (args.length == 4) {
                    A = Double.parseDouble(args[2]);
                    B = Double.parseDouble(args[3]);
                } else {
                    A = Double.parseDouble(args[5]);
                    B = Double.parseDouble(args[6]);
                }

                if (A < 0 || B < 0) {
                    System.out.println("Error: weights must be between >= 0");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
                return;
            }
        }

        Path backgroundFilePath = Paths.get(args[0]);
        File backgroundInput = new File(backgroundFilePath.toString());
        BufferedImage backgroundImage;

        // Try to read image
        try {
            backgroundImage = ImageIO.read(backgroundInput);
        } catch (Exception e) {
            System.out.println("Error: could not read file " + args[0]);
            return;
        }

        Path foregroundFilePath = Paths.get(args[1]);
        File foregroundInput = new File(foregroundFilePath.toString());
        BufferedImage foregroundImage;

        // Try to read image
        try {
            foregroundImage = ImageIO.read(foregroundInput);
        } catch (Exception e) {
            System.out.println("Error: could not read file " + args[0]);
            return;
        }

        // Pick the smallest dimensions
        int maxWidth = (backgroundImage.getWidth() < foregroundImage.getWidth())
                ? backgroundImage.getWidth() : foregroundImage.getWidth();
        int maxHeight = (backgroundImage.getHeight() < foregroundImage.getHeight())
                ? backgroundImage.getHeight() : foregroundImage.getHeight();

        BufferedImage outputImage = new BufferedImage(maxWidth, maxHeight, foregroundImage.getType());

        // Chroma key Cb and Cr values
        int CbKey = RGBToCb(key);
        int CrKey = RGBToCr(key);
        int CyKey = RGBToY(key);

        // For each pixel in image
        for (int i = 0, width = outputImage.getWidth(); i < width; ++i) {
            for (int j = 0, height = outputImage.getHeight(); j < height; ++j) {
                Color fgColor = new Color(foregroundImage.getRGB(i, j)); // Get color of foreground pixel
                Color bgColor = new Color(backgroundImage.getRGB(i, j)); // Get color of background pixel

                double alpha = getAlpha(RGBToCb(fgColor), RGBToCr(fgColor), RGBToY(fgColor), CbKey, CrKey, CyKey);

                // Composite foreground on background with alpha
                Color composited = new Color(
                        (int) ((alpha * fgColor.getRed()) + (bgColor.getRed() * (1 - alpha))),
                        (int) ((alpha * fgColor.getGreen()) + (bgColor.getGreen() * (1 - alpha))),
                        (int) ((alpha * fgColor.getBlue()) + (bgColor.getBlue() * (1 - alpha)))
                );

                outputImage.setRGB(i, j, composited.getRGB());
            }
        }

        String outputPath = foregroundFilePath.getParent()
                + File.separator
                + "green_"
                + foregroundFilePath.getFileName();

        // Try to save file
        try {
            System.out.println(outputPath);
            File output = new File(outputPath);
            ImageIO.write(outputImage, "jpg", output);
            System.out.println("File written to: " + outputPath);
        } catch (Exception e) {
            System.out.println("Error: could not write file " + outputPath);
        }
    }

    // Converts colors in RGB into YCbCr (Y value)
    private static int RGBToY(Color color) {
        return (int) Math.round(0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue());
    }

    // Converts colors in RGB into YCbCr (Cb value)
    private static int RGBToCb(Color color) {
        return (int) Math.round(128 + -0.168736 * color.getRed() - 0.331264 * color.getGreen() + 0.5 * color.getBlue());
    }

    // Convert colors in RGB into YCbCr (Cr value)
    private static int RGBToCr(Color color) {
        return (int) Math.round(128 + 0.5 * color.getRed() - 0.418688 * color.getGreen() - 0.081312 * color.getBlue());
    }

    private static double getAlpha(int Cb, int Cr, int Cy, int CbKey, int CrKey, int CyKey) {
        double temp = Math.sqrt((CbKey - Cb) * (CbKey - Cb) + (CrKey - Cr) * (CrKey - Cr) + (CyKey - Cy) * (CyKey - Cy));

        if (temp < A) return 0.0;
        else if (temp < B) return (temp - A) / (B - A);
        else return 1.0;
    }

    private static void printUsage() {
        System.out.println("Usage: java GreenScreen background.jpg foreground.jpg [R G B] [[tol. A] [tol. B]]");
        System.out.println("\t[R G B] the chroma key RGB values (0-255) (default: 0 255 0)");
        System.out.println("\t[[tolerance] [intensity]] (minimum 0.0) (default: 10.0 10.0)");
    }
}
