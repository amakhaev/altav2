package com.alta_v2.game;

import com.alta_v2.game.inputProcessor.InputProcessorFactory;
import com.alta_v2.mediatorModule.ProcessMediator;
import com.alta_v2.model.MenuDefinitionModel;
import com.alta_v2.mediatorModule.serde.ActionListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.google.inject.Inject;

public class AltaV2 extends Game {

	private final ProcessMediator processMediator;
	private final InputProcessorFactory inputProcessorFactory;
	private InputProcessor inputProcessor;

	/**
	 * Initialize new instance of {@link AltaV2}.
	 */
	@Inject
    public AltaV2(InputProcessorFactory inputProcessorFactory,
				  ProcessMediator processMediator,
				  ScreenManager screenManager) {
		this.inputProcessorFactory = inputProcessorFactory;
		this.processMediator = processMediator;
		screenManager.setGame(this);
	}

	/**
	 * {@inheritDoc}
	 */
    @Override
	public void create () {
		Gdx.input.setInputProcessor(this.inputProcessor);
		this.processMediator.loadMenuScreen(new MenuDefinitionModel());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		System.exit(0);
	}

	public void setInputListener(ActionListener actionListener) {
		this.inputProcessor = this.inputProcessorFactory.createDefaultInputProcessor(actionListener);
	}
}
