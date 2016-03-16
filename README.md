# jtreceval

A wrapper around binaries for trec_eval on various platforms.

Currently, we have the binaries for the following operating systems:
 * Linux Intel, 32bit (and hence 64bit)
 * Mac Intel, OS X 64bit
 * Windows Intel, 32bit (and hence 64bit)


## Compiling
```
mvn package
```

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

