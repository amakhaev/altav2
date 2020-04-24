package com.alta_v2.game.dao.domain.interaction

import com.alta_v2.game.dao.data.interaction.InteractionGroupEntity


interface InteractionService {

    fun getGroupsById(groupId: Int): List<InteractionGroupEntity>

}