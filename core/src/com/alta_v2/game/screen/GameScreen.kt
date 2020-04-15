package com.alta_v2.game.screen

import com.alta_v2.game.overlay.OverlayComponent
import com.alta_v2.game.overlay.OverlayComponentImpl
import com.alta_v2.game.overlay.OverlayFactory
import com.alta_v2.mediator.screen.context.ScreenContext
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.google.inject.assistedinject.Assisted
import com.google.inject.assistedinject.AssistedInject

/**
 * Provides the base screen-container for the game.
 */
class GameScreen @AssistedInject constructor(@param:Assisted private val context: ScreenContext,
                                             private val overlayFactory: OverlayFactory) : ScreenAdapter() {

    companion object {
        private const val FADE_DURATION = 0.25f
    }

    private lateinit var overlayComponent: OverlayComponent

    /**
     * {@inheritDoc}
     */
    override fun show() {
        overlayComponent = overlayFactory.createOverlay()
        overlayComponent.show(FADE_DURATION)
        context.screenUpdater.init(context.screenState)
        context.screenRender.init(context.screenState)
        context.dialogRender.init(context.screenState)
    }

    override fun hide() {}

    /**
     * {@inheritDoc}
     */
    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        context.screenUpdater.update(delta, context.screenState)
        context.screenRender.render(delta, context.screenState)
        context.dialogRender.render(delta, context.dialogState)

        overlayComponent.act(delta)
        overlayComponent?.render(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    }

    /**
     * {@inheritDoc}
     */
    override fun dispose() {
        context.destroy()
        overlayComponent.dispose()
    }

    /**
     * Fades out the screen.
     *
     * @param postAction - the action to be run after screen hides.
     */
    fun fadeOutScreen(postAction: Runnable?) {
        overlayComponent.hide(FADE_DURATION, postAction)
    }

}