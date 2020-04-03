package com.alta_v2.game.desktop;

import com.alta_v2.CoreInjector;
import com.alta_v2.SharedInjector;
import com.alta_v2.game.AltaV2;
import com.alta_v2.game.dao.DaoInjector;
import com.alta_v2.game.gamelogic.GameLogic;
import com.alta_v2.game.gamelogic.GameLogicInjector;
import com.alta_v2.rendering.config.AppConfig;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class DesktopLauncher {
	public static void main (String[] arg) {

		Injector injector = Guice.createInjector(
				new SharedInjector(), new CoreInjector(), new GameLogicInjector(), new DaoInjector()
		);
		AppConfig appConfig = injector.getInstance(AppConfig.class);

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle(appConfig.getTitle());
		config.setWindowedMode(appConfig.getWidth(), appConfig.getHeight());
		config.setResizable(false);


		AltaV2 altaV2 = injector.getInstance(GameLogic.class).getAltaV2();

		new Lwjgl3Application(altaV2, config);
	}
}
