package com.alta_v2.game.gamelogic.utils;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class LogicThreadFactory implements ThreadFactory {

    private final AtomicInteger threadCounter = new AtomicInteger();
    private final String threadName;

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.threadName + this.threadCounter.getAndIncrement());
        thread.setDaemon(true);
        return thread;
    }
}
