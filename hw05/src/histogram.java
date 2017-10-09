/*
 * CSCI 392
 * HW05 - Histogram Class
 * Peter Nguyen
 * Due: Sept. 27, 2017
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class histogram {
    private ArrayList<Integer> integers;

    public histogram(int maxDataPoints) {
        this.integers = new ArrayList<>(maxDataPoints);
    }

    public int grouping = 10;

    public void add_value(int value) {
        integers.add(value);
    }

    public void write() {
        // bin integers
        HashMap<Integer, Integer> binCounts = new HashMap<>();
        int lastBin = -1;
        for (int num : integers) {
            int bin = num / grouping;

            if (bin > lastBin)
                lastBin = bin;

            // Increment bin count or add bin count
            binCounts.merge(bin, 1, (a, b) -> a + b);
        }

        // output
        for (int bin = 0; bin <= lastBin; ++bin) {
            // Bin label
            System.out.print(String.format(
                    "%-8s",
                    Integer.toString(bin * grouping) + "-" + Integer.toString(((bin + 1) * grouping) - 1))
            );

            // Bar
            Integer count = binCounts.get(bin);
            if (count != null) // Add bar only if bin is not empty
                System.out.print(String.join("", Collections.nCopies(count, "*")));

            System.out.println();
        }
    }
}
