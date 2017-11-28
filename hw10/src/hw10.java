/*
 * CSCI 392
 * HW10 - Web Crawler 2.0
 * Peter Nguyen
 * Due: Nov. 29, 2017
 *
 * User specifies a path on the command line to retrieve
 * from faculty.winthrop.edu
 */

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Iterator;

public class hw10 {
    private static final String HOST = "https://faculty.winthrop.edu/";
    private static final int TIMEOUT = 5 * 1000; // 5 seconds
    private static final int MAX_DEPTH = 2;

    public static void main(String[] args) {
        if (args.length == 1) {
            crawl(HOST, args[0], MAX_DEPTH);
        } else {
            crawl(HOST, "/", MAX_DEPTH);
        }
    }

    private static void crawl(final String host, String path, final int maxDepth) {
        // Create URL
        URL url;
        try {
            url = new URL(host);
            url = new URL(url, path);
        } catch (MalformedURLException e) {
            printStatus(0, Status.BROKEN, host + path, false, true);
            return;
        }

        String location = url.toString();

        try {
            Response response = Jsoup.connect(location)
                    .timeout(TIMEOUT) // 5 second timeout
                    .followRedirects(true)
                    .ignoreContentType(true)
                    .execute();

            printStatus(0, Status.OKAY, path, true, true);

            // Only check for a elements if the content type is HTML
            if (response.contentType().equals("text/html")) {
                Document document = response.parse();

                Elements elements = document.select("a[href]");
                Iterator<Element> elementIterator = elements.iterator();

                while (elementIterator.hasNext()) {
                    Element element = elementIterator.next();

                    if (elementIterator.hasNext())
                        crawl(element, maxDepth, 1, false);
                    else
                        crawl(element, maxDepth, 1, true);
                }
            }
        } catch (IOException e) {
            // Bad response
            printStatus(0, Status.BROKEN, path, false, true);
        }
    }

    private static void crawl(
            Element element,
            final int maxDepth,
            final int depth,
            final boolean isLastChild
    ) {
        if (depth > maxDepth)
            return;

        String href = element.attr("href");
        String absHref = element.attr("abs:href");

        // Return if external link
        if (href.startsWith("http://") || href.startsWith("https://")) {
            printStatus(depth, Status.EXTERNAL, href, false, isLastChild);
            return;
        }

        try {
            Response response = Jsoup.connect(absHref)
                    .timeout(TIMEOUT) // 5 second timeout
                    .followRedirects(true)
                    .ignoreContentType(true)
                    .execute();

            // Only check for a elements if the content type is HTML
            if (response.contentType().equals("text/html")) {
                Document document = response.parse();
                Elements elements = document.select("a[href]");

                Iterator<Element> elementIterator = elements.iterator();
                printStatus(depth, Status.OKAY, href, elementIterator.hasNext(), isLastChild);

                // Recurse for all a elements with href attr
                while (elementIterator.hasNext()) {
                    Element elem = elementIterator.next();

                    // If there is a next element the element is not the last child
                    if (elementIterator.hasNext())
                        crawl(elem, maxDepth, depth + 1, false);
                    else
                        crawl(elem, maxDepth, depth + 1, true);
                }
            } else {
                // Successful response but not HTML
                printStatus(depth, Status.OKAY, href, false, isLastChild);
            }
        } catch (IOException e) {
            // Bad response
            printStatus(depth, Status.BROKEN, href, false, isLastChild);
        }
    }

    enum Status {
        OKAY,
        BROKEN,
        EXTERNAL
    }

    // Prints status in a tree
    private static void printStatus(
            final int depth,
            final Status status,
            final String path,
            final boolean hasChildren,
            final boolean isLastChild
    ) {
        // Left padding
        if (depth > 0) {
            for (int i = 1; i < depth; i++) {
                System.out.print("│");
                System.out.format("%7s", "");
            }

            if (isLastChild)
                System.out.print("└");
            else
                System.out.print("├");
            System.out.print(String.join("", Collections.nCopies(7, "─")));

            if (hasChildren)
                System.out.print("┬ ");
            else
                System.out.print("─ ");
        }

        // Status
        switch (status) {
            case OKAY:
                System.out.print("OKAY     ");
                break;
            case BROKEN:
                System.out.print("BROKEN   ");
                break;
            case EXTERNAL:
                System.out.print("EXTERNAL ");
                break;
        }

        // Path
        System.out.format("\"%s\"", path);
        System.out.println();
    }
}
