package com.alta_v2.game.gamelogic.interaction.executor

interface Executor<T> {

    /**
     * Prepares executor with given data.
     */
    fun prepare(data: T): ExecutionResult

    /**
     * Executes process.
     */
    fun execute()

    /**
     * Makes next step.
     */
    fun makeNextStep()
}