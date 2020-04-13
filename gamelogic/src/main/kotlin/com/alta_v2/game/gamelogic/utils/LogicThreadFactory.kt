package com.alta_v2.game.gamelogic.utils

import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicInteger

class LogicThreadFactory(private val threadName: String) : ThreadFactory {

    private val threadCounter = AtomicInteger()

    override fun newThread(runnable: Runnable): Thread {
        val thread = Thread(runnable, threadName + threadCounter.getAndIncrement())
        thread.isDaemon = true
        return thread
    }
}