package com.alta_v2.game.utils;

import java.util.concurrent.CompletableFuture;

public class ChangeScreenResult extends CompletableFuture<Void> {

    public void complete() {
        super.complete(null);
    }

}
