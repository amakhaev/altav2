package com.alta_v2.game.gamelogic.domain.map

import com.alta_v2.physics.task.MovementDirection
import com.alta_v2.physics.task.resultObserver.TaskResult

/**
 * Describes processor that working with map.
 */
interface MapProcessor {

    /**
     * Shows the title message box with given text.
     */
    fun showTitle(text: String)

    /**
     * Hides title message box.
     */
    fun hideTitle();

    /**
     * Moves the player person to given direction.
     */
    fun movePlayer(direction: MovementDirection): TaskResult?

    /**
     * Moves the NPC person to given direction.
     */
    fun moveNpc(npcId: Int, direction: MovementDirection): TaskResult?

    /**
     * Finds the object on map that is currently target for player.
     */
    fun findPurposeTargetedByPlayer(): Int?

    /**
     * Shows the common dialog with given text.
     */
    fun showDialog(text: String)

    /**
     * Hides the common dialog.
     */
    fun hideDialog()

    /**
     * Hides all dialogs.
     */
    fun hideAll()
}