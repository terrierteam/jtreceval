# jtreceval

A Java wrapper around the binaries for trec_eval on various platforms.

Currently, we have the binaries for the following operating systems:
 * Linux Intel, 32bit (and hence 64bit)
 * Mac Intel, OS X 64bit
 * Windows Intel, 32bit (and hence 64bit)


## Compiling
```
mvn package
```

Java 1.7 is the minimum requirement. The Maven pom file depends only on Apache Commons-IO, and Junit (for testing only).

## Usage from the command-line

```
java -jar target/jtreceval-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```


## Usage from Java

```
String qrels = "/path/to/qrels";
String res = "/path/to/run";
trec_eval te = new trec_eval();
String[][] output = te.runAndGetOutput(new String[]{"-q", qrels, res});
//1st dimension is line of output, 2nd dim is [measure, query, value]
``` 

## Known Issues/Possible Improvements

1. Currently the Cywgin trec_eval binary is incorrectly detected as a virus by Windows Defender.
2. A more reasonable Java API that is easy to use.