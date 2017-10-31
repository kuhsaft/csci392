## Homework 6
#### Due Wednesday, Oct. 4

------------------------------

Purpose
------------------------------
The purpose of this assignment is to extend your histogram class and create a new class named vertical_histogram.

Problem Statement
------------------------------
Create a new `.java` file containing a class named `vertical_histogram`. Write just the methods that need to be
overridden or overloaded.

Change your `main()` from the previous assignment so that it now declares a vertical histogram and a histogram.
So, you will populate both instances with values from the same data file. Also, allow the user to enter the size of
the range for the bars. 

Details
------------------------------
Sample input and output:
```
$ java lab06
Name of file to process: integers.txt
Bar range size : 10

Regular Histogram:
0-9    ***
10-19
20-29  **
30-39  ****
40-49  *

Vertical Histogram:
                         *
  *                      *
  *              *       *
  *              *       *       *
 0-9   10-19   20-29   30-39   40-49
```

What to Turn In
------------------------------
To make grading a bit easier, please name the class (and hence the file name) that contains `main()` `hw06_test`.

Email your three source code files to dannellys@winthrop.edu as atttachments:
- histogram.java
- vertical_histogram.java
- hw06_test.java

Again, please use the subject line, "CSCI 392 - HW06". 
