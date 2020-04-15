package com.alta_v2.physics.utils

import com.badlogic.gdx.Gdx

class NpcMovementCalculator {

    companion object {
        fun getPositionX(localX: Float, globalFocusX: Float, tileWidth: Int): Float {
            val centerX = Gdx.graphics.width.toFloat() / 2
            return localX * tileWidth + centerX - globalFocusX
        }

        fun getPositionY(localY: Float, globalFocusY: Float, tileHeight: Int): Float {
            val centerY = Gdx.graphics.height.toFloat() / 2
            return localY * tileHeight + centerY - globalFocusY
        }
    }
}