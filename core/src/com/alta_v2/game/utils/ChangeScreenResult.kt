package com.alta_v2.game.utils

import java.util.concurrent.CompletableFuture

class ChangeScreenResult : CompletableFuture<Void?>() {
    fun complete() {
        super.complete(null)
    }
}