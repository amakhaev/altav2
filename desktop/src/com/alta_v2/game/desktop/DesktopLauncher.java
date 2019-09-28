package com.alta_v2.game.desktop;

import com.alta_v2.game.CoreFacade;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("AlTa-v2");
		config.setWindowedMode(800, 600);
		config.setResizable(false);

		CoreFacade coreFacade = new CoreFacade();

		new Lwjgl3Application(coreFacade.getGame(), config);
	}
}
