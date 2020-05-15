package com.alta_v2.game.gamelogic.interaction.executor

import mu.KotlinLogging

abstract class ListItemExecutor<T> : Executor<List<T>> {

    private val log = KotlinLogging.logger {  }
    private val items = ArrayList<T>()

    private lateinit var executionResult: ExecutionResult

    protected var currentItem: ExecutionItem<T>? = null

    final override fun prepare(data: List<T>): ExecutionResult {
        if (!canExecute()) {
            log.error("Previous execution not finished yet")
            return executionResult
        }

        executionResult = ExecutionResult()
        items.addAll(data)
        return executionResult
    }

    final override fun execute() {
        scheduleItemAndRun()
    }

    /**
     * Executes one item.
     */
    protected abstract fun executeItem(item: ExecutionItem<T>)

    private fun canExecute() = items.isEmpty() && isCurrentItemDone()
    private fun isCurrentItemDone() = currentItem == null || currentItem?.isCompleted!!

    private fun scheduleItemAndRun() {
        if (!isCurrentItemDone()) {
            log.warn("Attempt to schedule next item but previous still in progress")
            return
        }

        if (items.isEmpty()) {
            executionResult.complete()
            return
        }

        val result = ExecutionResult()
        result.thenAccept{ scheduleItemAndRun() }

        currentItem = ExecutionItem(items[0], result)
        items.removeAt(0)
        currentItem?.run { executeItem(this) }
    }
}