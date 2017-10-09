/*
 * CSCI 392
 * HW03 - Arrays and Files
 * Peter Nguyen
 * Due: Sept. 13, 2017
 */

import java.io.*;
import java.util.*;

// sum all the integers in a file
public class HW03 {
    public static void main(String[] args) throws IOException {
        // prompt and read the name of the file
        BufferedReader stdinReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the name of a file to process: ");
        String fname = stdinReader.readLine();

        // open the data file
        BufferedReader inputFile;
        try {
            inputFile = new BufferedReader(new FileReader(fname));
        } catch (IOException e) {
            System.out.println("Error: Unable to open file: " + fname);
            System.out.println("Can not recover from error.  Exiting.");
            return;
        }

        ArrayList<Integer> integers = new ArrayList<>();

        // read until eof and add integers to list
        for (String line; (line = inputFile.readLine()) != null; ) {
            try {
                integers.add(Integer.parseInt(line));
            } catch (Exception e) {
                // Ignore non-integers
            }
        }
        inputFile.close();

        // sort integers
        Collections.sort(integers);

        // output integers and calculate sum
        int sum = 0;
        System.out.println("\nThe sum of the integers");
        for (Integer integer : integers) {
            sum += integer;
            System.out.println(Integer.toString(integer));
        }

        // output sum
        System.out.println("is " + Integer.toString(sum));
    }
}
