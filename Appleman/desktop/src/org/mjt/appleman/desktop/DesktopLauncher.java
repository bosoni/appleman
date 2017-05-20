package org.mjt.appleman.desktop;

import org.mjt.appleman.Appleman;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher
{
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Appleman";
		cfg.width = 1024;
		cfg.height = 768;
		new LwjglApplication(new Appleman(), cfg);
	}
}
