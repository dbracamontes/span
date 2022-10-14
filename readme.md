# Span Challenge
This program calculates the ranking.
In this league, a draw (tie) is worth 1 point and a win is worth 3 points. A loss is worth 0 points.
If two or more teams have the same number of points, they should have the same rank and be
printed in alphabetical order (as in the tie for 3rd place in the sample data).

## Prerequisites
Java 17<br />
Maven 3.8

## Installation

Use maven for the package generation. The output will be a jar in directory SpanChallenge\target\span-challenge-0.0.1-SNAPSHOT.jar

```bash
mvn package
```

## Running App
Installation steps is required before running the app. This is a spring boot application and the entry point is class src/main/java/SpanChallengeApplication.java.<br />
For running the program is required to pass the full path for the file /Users/daniel.bracamontes/Desktop/Case_1_input.txt.
```bash
 cd target
 java -jar span-challenge-0.0.1-SNAPSHOT.jar "/Users/daniel.bracamontes/Desktop/Case_1_input.txt"
```
## Running Test
The test cases are covered with cucumber. The definition of them can be checked in src/test/resources/cucumber/ranking_service.future. </br>
Implementation is located in src/test/java/cucumber/RankingServiceDef.java
```bash
mvn test
```