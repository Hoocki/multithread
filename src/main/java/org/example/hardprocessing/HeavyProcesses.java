package org.example.hardprocessing;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class HeavyProcesses {

    private final ExecutorService EXECUTOR_SERVICE = Executors.newWorkStealingPool();

    private final int THREAD_SLEEP = 1000;

    public void start1() {
        final Future<String> future1 = EXECUTOR_SERVICE.submit(createCallableTask("1"));
        final Future<String> future2 = EXECUTOR_SERVICE.submit(createCallableTask("2"));
        final Future<String> future3 = EXECUTOR_SERVICE.submit(createCallableTask("3"));

        try {
            final String res1 = future1.get();
            final String res2 = future2.get();
            final String res3 = future3.get();
            System.out.println(res1);
            System.out.println(res2);
            System.out.println(res3);
        } catch (final Exception err) {
            System.out.println(err);
        }
        EXECUTOR_SERVICE.shutdown();
    }

    public void start2() {
        final List<Callable<String>> tasks = IntStream.rangeClosed(1, 3)
                .mapToObj(i -> createCallableTask(String.valueOf(i)))
                .toList();
        try {
            final List<Future<String>> futures = EXECUTOR_SERVICE.invokeAll(tasks);
            futures.stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .forEach(System.out::println);

        } catch (Exception err) {
            System.out.println(err);
        }
        EXECUTOR_SERVICE.shutdown();
    }

    public void start3() {
        final CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(createSupplierTask("1"));
        final CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(createSupplierTask("2"));
        final CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(createSupplierTask("3"));

        final String res1 = completableFuture1.join();
        final String res2 = completableFuture2.join();
        final String res3 = completableFuture3.join();
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }

    public void start4() {
        final List<CompletableFuture<String>> futures = IntStream.rangeClosed(1, 3)
                .mapToObj(i -> CompletableFuture.supplyAsync(createSupplierTask(String.valueOf(i))))
                .toList();

        futures.stream()
                .map(CompletableFuture::join)
                .forEach(System.out::println);
    }

    private String slowWork(final String val) {
        try {
            Thread.sleep(THREAD_SLEEP);
        } catch (final Exception err) {
            System.out.println(err);
        }
        return "done: " + val;
    }

    private Callable<String> createCallableTask(final String val) {
        return () -> slowWork(val);
    }

    private Supplier<String> createSupplierTask(final String val) {
        return () -> slowWork(val);
    }

}