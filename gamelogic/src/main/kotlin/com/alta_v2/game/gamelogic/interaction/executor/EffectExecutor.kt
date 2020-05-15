package com.alta_v2.game.gamelogic.interaction.executor

import com.alta_v2.game.gamelogic.data.interaction.DialogModel
import com.alta_v2.game.gamelogic.data.interaction.EffectModel
import com.google.inject.Inject
import mu.KotlinLogging

class EffectExecutor @Inject constructor(private val dialogExecutor: DialogExecutor) : ListItemExecutor<EffectModel>() {

    private val log = KotlinLogging.logger {  }
    private val effectContexts: MutableList<ExecutionContext> = arrayListOf()

    override fun executeItem(item: ExecutionItem<EffectModel>) {
        effectContexts.clear()

        item.data.dialogEffect?.run { effectContexts.add(createDialogContext(this)) }
//        item.data.changeTextureEffect?.run { executeChangeTextureEffect(this) }
//        item.data.movementEffect?.run { executeMovementEffect(this) }

        if (effectContexts.isEmpty()) {
            item.result.complete()
        } else {
            effectContexts.forEach{ it.executor.execute() }
        }
    }

    override fun makeNextStep() {
        if (currentItem?.isCompleted!!) {
            log.warn("Current effect ${currentItem?.data?.effectId} ")
            return
        }

        val notCompletedEffects = effectContexts.filter { !it.result.isDone }
        notCompletedEffects.forEach { it.executor.makeNextStep() }
    }

    private fun createDialogContext(dialogEffect: DialogModel): ExecutionContext {
        val result = dialogExecutor.prepare(dialogEffect)
        result.thenAccept { onComplete() }
        return ExecutionContext(dialogExecutor, result)
    }

    private fun onComplete() {
        val notCompletedEffects = effectContexts.filter { !it.result.isDone }
        if (notCompletedEffects.isEmpty()) {
            currentItem!!.result.complete()
        }
    }
}

private data class ExecutionContext(val executor: Executor<*>, val result: ExecutionResult)