## Program 3
#### Due Wednesday, Sept. 27

------------------------------

Purpose
------------------------------
The purpose of this assignment is to convert your histogram code into a histogram class.

Problem Statement
------------------------------
Take your existing code and divide it into two files. One file will contain the histogram class and the other will
contain your `main()` that tests the new class.

The histogram class should contain at least three methods:
- ***constructor*** histogram(int)
- public method add_value(int) 
- public method write() 

Details
------------------------------
The constructor is passed the max number of values that are to be graphed. You need to know that value to create an
appropriate sized array. Do not allow a default for this attribute.

`add_value` is passed an integer data value that is to be graphed later. So, if someone wants to plot 50 data values,
their code will call add_value 50 times.

The `write` method just prints the graph.

Add a new feature to your new class. Allow the user to change the grouping size of the bars. In other words, instead of
always having bars represent the values 0-9, 10-19, etc., allow the user of your class to change the grouping to 0-19,
20-39, etc. To do this, add a public integer field named `grouping`. The default grouping size should be 10.
(Note: implement this feature last.)

The `main()` will do the same things as the previous assignment: ask for a filename, read the filename, open the file,
read the values, etc. The difference in the `main()` for this assignment is that it will declare a variable of type
`histogram`. Instead of adding the values from the file into an array, add them into the histogram. 

What to Turn In
------------------------------
Email your three source code files to dannellys@winthrop.edu as atttachments:
- histogram.java
- hw05_test.java

Again, please use the subject line, "CSCI 392 - HW05". 
