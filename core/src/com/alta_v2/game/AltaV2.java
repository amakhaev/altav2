package com.alta_v2.game;

import com.alta_v2.game.inputProcessor.InputProcessorFactory;
import com.alta_v2.game.screen.GameScreenFactory;
import com.alta_v2.mediatorModule.ProcessMediator;
import com.alta_v2.mediatorModule.ProcessMediatorFactory;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.google.inject.Inject;

public class AltaV2 extends Game {

	private final InputProcessor inputProcessor;
	private final ProcessMediator processMediator;

	/**
	 * Initialize new instance of {@link AltaV2}.
	 */
	@Inject
    public AltaV2(ProcessMediatorFactory processMediatorFactory,
				  InputProcessorFactory inputProcessorFactory,
				  GameScreenFactory screenFactory) {
		ScreenManager screenManager = new ScreenManager(this, screenFactory);
		this.processMediator = processMediatorFactory.createProcessMediator(screenManager);
		this.inputProcessor = inputProcessorFactory.createDefaultInputProcessor(this.processMediator);
	}

	/**
	 * {@inheritDoc}
	 */
    @Override
	public void create () {
		Gdx.input.setInputProcessor(this.inputProcessor);
		this.processMediator.loadMenuScreen();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		System.exit(0);
	}
}
