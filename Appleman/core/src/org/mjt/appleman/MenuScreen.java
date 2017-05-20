package org.mjt.appleman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;

public class MenuScreen implements Screen
{
    private Appleman main;
    private Scene menu;

    public MenuScreen(Appleman main)
    {
        this.main = main;
    }

    @Override
    public void show()
    {
        menu = new Scene();
        menu.createCamera();

        Array<Entity> ent = Entity.load("menu.g3db", null);
        menu.addEntity(ent);
    }

    @Override
    public void render(float delta)
    {
        Util.setDisplayMode();
        
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        menu.render();

        boolean start = false;
        // enter aloittaa pelin (kuten myös starttia klikkaaminen)
        if (Gdx.input.isKeyJustPressed(Keys.ENTER))
        {
            start = true;
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) || start)
        {
            int sel = menu.getEntityIndex(Gdx.input.getX(), Gdx.input.getY(), true);
            if (sel == 2 || start) // start
            {
                dispose();
                GameScreen gameScreen = new GameScreen(main);
                main.setScreen(gameScreen);
                return;
            }

            if (Globals.HTML == false)
            {
                if (sel == 0) // exit
                {
                    exitApp();
                    return;
                }
            }
        }

        // exit
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            if (Globals.HTML)
            {
                return;
            } else if (Globals.DESKTOP)
            {
                exitApp();
                return;
            }
        }

    }

    void exitApp()
    {
        dispose();
        Globals.dispose();
        Gdx.app.exit();
    }

    @Override
    public void resize(int width, int height)
    {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resume()
    {
    }

    @Override
    public void hide()
    {
    }

    // TöTö EI KUTSUTA AUTOMAATTISESTI joten ite pitää kutsua
    @Override
    public void dispose()
    {
        menu.dispose();
    }
}
