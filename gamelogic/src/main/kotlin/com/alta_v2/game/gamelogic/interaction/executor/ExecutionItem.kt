package com.alta_v2.game.gamelogic.interaction.executor

data class ExecutionItem<T>(val data: T, val result: ExecutionResult) {

    val isCompleted
        get() = result.isDone

}