ANT, Java and JUnit




ANT 1.7+:

 $ant -version

 Apache Ant version 1.7.1 compiled on November 24 2010



Java 1.7+ JDK and JRE:

  $javac -version

  javac 1.7.0_67
  
  $java -version

  java version "1.7.0_67"

  Java(TM) SE Runtime Environment (build 1.7.0_67-b01)
  
  Java HotSpot(TM) 64-Bit Server VM (build 24.65-b04, mixed mode)



JUnit4.10+:

  File:lib/junit-4.10.jar

  Used by:test code in test/




  
Usage



Compile and JAR:

 $ant jar


Run:

You have two options when running the code. If you use this first method you will be led through a series of questions to set up the initial conditions.

 $ant run

 or 

If you use this second method you can run the code by giving it the map dat file and an input file with initial conditions for the simulation.
$ ant compile
$ java -classpath lib/groupwork.jar groupproject.TestDriver small.dat SampleInput.txt


Compile and JAR tests:

 $ant jarTests


Run tests:

 $ant runTests


View results as XML:

 $cat build/test-xml/*

View results as HTML:

 $cat build/test-html/index.html
