package com.multithread.hardprocessing;

public class Main {
    public static void main(final String[] args) {
        final long startTime = System.currentTimeMillis();
//        new HeavyProcesses().start1();
        new HeavyProcesses().start2();
//        new HeavyProcesses().start3();
//        new HeavyProcesses().start4();
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
    }
}