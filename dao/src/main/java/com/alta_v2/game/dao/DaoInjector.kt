package com.alta_v2.game.dao

import com.alta_v2.game.dao.domain.effect.EffectService
import com.alta_v2.game.dao.domain.effect.EffectServiceImpl
import com.alta_v2.game.dao.domain.interaction.InteractionService
import com.alta_v2.game.dao.domain.interaction.InteractionServiceImpl
import com.alta_v2.game.dao.domain.map.MapService
import com.alta_v2.game.dao.domain.map.MapServiceImpl
import com.alta_v2.game.dao.domain.person.PersonService
import com.alta_v2.game.dao.domain.person.PersonServiceImpl
import com.alta_v2.game.dao.facade.definition.DefinitionDaoApi
import com.alta_v2.game.dao.facade.definition.DefinitionDaoApiImpl
import com.alta_v2.game.dao.provider.ConnectionSourceProvider
import com.google.inject.AbstractModule
import com.google.inject.Singleton
import com.j256.ormlite.support.ConnectionSource

class DaoInjector : AbstractModule() {
    override fun configure() {
        bind(ConnectionSource::class.java).toProvider(ConnectionSourceProvider::class.java).`in`(Singleton::class.java)

        bind(MapService::class.java).to(MapServiceImpl::class.java).`in`(Singleton::class.java)
        bind(PersonService::class.java).to(PersonServiceImpl::class.java).`in`(Singleton::class.java)
        bind(InteractionService::class.java).to(InteractionServiceImpl::class.java).`in`(Singleton::class.java)
        bind(EffectService::class.java).to(EffectServiceImpl::class.java).`in`(Singleton::class.java)

        bind(DefinitionDaoApi::class.java).to(DefinitionDaoApiImpl::class.java).`in`(Singleton::class.java)
    }
}