## Homework 2
#### Due Wednesday, Sept. 6

------------------------------

Purpose
------------------------------
Usage of command line arguments.

Problem Statement
------------------------------
Allow the user to enter a name and/or the number of times to repeat the output message on the command line.

Details
------------------------------
The user might enter nothing, just a name, or a name and a count. If the user provides no extra command line arguments,
then just print "Hello World". If the user enters a name then output "Hello" followed by that name. If the user enters
a number, then output the appropriate message that number of times.

Here are some sample runs:
```
$ java lab02
Hello World
$ java lab02 2
Hello World
Hello World
$ java lab02 Bob
Hello Bob
$ java lab02 Bob 3
Hello Bob
Hello Bob
Hello Bob
$ java lab02 3 Bob
Hello Bob
Hello Bob
Hello Bob
```

For a challenge, give an error when the user enters bad input such as:
```
$ java lab02 Bob Joe
Usage Error: enter a name and/or integer
$ java lab02 3 3 3 3
Usage Error: too many arguments
```

What to Turn In
------------------------------
Email your program's source code to dannellys@winthrop.edu by the beginning of class on Wednesday Sept 6. Again, please
use the subject line, "CSCI 392 - HW02". 
