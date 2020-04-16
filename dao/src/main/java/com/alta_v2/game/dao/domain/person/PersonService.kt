package com.alta_v2.game.dao.domain.person

import com.alta_v2.game.dao.data.person.PersonEntity

interface PersonService {

    fun getNpcForMap(mapId: Int): List<PersonEntity>

    fun getPlayerForMap(mapId: Int): PersonEntity?
}