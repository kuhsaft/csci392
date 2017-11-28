## Homework 10
#### Due Wednesday, Nov. 29

------------------------------

Purpose
------------------------------
The purpose of this assignment is to create a fairly complex application. You will need to use networking classes,
Jsoup, string manipulation, exception handling, probably need ArrayLists, etc.

Requirements
------------------------------
I would like a web crawler that can find bad links in a web site. The application has three main functions.

1. I would like to specify an HTML file via the command line (arg[0]) and have the application list all links
within that file (that was version 1.0).
2. For each of the links that are local, the application should print a message indicating if the link is okay or broken.
3. For each of the links that are links to local HTML files, the application should list all links inside those
sub-files and determine if those links are valid.

**DO NOT** check external links to determine if they are okay or broken.  Just list external links as external.
We will assume that any href beginning with `http://` or `https://` is an external link.

Assume the web server is `faculty.winthrop.edu`. For example, if the user specified `dannellys`, your program would
search `http://faculty.winthrop.edu/dannellys/`.

Make your output clear and easily understood. It should be clear which links belong to the original URL and which links
belong to different sub-URLs. Just of long list of links and whether they are good or bad is not very helpful to
someone maintaining a web site.

Spaces inside an href will probably confuse your program, but that's okay. For example, <a href="file one.ppt"> will
probably confuse your program. 

Depth of the Search
------------------------------
Assume the file `AAA.htm` contains links to the files `BBB.htm` and `CCC.htm`. The file `BBB.htm` contains no links.
The file `CCC.htm` links to `DDD.htm` and `EEE.htm`. In other words:

```
AAA:
  BBB:
    no links
  CCC:
    DDD:
        links exist, but contents don't matter
    EEE:
        links exist, but contents don't matter
```

If told to search the file `AAA.htm`, the application will print that `AAA` contains valid links to `BBB` and `CCC`.
Additionally, the application will print that `BBB` contains no links, and that `CCC` contains valid
links to `DDD` and `EEE`.

**Important Note**: stop there! We don't want this application to become a big beast eating network bandwidth.
So, we will only go down one level from `AAA` to `BBB` and `CCC`, but will not also search for links in the files
`DDD` and `EEE`, and files that they link to, and files that those files link to, and so on. 

Example Run
------------------------------
Here is an example run of one of my versions of this program. This output came from a solution where I used recursion. The output from my non-recursive version looks a lot different. 

```
> java -cp .:jsoup-1.9.2.jar links4 dannellys/csci392
There are 29 links:
okay     --> "Java_Syllabus_Fall_2017.pdf"
okay     --> "lecture/Day01_Overview.ppt"
okay     --> "homework/hw01.htm"
         There are 0 links:
okay     --> "lecture/Day02_Basics.ppt"
okay     --> "homework/hw02.htm"
         There are 0 links:
broken   --> "lecture/Day03 - Arrays and Strings.ppt"
okay     --> "lecture/Files.htm"
         There are 4 links:
         okay     --> "../examples/filetest1.java"
         okay     --> "../examples/filetest2.java"
         okay     --> "../examples/filetest3.java"
         okay     --> "../examples/filetest4.java"
okay     --> "homework/hw03.htm"
         There are 1 links:
         okay     --> "../examples/filetest4.java"
broken   --> "lecture/Day05 - Java Exceptions.ppt"
external --> "http://docs.oracle.com/javase/1.5.0/docs/api/java/lang/Exception.html"
okay     --> "homework/hw04.htm"
         There are 0 links:
okay     --> "lecture/Class_Basics.ppt"
okay     --> "homework/hw05.htm"
         There are 0 links:
okay     --> "lecture/Classes_Inherit.ppt"
okay     --> "homework/hw06.htm"
         There are 0 links:
okay     --> "lecture/images01.htm"
         There are 3 links:
         external --> "http://docs.oracle.com/javase/7/docs/api/java/awt/Image.html"
         external --> "http://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferedImage.html"
         okay     --> "../examples/img2_output.txt"
okay     --> "homework/images1.htm"
         There are 0 links:
okay     --> "lecture/images02.htm"
         There are 0 links:
okay     --> "homework/images2.htm"
         There are 0 links:
okay     --> "lecture/Basic_Client_Server.ppt"
okay     --> "examples/web1.java"
okay     --> "examples/web2.java"
okay     --> "homework/web1.htm"
         There are 0 links:
okay     --> "lecture/Jsoup.pptx"
external --> "http://jsoup.org"
external --> "http://www.javatpoint.com/jsoup-tutorial"
external --> "http://download.oracle.com/javase/1.5.0/docs/api/java/util/ArrayList.html"
broken   --> "badlink.htm"
broken   --> "badlink.htm"
```

Hints
------------------------------
Where do I get started?

The first thing to do is change version 1 to report which links are local or external.

Second, you will need a function that can determine if a link is good or bad.
While Jsoup does have a way to do this, I found it much easier to use Java's networking functions to connect to
`faculty.winthrop.edu`, GET the file, and read the first line of the reply. A code of `200` in the first line means
the link is good.

The tricky part is getting the URL correct. If you are processing the file `/dannellys/csci392/default.htm`
and you find a reference to `homework/hw01.htm` then the link you want to check is `/dannellys/csci392/homework/hw01.htm`
That link is part of the file name and part of the reference.

That problem can be solved with either careful string manipulation or with Jsoup methods.

Third, for all local links that are good and end with either `.htm` or `.html`, you will need to search them for
links and determine which links are okay, broken, or external. In other words, you need to figure out how to
go down one level.

To solve the third problem, you could build a list of HTML links that you need to process.
You should use an `ArrayList` for this. For example, while processing `/dannellys/csci392`, each good local link to an
HTML file could be placed into an `ArrayList`. After processing `/dannellys/csci392/`, process each link in the list.

Another approach to solving the third problem is to write a recursive function. Be careful to add a stopping
condition, otherwise you might end up accidentally creating a Denial of Service attack. 

General Advice
------------------------------
My `main()` was very short. It worked best to write several short functions.

Most importantly, **ADD LOTS OF COMMENTS AND PRINT STATEMENTS**. 

Submission Instructions
------------------------------
To make grading a bit easier, please name the class (and hence the file name for your code) `hw10`.
Please send the source code file as an attachment.

Email your program's source code to dannellys@winthrop.edu by the beginning of the final day of class.
Again, please use the subject line, "CSCI 392 - HW10". 
