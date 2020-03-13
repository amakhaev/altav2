package com.alta_v2.physics.utils;

import com.badlogic.gdx.Gdx;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NpcMovementCalculator {

    public float getPositionX(float localX, float globalFocusX, int tileWidth) {
        float centerX = ((float) Gdx.graphics.getWidth() / 2);
        return localX * tileWidth + centerX - globalFocusX;
    }

    public float getPositionY(float localY, float globalFocusY, int tileHeight) {
        float centerY = ((float) Gdx.graphics.getHeight() / 2);
        return localY * tileHeight + centerY - globalFocusY;
    }
}
