package com.multithread.robotmovement;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Leg implements Runnable {
//    1 implementation: use shared object (lock) and string (currentLeg).
//    if currentLeg = leg.name => print leg name, otherwise current leg releases lock and allowing the other leg enter

//    private static final Object lock = new Object();
//    private static volatile String currentLeg = "left";
//
//    private final String name;
//
//    public Leg(final String name) {
//        this.name = name;
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            synchronized (lock) {
//                if (name.equals(currentLeg)) {
//                    System.out.println(name);
//                    if (name.equals("left")) {
//                        currentLeg = "right";
//                    }
//                    else {
//                        currentLeg = "left";
//                    }
//                }
//            }
//        }
//    }
//
//    public static void main(final String[] args) {
//        CompletableFuture.allOf(
//                CompletableFuture.runAsync(new Leg("right")),
//                CompletableFuture.runAsync(new Leg("left"))
//        ).join();
//    }

//  2 implementation: use ScheduledExecutorService to execute each operation periodically.
//  At the beginning, leftLeg prints their name, then righLeg prints name with delay SWITCH_DELAY / 2
//  then leftLeg prints name and so on
    private final String name;

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    private static final int SWITCH_DELAY = 100;

    private static final int TIME_SLEEP = 3000;

    public Leg(final String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name);
    }

    public static void main(final String[] args) {
        final Leg leftLeg = new Leg("left");
        final Leg rightLeg = new Leg("right");

        scheduler.scheduleAtFixedRate(leftLeg, 0, SWITCH_DELAY, TimeUnit.MILLISECONDS);
        scheduler.scheduleAtFixedRate(rightLeg, SWITCH_DELAY / 2, SWITCH_DELAY, TimeUnit.MILLISECONDS);

        try {
            Thread.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduler.shutdown();
    }
}



