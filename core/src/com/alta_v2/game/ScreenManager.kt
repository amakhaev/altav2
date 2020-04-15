package com.alta_v2.game

import com.alta_v2.exception.ChangeScreenException
import com.alta_v2.game.screen.GameScreen
import com.alta_v2.game.screen.GameScreenFactory
import com.alta_v2.game.utils.ChangeScreenResult
import com.alta_v2.mediator.screen.context.ScreenContext
import com.google.inject.Inject
import mu.KotlinLogging
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Provides the manager to handle screens.
 */
class ScreenManager @Inject constructor(private val screenFactory: GameScreenFactory) {

    private val log = KotlinLogging.logger {  }

    private var game: AltaV2? = null
    private val screenChangeIsLocked: AtomicBoolean = AtomicBoolean(false)

    fun setGame(game: AltaV2) {
        this.game = game
    }

    /**
     * Changes the screen.
     *
     * @param screenContext - the [ScreenContext] instance.
     */
    fun changeScreen(screenContext: ScreenContext?): ChangeScreenResult {
        if (game == null) {
            throw ChangeScreenException("AltaV2 container is null but must not")
        }
        if (screenChangeIsLocked.get()) {
            log.warn("Screen changing is not available because another change operation in progress.")
            throw ChangeScreenException("Screen changing is not available because another change operation in progress.")
        }
        val changeScreenResult = ChangeScreenResult()
        try {
            screenChangeIsLocked.set(true)
            val newGameScreen = screenFactory.createGameScreen(screenContext!!)

            if (game?.screen == null) {
                game?.screen = newGameScreen
                screenChangeIsLocked.set(false);
            } else {
                val oldScreen = game?.screen as GameScreen
                oldScreen.fadeOutScreen(Runnable {
                    game?.screen = newGameScreen
                    oldScreen.dispose()
                    screenChangeIsLocked.set(false)
                    changeScreenResult.complete()
                })
            }
        } catch (e: Exception) {
            screenChangeIsLocked.set(false)
            throw ChangeScreenException(e)
        }
        return changeScreenResult
    }
}