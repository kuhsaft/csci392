/*
 * CSCI 392
 * HW09 - Web Crawler 1.0
 * Peter Nguyen
 * Due: Nov. 1, 2017
 *
 * User specifies a path on the command line to retrieve
 * from faculty.winthrop.edu
 */

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class hw09 {
    // Matches <a> elements
    private final static Pattern aElementRegex = Pattern.compile(
            "<a(?<attr>[^>]+)>(?<content>.+?)<\\/a>",
            Pattern.CASE_INSENSITIVE | Pattern.DOTALL
    );

    // Matches href attribute
    private final static Pattern hrefAttrRegex = Pattern.compile(
            "\\s*href\\s*=\\s*((?<quote>\\\")(?<link>[^\"]*)\\k<quote>|(?<link2>[^'\">\\s]+))",
            Pattern.CASE_INSENSITIVE
    );

    public static void main(String[] args) {
        System.out.println("Starting Web Tester\n");

        String path = "/"; // Default root path
        if (args.length == 1) {
            path = args[0]; // Set path to user input

            if (!path.startsWith("/")) // Prefix with '/' if necessary
                path = "/" + path;
        }

        try {
            // Connect to the server
            URL url = new URL("http://faculty.winthrop.edu" + path);
            HttpURLConnection.setFollowRedirects(true);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            int status = connection.getResponseCode();

            // Check if HTTP OK
            if (status != HttpURLConnection.HTTP_OK)
                throw new Exception("HTTP Error: Status " + Integer.toString(status));

            // Read content to string
            Scanner sc = new Scanner(new InputStreamReader(connection.getInputStream())).useDelimiter("\\A");
            String content = sc.hasNext() ? sc.next() : "";

            // Close connection
            sc.close();
            connection.disconnect();

            printLinks(content);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nDone with Web Tester");
    }

    private static void printLinks(final String content) {
        Matcher tagMatcher = aElementRegex.matcher(content);
        while (tagMatcher.find()) {
            String attributes = tagMatcher.group("attr").replaceAll("[\\t\\n\\r]+", " ").trim();

            Matcher hrefMatcher = hrefAttrRegex.matcher(attributes);
            while (hrefMatcher.find()) {
                String link = hrefMatcher.group("link");

                if (link == null)
                    link = hrefMatcher.group("link2");

                if (link != null)
                    System.out.println(link);
            }
        }
    }
}
