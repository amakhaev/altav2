package com.alta_v2.game.dao.domain.map

import com.alta_v2.game.dao.data.map.MapEntity

interface MapService {

    fun getMapById(mapId: Int): MapEntity?

}