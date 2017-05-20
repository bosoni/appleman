package org.mjt.appleman;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSystem;
import com.badlogic.gdx.graphics.g3d.particles.batches.BillboardParticleBatch;
import com.badlogic.gdx.graphics.g3d.particles.batches.PointSpriteParticleBatch;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import java.util.Random;

public class Globals
{
    public static boolean DESKTOP;
    public static boolean HTML;

    public static int SCREENWIDTH = 1024, SCREENHEIGHT = 768;

    public static final Random random = new Random();
    public static final Vector3 tmpVector3 = new Vector3();
    public static final AnimationController.Transform tmpTransform = new AnimationController.Transform();
    public static final Matrix4 tmpMatrix = new Matrix4();

    public static AssetManager assets;
    public static ModelBatch modelBatch;
    public static SpriteBatch spriteBatch;
    public static DecalBatch decalBatch;
    public static ParticleSystem particleSystem;
    public static PointSpriteParticleBatch pointSpriteBatch;
    public static BillboardParticleBatch billboardParticleBatch;

    public static void init()
    {
        DESKTOP = Gdx.app.getType() == ApplicationType.Desktop;
        HTML = Gdx.app.getType() == ApplicationType.WebGL;

        assets = new AssetManager();
        spriteBatch = new SpriteBatch();
        modelBatch = new ModelBatch();
        particleSystem = ParticleSystem.get();
        pointSpriteBatch = new PointSpriteParticleBatch();
        billboardParticleBatch = new BillboardParticleBatch();
    }

    public static void createDecalBatch(Camera camera)
    {
        decalBatch = new DecalBatch(new CameraGroupStrategy(camera));
    }

    public static void dispose()
    {
        if (assets != null)
        {
            assets.dispose();
        }
        if (modelBatch != null)
        {
            modelBatch.dispose();
        }
        if (spriteBatch != null)
        {
            spriteBatch.dispose();
        }
        if (decalBatch != null)
        {
            decalBatch.dispose();
        }

        assets = null;
        modelBatch = null;
        spriteBatch = null;
        decalBatch = null;
        billboardParticleBatch = null;
    }

}
