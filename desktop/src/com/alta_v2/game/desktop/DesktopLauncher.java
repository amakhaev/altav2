package com.alta_v2.game.desktop;

import com.alta_v2.game.AltaV2;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("AlTa-v2");
		config.setWindowedMode(800, 600);
		config.setResizable(false);

		new Lwjgl3Application(new AltaV2(), config);
	}
}
