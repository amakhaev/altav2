package com.alta_v2.game.overlay

/**
 * Provides the factory to create actors.
 */
interface OverlayFactory {

    /**
     * Creates the overlay.
     *
     * @return created [OverlayComponentImpl] instance.
     */
    fun createOverlay(): OverlayComponentImpl
}