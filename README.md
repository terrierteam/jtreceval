# jtreceval

A wrapper around binaries for trec_eval on various platforms.

Currently, we have the binaries for the following operating systems:
 * Linux 32bit and 64bit
 * Mac OS X 64bit


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
te.runAndGetOutput(new String[]{"-q", qrels, res);
``` 

