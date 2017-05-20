package org.mjt.appleman;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Logger;

public class Appleman extends Game
{
    @Override
    public void create()
    {
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);

        Globals.init();
        Globals.assets.getLogger().setLevel(Logger.DEBUG);

        Globals.SCREENWIDTH = Gdx.graphics.getWidth();
        Globals.SCREENHEIGHT = Gdx.graphics.getHeight();

        MenuScreen menuScreen = new MenuScreen(this);
        setScreen(menuScreen);
    }

    @Override
    public void render()
    {
        super.render();
    }

}
