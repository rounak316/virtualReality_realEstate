package myapplication.prak.vrrealstate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.SortedList;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.appdatasearch.GetRecentContextCall;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;
import java.util.ArrayList;

import myapplication.prak.vrrealstate.layouts.*;

public class Splash2 extends AppCompatActivity {

    Handler handler ;
Runnable runna = new Runnable() {
    @Override
    public void run() {
        if(vp!=null)
        {

            vp.setCurrentItem((vp.getCurrentItem()+1) % lol.size() , true);


       handler.postDelayed(runna , 5000);


        }
    }
};


    @Override
    protected void onStart() {
        super.onStart();

        handler = new Handler();
        handler.postDelayed(runna , 5000);

        getRegId();


    }
    LinearLayout tabindi;

    @Override
    protected void onStop() {
        super.onStop();

        handler.removeCallbacks(runna);
    }
    ViewPager vp;
    ArrayList<String> lol , lol2 ;

    GoogleCloudMessaging gcm;
    String regid;
    String PROJECT_NUMBER = "258216676834";

    public void getRegId(){
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }
                    regid = gcm.register(PROJECT_NUMBER);

                    StringRequest req = new StringRequest(getResources().getString(R.string.server)+ "ub/api/CLOUD%20MESSSAGE/register.php?id=" + regid, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("YES BITCHESKA", "DSADONELADSA");

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Log.d("YES BITCHESKA", "DSADONELADSA" + error);
                        }
                    });
MyApplication.mRequestQueue.add(req);
                    msg = "Device registered, registration ID=" + regid;
                    Log.i("GCM", msg);
                    Log.d("YES BITCHES", "DSADSA");


                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    Log.i("GCM",  ex + " ");


                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
            //    Toast.makeText(Splash2.this, "GCM registered", 1000).show();
            }
        }.execute(null, null, null);
    }








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        View homeowner = findViewById(R.id.homeowner);
        homeowner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                v.setDrawingCacheEnabled(true);

// this is the important code :)
// Without it the view will have a dimension of 0,0 and the bitmap will be null
                v.measure(View.MeasureSpec.makeMeasureSpec((int) v.getX(), View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec((int) v.getY(), View.MeasureSpec.UNSPECIFIED));
//                v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
//v.layout( (int) v.getX() ,(int) v.getY()  , v.getMeasuredWidth(), v.getMeasuredHeight() );
                v.buildDrawingCache(true);
                Bitmap bm = Bitmap.createBitmap(v.getDrawingCache());
                v.setDrawingCacheEnabled(false); // clear drawing cache

String barcodeNumber = "poopy";

             String pathy =   MediaStore.Images.Media.insertImage(getContentResolver(), bm,
                        barcodeNumber + ".jpg", barcodeNumber + ".jpg Card Image");



                Uri uri1 = Uri.parse(pathy);
                Uri uri2 = Uri.parse(pathy);
                Uri uri3 = Uri.parse(pathy);

                ArrayList<Uri> imageUriArray = new ArrayList<Uri>();
                imageUriArray.add(uri1);
                imageUriArray.add(uri2);

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Text caption message!!");
                intent.setType("text/plain");
                intent.setType("image/jpeg");
                intent.setPackage("com.whatsapp");
                intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUriArray);
                startActivity(intent);
            }
        });


  lol =   getIntent().getStringArrayListExtra("lol");
   lol2 =     getIntent().getStringArrayListExtra("lol2");
        tabindi = (LinearLayout) findViewById(R.id.tabindi);
for(int i=0;i < lol.size();i++) {
    tabindi.addView(getLayoutInflater().inflate(R.layout.qweqwe,null));
}
if(tabindi.getChildCount()>0)
        tabindi.getChildAt(0).setPressed(true);

  vp = (ViewPager) findViewById(R.id.vp);
vp.setAdapter(new adap(getSupportFragmentManager()));

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0;i<        tabindi.getChildCount();i++)
                {

                    tabindi.getChildAt(i).setPressed(false);
                }
                tabindi.getChildAt(position).setPressed(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        View guest = findViewById(R.id.guest);

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Splash2.this , myapplication.prak.vrrealstate.MainActivity.class));
finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    class adap extends FragmentPagerAdapter
    {



        public adap(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bun = new Bundle();
            bun.putString("title" , "" + lol.get(position));
            bun.putString("url" , "" + lol2.get(position));
            fraggu fr =      new fraggu();
            fr.setArguments(bun);
            return fr;
        }

        @Override
        public int getCount() {
            return lol.size();
        }
    }



}
