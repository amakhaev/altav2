package com.alta_v2.facade.coreApi;

import com.alta_v2.game.utils.ChangeScreenResult;
import com.alta_v2.mediator.ProcessMediator;
import com.alta_v2.model.MenuDefinitionModel;
import com.alta_v2.model.TiledMapDefinitionModel;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ScreenCoreApiImpl implements ScreenCoreApi {

    private final ProcessMediator processMediator;

    /**
     * {@inheritDoc}
     */
    @Override
    public ChangeScreenResult loadMenuScreen(MenuDefinitionModel definition) {
        return this.processMediator.loadMenuScreen(definition);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChangeScreenResult loadTiledMapScreen(TiledMapDefinitionModel definition) {
        return this.processMediator.loadTiledMapScreen(definition);
    }
}
