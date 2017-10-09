/*
 * CSCI 392
 * HW04 - Histogram 1.0
 * Peter Nguyen
 * Due: Sept. 20, 2017
 */

import java.io.*;
import java.util.*;

// sum all the integers in a file
public class HW04 {
    private static final int BIN_SIZE = 10;
    private static final int NUM_BINS = 10;

    public static void main(String[] args) throws IOException {
        // prompt and read the name of the file
        BufferedReader stdinReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the name of a file to process: ");
        String fname = stdinReader.readLine();
        System.out.println();

        // open the data file
        BufferedReader inputFile;
        try {
            inputFile = new BufferedReader(new FileReader(fname));
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found: " + fname);
            return;
        }

        ArrayList<Integer> integers = new ArrayList<>();
        int numIgnored = 0;

        // read until eof and add integers to list
        for (String line; (line = inputFile.readLine()) != null; ) {
            try {
                int intRead = Integer.parseInt(line);

                if (intRead < 0 || intRead > ((BIN_SIZE * NUM_BINS) - 1))
                    ++numIgnored; // Ignore numbers out of range
                else
                    integers.add(intRead);
            } catch (NumberFormatException e) {
                // Ignore non-integers
                ++numIgnored;
            }
        }
        inputFile.close();

        // bin integers
        int[] binCount = new int[NUM_BINS];
        int lastBin = -1;
        Arrays.fill(binCount, 0);
        for (int num : integers) {
            int bin = num / BIN_SIZE;

            if (bin > lastBin)
                lastBin = bin;

            ++binCount[bin];
        }

        // output
        if (numIgnored > 0)
            System.out.println(String.format(
                    "WARNING: %d lines were skipped because they were out of range or not an integer\n",
                    numIgnored
            ));

        for (int bin = 0; bin <= lastBin; ++bin) {
            System.out.print(String.format( // Bin label
                    "%-8s",
                    Integer.toString(bin * BIN_SIZE) + "-" + Integer.toString(((bin + 1) * BIN_SIZE) - 1))
            );

            // Bar
            System.out.print(String.join("", Collections.nCopies(binCount[bin], "*")));

            System.out.println();
        }
    }
}
