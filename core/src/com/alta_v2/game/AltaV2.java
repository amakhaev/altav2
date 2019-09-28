package com.alta_v2.game;

import com.alta_v2.game.inputProcessor.InputProcessorFactory;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AltaV2 extends Game {

    private final ScreenSwitcher screenSwitcher;
    private final InputProcessorFactory processorFactory;

    @Override
	public void create () {
        this.screenSwitcher.setInitialScreen();
		Gdx.input.setInputProcessor(this.processorFactory.createDefaultInputProcessor(this.screenSwitcher));
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
