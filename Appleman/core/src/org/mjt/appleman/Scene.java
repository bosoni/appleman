package org.mjt.appleman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;

public class Scene
{
    public PerspectiveCamera camera;
    /**
     * jos t�m� asetettu, modelit renderoidaan k�ytt�m�ll� t�t� envi� eik�
     * modelin omaa envi�.
     */
    public Environment environment = null;
    private Array<Entity> entities = new Array<Entity>();

    public int visibleEntities = 0;

    public void createCamera()
    {
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0, 150, 0);
        camera.lookAt(Vector3.Zero);
        camera.near = 1f;
        camera.far = 1000;
        camera.update();
    }

    public void addEntity(final Entity ent)
    {
        entities.add(ent);
    }

    public void addEntity(final Array<Entity> ent)
    {
        for (Entity e : ent)
        {
            entities.add(e);
        }
    }

    public Entity getEntity(final int index)
    {
        return entities.get(index);
    }

    public Entity getEntity(final String name)
    {
        for (int q = 0; q < entities.size; q++)
        {
            if (entities.get(q).name.equals(name))
            {
                return entities.get(q);
            }
        }
        return null;
    }

    public void removeEntity(final String name)
    {
        for (int q = 0; q < entities.size; q++)
        {
            if (entities.get(q).name.equals(name))
            {
                entities.removeIndex(q);
                break;
            }
        }
    }

    /**
     * renderoi frustumissa olevat entityt.
     */
    public void render()
    {
        visibleEntities = 0;

        Globals.modelBatch.begin(camera);

        for (int i = 0; i < entities.size; i++)
        {
            if ((entities.get(i).checkBB && entities.get(i).isVisibleBB(camera, entities.get(i))) // jos bb tai sphere
                    // frustumissa, rendaa
                    || entities.get(i).isVisibleSphere(camera, entities.get(i)))
            {
                if (environment != null)
                {
                    Globals.modelBatch.render(entities.get(i), environment);
                } else
                {
                    Globals.modelBatch.render(entities.get(i), entities.get(i).environment);
                }
                visibleEntities++;
            }
        }
        Globals.modelBatch.end();
    }

    /**
     * palauttaa l�himm�n entityn indexin joka screenX,screenY kohdassa.
     * <p>
     * jos checkBB==true, tsekataan onko boundingbox frustumissa, muuten onko
     * sphere frustumissa.
     *
     * @param screenX
     * @param screenY
     * @param checkBB
     * @return
     */
    public int getEntityIndex(int screenX, int screenY, boolean checkBB)
    {
        Ray ray = camera.getPickRay(screenX, screenY);
        int result = -1;
        float len = 1000000;
        for (int i = 0; i < entities.size; i++)
        {
            if ((checkBB && Intersector.intersectRayBounds(ray, entities.get(i).bounds, Globals.tmpVector3))
                    || (!checkBB && Intersector.intersectRaySphere(ray, entities.get(i).center, entities.get(i).radius,
                            Globals.tmpVector3)))
            {
                float len2 = Globals.tmpVector3.len2();
                if (len2 < len)
                {
                    len = len2;
                    result = i;
                }
            }
        }
        return result;
    }

    public void dispose()
    {
        //TODO for loop ja joka entitys dispose		
        entities.clear();
    }

}
