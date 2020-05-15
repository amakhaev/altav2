package com.alta_v2.game.gamelogic.interaction.executor

import com.alta_v2.game.gamelogic.data.interaction.InteractionModel
import com.google.inject.Inject
import mu.KotlinLogging

class InteractionExecutor @Inject constructor(private val effectExecutor: EffectExecutor) : ListItemExecutor<InteractionModel>() {

    private val log = KotlinLogging.logger {  }

    override fun executeItem(item: ExecutionItem<InteractionModel>) {
        effectExecutor.prepare(item.data.effectModels).thenAccept{ item.result.complete() }
        effectExecutor.execute()
    }

    override fun makeNextStep() {
        if (currentItem?.isCompleted!!) {
            log.warn("Current interaction ${currentItem?.data?.id} ")
            return
        }

        effectExecutor.makeNextStep()
    }
}