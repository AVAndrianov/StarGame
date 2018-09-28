package com.avandrinov.stargame.desktop;

import com.avandrinov.stargame.StarGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.avandrinov.stargame.StarGameExample;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.resizable = false;
//		config.height = 512;
//		config.width = 1024;
		new LwjglApplication(new StarGame(), config);
	}
}
