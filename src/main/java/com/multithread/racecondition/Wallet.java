package com.multithread.racecondition;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This is our service.
 * We think about our clients, so we cannot change contract of our public methods.
 * It must always return int even if we change type of money.
 */
public class Wallet {

//    1 implementation: use synchronized for method
//
//    private int money = 0;
//
//    public synchronized int incr() {
//        return money++;
//    }
//
//    public int getMoney() {
//        return money;
//    }

//    2 implementation: use AtomicInteger object
//
//    private final AtomicInteger money = new AtomicInteger(0);
//    public int incr() {
//        return money.incrementAndGet();
//    }
//
//    public int getMoney() {
//        return money.get();
//    }

    // 3 implementation: use ReentrantLock
    private int money = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public int incr() {
        lock.lock();
        try {
           money++;
        } finally {
            lock.unlock();
        }
        return money;
    }

    public int getMoney() {
        return money;
    }

}