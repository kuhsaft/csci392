/*
 * CSCI 392
 * HW05 - Histogram Class
 * Peter Nguyen
 * Due: Sept. 27, 2017
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class vertical_histogram extends histogram {
    public vertical_histogram(int maxDataPoints) {
        super(maxDataPoints);
    }

    @Override
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

        int maxBar = Collections.max(binCounts.values());

        // output
        for (int row = maxBar; row > 0; --row) {
            for (int bin = 0; bin <= lastBin; ++bin) {
                if (row <= binCounts.getOrDefault(bin, 0)) {
                    System.out.print(centerString("*", 9));
                } else {
                    System.out.print(centerString(" ", 9));
                }
            }

            System.out.println();
        }

        // Bin labels
        for (int bin = 0; bin <= lastBin; ++bin) {
            // Bin label
            System.out.print(centerString(
                    Integer.toString(bin * grouping) + "-" + Integer.toString(((bin + 1) * grouping) - 1),
                    9
            ));
        }
    }

    private static String centerString(String string, int maxPadding) {
        if (string == null)
            throw new NullPointerException("Can not pad null String!");

        int padding = (maxPadding - string.length()) / 2; // Calc left/right padding
        if (padding < 0) return string;

        // extra character in case of even length string
        int extra = (string.length() % 2 == 0) ? 1 : 0;

        String leftPadding = "%" + padding + "s";
        String rightPadding = "%" + (padding + extra) + "s";

        String strFormat = leftPadding + "%s" + rightPadding;
        return String.format(strFormat, "", string, "");
    }
}
