package com.alta_v2.game.dao.domain.effect

import com.alta_v2.game.dao.data.effect.EffectAggregationEntity

interface EffectService {

    /**
     * Gets the map of EffectAggregationEntity.
     * {code}key{code} is ID of specific group.
     * {code}value{code} is the list of EffectAggregationEntity sorted by group order field.
     */
    fun getEffects(groupIds: List<Int>): Map<Int, List<EffectAggregationEntity>>

}