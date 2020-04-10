package com.alta_v2.game.dao;

import com.alta_v2.game.dao.domain.map.MapService;
import com.alta_v2.game.dao.domain.map.MapServiceImpl;
import com.alta_v2.game.dao.domain.person.PersonService;
import com.alta_v2.game.dao.domain.person.PersonServiceImpl;
import com.alta_v2.game.dao.facade.definition.DefinitionDaoApi;
import com.alta_v2.game.dao.facade.definition.DefinitionDaoApiImpl;
import com.alta_v2.game.dao.facade.definition.mapper.PersonMapper;
import com.alta_v2.game.dao.provider.ConnectionSourceProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.j256.ormlite.support.ConnectionSource;

public class DaoInjector extends AbstractModule {

    @Override
    protected void configure() {
        bind(ConnectionSource.class).toProvider(ConnectionSourceProvider.class).in(Singleton.class);

        bind(MapService.class).to(MapServiceImpl.class).in(Singleton.class);
        bind(PersonService.class).to(PersonServiceImpl.class).in(Singleton.class);

        bind(PersonMapper.class).toInstance(PersonMapper.INSTANCE);

        bind(DefinitionDaoApi.class).to(DefinitionDaoApiImpl.class).in(Singleton.class);
    }

}
