## Homework 9
#### Due Wednesday, Nov. 1

------------------------------

Purpose
------------------------------
The purpose of this assignment is to use some of Java's built-in networking functions.

Problem Statement
------------------------------
I would like a web crawler that can find bad links in a web site. Eventually, the program will wander around a web site
and report links that go to non-existant files. The first step in creating such a program is to create a program that
can find all the links in a given HTML file.

Modify the `web2.java` program that I described in class to build a program that will print all the files that are
linked to by a given html file. For now, just hardcode your program to connect to [faculty.winthrop.edu](), but allow
the user to specify the filename via the command line (`args[0]`). Retrieve the specified file from the web
server and print only the names of all the links in web page.

For example, if the web page contains the html code
```html
<a href="bob.htm"> link to bob </a>
```
then just print
```
bob.htm
```

For this first version, use the Java string manipulation functions to find all `<a>` tags.  You will discover that
finding all `<a>` tags is very difficult and that your code is not entirely effective. So, in the next version of
this program we will use a Java package, Jsoup, that does HTML parsing. The second version, will not only find all
the links inside a page, but also determine which links are broken. 

Details
------------------------------
This version of your web crawler is not expected to by perfect. The following HTML will probably confuse your program.

```html
<a href="homework1.htm">homework one</a> and <a href="homework2.htm">homework two</a>
<P>
<a 
href="link.html">
this is a link
</a>
```

But, your program should be able to handle all of these lines from a web page.

```html
blah blah <a href="homework1.htm">homework one</a> more blah blah
<P>
<a href = "link.html" > this link has lots of spaces </a>
```

**Do NOT use Jsoup for this program. Figure out the string matching on your own.**

What to Turn In
------------------------------
To make grading a bit easier, please name the class (and hence the file name for your code) `hw09`.
Please send the source code file as an attachment.

Email your program's source code to dannellys@winthrop.edu by the beginning of class on Wednesday Nov 1.
Again, please use the subject line, "CSCI 392 - HW09". 
