package com.alta_v2.facade.coreApi;

import com.alta_v2.mediatorModule.ProcessMediator;
import com.alta_v2.model.MenuDefinitionModel;
import com.alta_v2.model.TiledMapDefinitionModel;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class CoreApiImpl implements CoreApi {

    private final ProcessMediator processMediator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadMenuScreen(MenuDefinitionModel definition) {
        this.processMediator.loadMenuScreen(definition);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadTiledMapScreen(TiledMapDefinitionModel definition) {
        this.processMediator.loadTiledMapScreen(definition);
    }
}
