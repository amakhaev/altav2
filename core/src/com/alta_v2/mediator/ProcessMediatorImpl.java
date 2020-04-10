package com.alta_v2.mediator;

import com.alta_v2.game.ScreenManager;
import com.alta_v2.game.utils.ChangeScreenResult;
import com.alta_v2.mediator.screen.context.ContextFactory;
import com.alta_v2.mediator.screen.context.ScreenContext;
import com.alta_v2.model.MenuDefinitionModel;
import com.alta_v2.model.TiledMapDefinitionModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

/**
 * Provides the mediator that responsible for orchestration of game.
 */
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ProcessMediatorImpl implements ProcessMediator {

    private final ContextFactory contextFactory;
    private final ScreenManager screenManager;

    @Getter
    private ScreenContext currentContext;

    /**
     * {@inheritDoc}
     */
    @Override
    public ChangeScreenResult loadMenuScreen(MenuDefinitionModel definition) {
        this.currentContext = this.contextFactory.createMenuContext(definition);
        return this.screenManager.changeScreen(this.currentContext);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChangeScreenResult loadTiledMapScreen(TiledMapDefinitionModel definition) {
        this.currentContext = this.contextFactory.createTiledMapContext(definition);
        return this.screenManager.changeScreen(this.currentContext);
    }
}
