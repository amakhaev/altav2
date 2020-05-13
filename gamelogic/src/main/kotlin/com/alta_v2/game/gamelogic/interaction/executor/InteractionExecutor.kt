package com.alta_v2.game.gamelogic.interaction.executor

import com.alta_v2.game.gamelogic.data.interaction.InteractionModel
import com.google.inject.Inject

class InteractionExecutor @Inject constructor(private val effectExecutor: EffectExecutor) : ListItemExecutor<InteractionModel>() {

    override fun executeItem(item: ExecutionItem<InteractionModel>) {
        effectExecutor.prepare(item.data.effectModels).thenAccept{ item.result.complete() }
        effectExecutor.execute()
    }
}