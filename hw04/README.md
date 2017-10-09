## Program 3
#### Due Wednesday, Sept. 20

------------------------------

Purpose
------------------------------
The purpose of this assignment is for you to learn to work with files and exceptions.

Problem Statement
------------------------------
Create a Java program that will read in a list of integers from a file and output a histogram.
- The name of the file should come from the user.
- The distribution size of each bar in the graph should be 10.
- Start the graph at value 0 and end at no more than 99. 

Details
------------------------------
Here is a sample run:
```
$ java lab04
Name of file to process: integers.txt
0-9    ***
10-19
20-29  **
30-39  ****
40-49  *
```

The above output would be the result of processing this file, named `integers.txt`:
```
3
5
8
28
29
30
31
Hi
Mom
45
32
33
```

If the user inputs the name of a file that does not exist or can't be opened for reading, report the error and exit.

If the file contains text, just ignore the text. Be careful that you don't count the text as a zero and that text does
not cause the previous value to appear in the graph twice. 

What to Turn In
------------------------------
Email your program's source code to dannellys@winthrop.edu by the beginning of class on Wednesday Sept 20. Again, please
use the subject line, "CSCI 392 - HW04". 
