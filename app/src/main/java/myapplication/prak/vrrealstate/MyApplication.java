package myapplication.prak.vrrealstate;

import android.app.Application;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Prakhar on 7/11/2015.
 */
public class MyApplication extends Application
{
  public   static String city = "";
    public   static  String     property_type = "";
    public   static  String    project_price_range="";
    public    static RequestQueue mRequestQueue;
static public ImageLoader imageLoader;
    @Override
    public void onCreate() {
        super.onCreate();
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());




        imageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(
                    10);

            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }

            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
    }

    // Getter for RequestQueue or just make it public
}