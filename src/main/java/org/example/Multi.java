package org.example;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Multi {

    private ExecutorService executorService = Executors.newFixedThreadPool(3);

    public void start1() {
        Future<String> future1 = executorService.submit(createTask("1"));
        Future<String> future2 = executorService.submit(createTask("2"));
        Future<String> future3 = executorService.submit(createTask("3"));

        try {
          String res1 =  future1.get();
          String res2 =  future2.get();
          String res3 =  future3.get();
          System.out.println(res1);
          System.out.println(res2);
          System.out.println(res3);
        } catch (Exception err) {
            System.out.println(err);
        }
        executorService.shutdown();
    }

    public void start2() {
        final List<Callable<String>> callables = IntStream.rangeClosed(1, 3)
                .mapToObj(i -> this.createTask("" + i))
                .toList();
        try {
            final List<Future<String>> futures = executorService.invokeAll(callables);
            final List<String> results = futures.stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .toList();

            results.forEach(System.out::println);
        } catch (Exception err) {
            System.out.println(err);
        }
        executorService.shutdown();
    }

    public void start3() {
        final CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(createAnotherTask("1"));
        final CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(createAnotherTask("2"));
        final CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(createAnotherTask("3"));

        try {
            final String res1 =  completableFuture1.get();
            final String res2 =  completableFuture2.get();
            final String res3 =  completableFuture3.get();
            System.out.println(res1);
            System.out.println(res2);
            System.out.println(res3);
        } catch (InterruptedException | ExecutionException err) {
            System.out.println(err);
        }
    }

    public void start4() {
        final List<CompletableFuture<String>> futures = IntStream.rangeClosed(1, 3)
                .mapToObj(i -> CompletableFuture.supplyAsync(createAnotherTask("" + i)))
                .toList();

        final List<String> results = futures.stream()
                .map(result -> result.join())
                .toList();
        results.forEach(System.out::println);

    }

    private String slowWork(final String val) {
        try {
            Thread.sleep(1000);
        } catch (final Exception err) {
            System.out.println(err);
        }
        return "done: " + val;
    }

    private Callable<String> createTask(final String val) {
        return () -> slowWork(val);
    }

    private Supplier<String> createAnotherTask(final String val) {
        return () -> slowWork(val);
    }

}