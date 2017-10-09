/*
 * CSCI 392
 * HW02 - Hello World 2.0
 * Peter Nguyen
 * Due: Sept. 6, 2017
 */

public class Hello {

    public static void main(String[] args) {
        switch (args.length) {
            case 0:
                noArgs();
                break;
            case 1:
                oneArg(args[0]);
                break;
            case 2:
                twoArgs(args);
                break;
            default:
                System.out.println("Error: too many args");
                usage();
                break;
        }
    }

    static private void usage() {
        System.out.println("Usage:");
        System.out.println("    java Hello [name] [count]");
        System.out.println("    java Hello [count] [name]");
    }

    static private void noArgs() {
        System.out.println("Hello World");
    }

    static private void oneArg(String arg) {
        // Arg may be name or count
        try { // If arg is count
            int count = Integer.parseInt(arg);

            // Count must be positive
            if (count < 0) {
                System.out.println("Error: count must be positive");
                usage();
                return;
            }

            // Print "Hello World" `count` times
            for (; count > 0; count--)
                System.out.println("Hello World");
        } catch (Exception e) { // If arg is name or > than max int
            System.out.println(arg);
        }
    }

    static private void twoArgs(String[] args) {
        int count;
        String name;

        // First arg may be name or count
        try { // If first arg is count
            count = Integer.parseInt(args[0]);
            name = args[1];
        } catch (Exception e) { // If second arg is count
            try {
                count = Integer.parseInt(args[1]);
                name = args[0];
            } catch (Exception ex) {
                System.out.println("Error: one arg must be count");
                usage();
                return;
            }
        }

        // Count must be positive
        if (count < 0) {
            System.out.println("Error: count must be positive");
            usage();
            return;
        }

        for (; count > 0; count--)
            System.out.println(name);
    }
}
