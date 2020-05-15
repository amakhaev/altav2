package com.alta_v2.game.gamelogic.interaction.executor

import java.util.concurrent.CompletableFuture

class ExecutionResult : CompletableFuture<Void>() {

    fun complete() = super.complete(null)
}