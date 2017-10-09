/*
 * CSCI 392
 * HW05 - Histogram Class
 * Peter Nguyen
 * Due: Sept. 27, 2017
 */

import java.io.*;

// sum all the integers in a file
public class hw05_test {
    public static void main(String[] args) throws IOException {
        // prompt and read the name of the file
        BufferedReader stdinReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the name of a file to process: ");
        String fname = stdinReader.readLine();
        System.out.println();

        // open the data file
        RandomAccessFile fileReader;
        try {
            fileReader = new RandomAccessFile(fname, "r");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found: " + fname);
            return;
        }

        // Count lines
        int lineCount = 0;
        while (fileReader.readLine() != null)
            ++lineCount;
        fileReader.seek(0);

        // create a graph instance
        histogram myHistogram;
        myHistogram = new histogram(lineCount);
        int numIgnored = 0;

        // read until eof and add integers to list
        for (String line; (line = fileReader.readLine()) != null; ) {
            try {
                int intRead = Integer.parseInt(line);
                myHistogram.add_value(intRead);
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
        System.out.println("Histogram One - grouping = 10");
        myHistogram.write();
        System.out.println();

        System.out.println("Histogram Two - grouping = 20");
        myHistogram.grouping = 20;
        myHistogram.write();
    }
}
