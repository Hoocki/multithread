package com.multithread.racecondition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is a third party service. We cannot change it.
 */
public class ConcurrentTransactor {

    private final Wallet wallet;

    public static final int TARGET_SUM = 1_000_000;

    private final ExecutorService executorService = Executors.newFixedThreadPool(100);

    public ConcurrentTransactor(final Wallet wallet) {
        this.wallet = wallet;
    }

    public void run() {
        final var tasks = createTasks();
        try {
            executorService.invokeAll(tasks);
        } catch (final Exception err) {
            System.out.println(err);
        }
        executorService.shutdown();
    }

    private List<Callable<Integer>> createTasks() {
        final List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i < TARGET_SUM; i++) {
            tasks.add(wallet::incr);
        }
        return tasks;
    }

}
