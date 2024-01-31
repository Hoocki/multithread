package com.multithread.racecondition;

import static java.lang.String.format;

public class Main {

    private static final String SUCCESS_MSG = "Correct! Your sum is %s";
    private static final String ERROR_MSG = "Expected: %s, actual: %s";

    public static void main(final String[] args) {
        final var wallet = new Wallet();
        final var concurrentTransactor = new ConcurrentTransactor(wallet);
        concurrentTransactor.run();
        if (wallet.getMoney() != ConcurrentTransactor.TARGET_SUM) {
            final var msg = String.format(ERROR_MSG, ConcurrentTransactor.TARGET_SUM, wallet.getMoney());
            throw new RuntimeException(msg);
        }
        final var msg = format(SUCCESS_MSG, wallet.getMoney());
        System.out.println(msg);
    }

}