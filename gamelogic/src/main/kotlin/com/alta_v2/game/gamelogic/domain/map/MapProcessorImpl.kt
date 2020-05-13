package com.alta_v2.game.gamelogic.domain.map

import com.alta_v2.facade.dialogApi.DialogCoreApi
import com.alta_v2.facade.tiledMapApi.TiledMapCoreApi
import com.alta_v2.physics.task.MovementDirection
import com.alta_v2.physics.task.resultObserver.TaskResult
import com.google.inject.Inject

class MapProcessorImpl @Inject constructor(private val dialogCoreApi: DialogCoreApi,
                                           private val tiledMapCoreApi: TiledMapCoreApi) : MapProcessor {

    override fun showTitle(text: String) = dialogCoreApi.showTitleDialog(text, 3000f)

    override fun hideTitle() = dialogCoreApi.hideTitleDialog()

    override fun showDialog(text: String) = dialogCoreApi.showDialog(text)

    override fun hideDialog() = dialogCoreApi.hideDialog()

    override fun hideAll() {
        dialogCoreApi.hideTitleDialog()
        dialogCoreApi.hideDialog()
    }

    override fun movePlayer(direction: MovementDirection) = tiledMapCoreApi.performPlayerMovement(direction)

    override fun moveNpc(npcId: Int, direction: MovementDirection) = tiledMapCoreApi.performNpcMovement(npcId, direction)

    override fun findPurposeTargetedByPlayer(): Int? = tiledMapCoreApi.playerPurpose
}