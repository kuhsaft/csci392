/*
 * CSCI 392
 * HW05 - Histogram Class
 * Peter Nguyen
 * Due: Sept. 27, 2017
 */

import java.io.*;

// sum all the integers in a file
public class hw06_test {
    public static void main(String[] args) throws IOException {
        // prompt and read the name of the file
        BufferedReader stdinReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the name of a file to process: ");
        String fname = stdinReader.readLine();

        // open the data file
        RandomAccessFile fileReader;
        try {
            fileReader = new RandomAccessFile(fname, "r");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found: " + fname);
            return;
        }

        int barSize;
        System.out.print("Bar range size: ");
        try {
            barSize = Integer.parseInt(stdinReader.readLine());
            if (barSize < 1)
                throw new NumberFormatException("Bar size cannot be less than 1");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid bar size");
            return;
        }
        System.out.println();

        // Count lines
        int lineCount = 0;
        while (fileReader.readLine() != null)
            ++lineCount;
        fileReader.seek(0);

        // create a graph instance
        histogram regularHistogram = new histogram(lineCount);
        regularHistogram.grouping = barSize;

        vertical_histogram verticalHistogram = new vertical_histogram(lineCount);
        verticalHistogram.grouping = barSize;

        int numIgnored = 0;
        // read until eof and add integers to list
        for (String line; (line = fileReader.readLine()) != null; ) {
            try {
                int intRead = Integer.parseInt(line);
                regularHistogram.add_value(intRead);
                verticalHistogram.add_value(intRead);
            } catch (NumberFormatException e) {
                // Ignore non-integers
                ++numIgnored;
            }
        }
        fileReader.close();

        if (numIgnored > 0)
            System.out.println(String.format(
                    "WARNING: %d lines were skipped because they were not an integer\n",
                    numIgnored
            ));

        // Output
        System.out.println("Regular Histogram:");
        regularHistogram.write();
        System.out.println();

        System.out.println("Vertical Histogram:");
        verticalHistogram.write();
    }
}
