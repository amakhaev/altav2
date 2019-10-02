package com.alta_v2.game;

import com.badlogic.gdx.Game;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AltaV2 extends Game {

    private final ScreenSwitcher screenSwitcher;

    @Override
	public void create () {
        this.screenSwitcher.setInitialScreen();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
