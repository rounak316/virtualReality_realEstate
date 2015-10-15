package myapplication.prak.vrrealstate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import myapplication.prak.vrrealstate.layouts.*;
import myapplication.prak.vrrealstate.layouts.MainActivity;

/**
 * Created by Prakhar on 7/16/2015.
 */
public class virtual_reality extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vr_view);
        ArrayList<String> lio = new ArrayList<String>();
String walky = getIntent().getStringExtra("walky");
        lio = getIntent().getStringArrayListExtra("vr_url");

        int counter = getIntent().getIntExtra("pos", 0);

        final String url = lio.get(counter = (counter % (lio.size())));

   //     final String  ur2l = "1143699695110672406_1569392336680957_1680100571279565891_n.jpg";

if(walky==null || walky.length() <=3)
{
    walky = "{}";

}
        String DICY = "";
        try {
            JSONObject dec = new JSONObject(walky);

            DICY =  dec.getString(url);

            Log.d("url_recieved", lio + " " + walky);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("url_recieved", walky + " " + e);
            DICY = "";
        }





        final int finalCounter = counter;
        final ArrayList<String> finalLio = lio;
        final String finalWalky = walky;
        final String finalDICY = DICY;
        MyApplication.imageLoader.get(getResources().getString(R.string.server) + "ub/admin/images/virtual_reality/" + url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {

                Snackbar.make(getWindow().getDecorView() , "Please wait.." , Snackbar.LENGTH_LONG);

            Intent intr =     new Intent(virtual_reality.this , myapplication.prak.vrrealstate.layouts.MainActivity.class);
              intr.putExtra("url" , url);
                intr.putExtra("cnt" , finalCounter);
                intr.putStringArrayListExtra("vr_url", finalLio);
                intr.putExtra("walky", finalWalky);
                intr.putExtra("DICY", finalDICY);
//startActivity(intr);

if(response.getBitmap()!=null) {
    Bitmap b =response.getBitmap();
    try {

        File file = new File(virtual_reality.this.getExternalCacheDir() + "/vr_image.jpg" );

      {
            try {
                file.createNewFile();
                b.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(virtual_reality.this.getExternalCacheDir() + "/vr_image.jpg"));
           //     Toast.makeText(virtual_reality.this , " Downloaded " , 1000).show();
                //virtual_reality.this.getExternalCacheDir() + "/vr_image.jpg"
                startActivity(intr);
finish();

            } catch (IOException e) {
                e.printStackTrace();
            //    Toast.makeText(virtual_reality.this , " IOException " + e , 1000).show();
            }
        }


    } catch (Exception e) {


        e.printStackTrace();
        Snackbar.make(getWindow().getDecorView() , "Unable to download image" , Snackbar.LENGTH_LONG);
    //    Toast.makeText(virtual_reality.this , " FileNotFoundException " + e , 1000).show();
        finish();
    }
}



            }

            @Override
            public void onErrorResponse(VolleyError error) {

                Snackbar.make(getWindow().getDecorView(), "Unable to download image", Snackbar.LENGTH_LONG);

finish();
            }
        });

    }
}
