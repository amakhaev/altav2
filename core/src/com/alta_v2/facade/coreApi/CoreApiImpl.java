package com.alta_v2.facade.coreApi;

import com.alta_v2.mediatorModule.ProcessMediator;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class CoreApiImpl implements CoreApi {

    private final ProcessMediator processMediator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadMenuScreen() {
        this.processMediator.loadMenuScreen();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadTiledMapScreen() {
        this.processMediator.loadTiledMapScreen();
    }
}
