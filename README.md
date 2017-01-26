# jtreceval

A Java wrapper around the binaries for NIST's trec_eval (https://github.com/usnistgov/trec_eval) on various platforms. The trec_eval binaries are included in the jar file, so the jar is easy to use on a number of platforms without compiling.

Currently, we have the binaries for the following operating systems:
 * Linux, Intel 32bit, 64bit
 * Mac OS X, Intel 64bit
 * Windows, Intel 32bit (and hence 64bit)


## Compiling
```
mvn package
```

Java 1.7 is the minimum requirement. The Maven pom file depends only on Apache Commons-IO, and Junit (for testing only).

## Usage from the command-line

Essentially, executing the jar file using `java -jar` should have the same effect as executing `trec_eval` directly on your platform:
```
java -jar target/jtreceval-0.0.2-SNAPSHOT-jar-with-dependencies.jar
```

Example:
```
java -jar target/jtreceval-0.0.2-SNAPSHOT-jar-with-dependencies.jar qrels myrun.res
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

## Credits

Developed by Craig Macdonald, University of Glasgow. 

The repository contains compilations of NIST's trec_eval for various platforms.
