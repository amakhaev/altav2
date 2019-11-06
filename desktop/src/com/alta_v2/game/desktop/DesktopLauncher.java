package com.alta_v2.game.desktop;

import com.alta_v2.CoreInjector;
import com.alta_v2.game.AltaV2;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class DesktopLauncher {
	public static void main (String[] arg) {

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("AlTa-v2");
		config.setWindowedMode(800, 600);
		config.setResizable(false);

		Injector coreInjector = Guice.createInjector(new CoreInjector());
		new Lwjgl3Application(coreInjector.getInstance(AltaV2.class), config);
	}
}
