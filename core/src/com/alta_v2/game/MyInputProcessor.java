package com.alta_v2.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

public class MyInputProcessor extends InputAdapter {

    @Override
    public boolean keyTyped (char character) {
        if (character == 'm') {
            System.out.println("Java heap size: " + Gdx.app.getJavaHeap() / 1024 / 1024 + " MB");
            System.out.println("Native heap size: " + Gdx.app.getNativeHeap() / 1024 / 1024 + " MB");
        }
        return false;
    }

}
