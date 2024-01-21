package org.example;

public class Main {
    public static void main(final String[] args) {
        final long startTime = System.currentTimeMillis();
//        new Multi().start1();
//        new Multi().start2();
//        new Multi().start3();
        new Multi().start4();
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
    }
}