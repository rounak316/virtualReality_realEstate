package myapplication.prak.vrrealstate.layouts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import org.rajawali3d.Object3D;
import org.rajawali3d.cardboard.RajawaliCardboardRenderer;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.primitives.Sphere;

import java.util.ArrayList;

import myapplication.prak.vrrealstate.MyApplication;
import myapplication.prak.vrrealstate.R;

public class MyRenderer extends RajawaliCardboardRenderer {


    public void changeImage()
    {
        Log.d("debug1", "" + getCurrentScene().getNumChildren());
        ArrayList<Object3D> objectList = getCurrentScene().getChildrenCopy();
        Material material = objectList.get(0).getMaterial();
        for (ATexture texture : material.getTextureList())
        {
            material.removeTexture(texture);
            texture = null;
        }

        Texture t = new Texture("sphereTexture",R.drawable.loader);
        t.shouldRecycle(true);
        try {
            material.addTexture(t);
        }
        catch (Exception e){e.printStackTrace();}

    }

String str = "";
    public MyRenderer(Context context) {
        super(context);

        str=    (context.getExternalCacheDir() + "/vr_image.jpg").toString();

    }

    @Override
    protected void initScene() {


        Bitmap bit =   BitmapFactory.decodeFile(str);
        Sphere sphere = createPhotoSphereWithTexture(new Texture("photo", bit));

        getCurrentScene().addChild(sphere);

        getCurrentCamera().setPosition(Vector3.ZERO);
        getCurrentCamera().setFieldOfView(75);

    }

    private static Sphere createPhotoSphereWithTexture(ATexture texture) {

        Material material = new Material();
        material.setColor(0);

        try {
            material.addTexture(texture);
        } catch (ATexture.TextureException e) {
            throw new RuntimeException(e);
        }

        Sphere sphere = new Sphere(50, 64, 32);
        sphere.setScaleX(-1);
        sphere.setMaterial(material);

        return sphere;
    }
}
