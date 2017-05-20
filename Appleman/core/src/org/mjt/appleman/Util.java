package org.mjt.appleman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class Util
{
    static float screenScale = 1;

    public static void setDisplayMode()
    {
        if (Gdx.input.isKeyPressed(Input.Keys.PAGE_UP))
        {
            screenScale += 0.1f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.PAGE_DOWN))
        {
            screenScale -= 0.1f;
            if (screenScale < 0.1f)
            {
                screenScale = 0.1f;
            }
        }

        int w = (int) ((float) Globals.SCREENWIDTH * screenScale), h = (int) ((float) Globals.SCREENHEIGHT * screenScale);
        Gdx.graphics.setWindowedMode(w, h);
    }

    public static boolean isNumeric(String str)
    {
        return str.matches("[+-]?\\d*(\\.\\d+)?");
    }

    public static Mesh createFullScreenQuad()
    {
        float[] verts = new float[20];
        int i = 0;
        verts[i++] = -1; // x1
        verts[i++] = -1; // y1
        verts[i++] = 0;
        verts[i++] = 0f; // u1
        verts[i++] = 0f; // v1

        verts[i++] = 1f; // x2
        verts[i++] = -1; // y2
        verts[i++] = 0;
        verts[i++] = 1f; // u2
        verts[i++] = 0f; // v2

        verts[i++] = 1f; // x3
        verts[i++] = 1f; // y2
        verts[i++] = 0;
        verts[i++] = 1f; // u3
        verts[i++] = 1f; // v3

        verts[i++] = -1; // x4
        verts[i++] = 1f; // y4
        verts[i++] = 0;
        verts[i++] = 0f; // u4
        verts[i++] = 1f; // v4
        Mesh mesh = new Mesh(true, 4, 0, // static mesh with 4 vertices and no indices
                new VertexAttribute(Usage.Position, 3, ShaderProgram.POSITION_ATTRIBUTE),
                new VertexAttribute(Usage.TextureCoordinates, 2, ShaderProgram.TEXCOORD_ATTRIBUTE + "0"));

        mesh.setVertices(verts);
        return mesh;
    }
    // original code by kalle_h
}
