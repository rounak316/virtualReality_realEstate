package myapplication.prak.vrrealstate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import myapplication.prak.vrrealstate.layouts.fraggu;

public class Splash extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vr_view);


StringRequest req = new StringRequest( getResources().getString(R.string.server) +  "ub/api/banner.php", new Response.Listener<String>() {
    @Override
    public void onResponse(String response) {

        JSONArray ar = null;
        try {

            JSONObject onj = new JSONObject(response);
            ar = onj.getJSONArray("banner");

            MyApplication.city = onj.getJSONArray("city").toString();
            MyApplication.     property_type = onj.getJSONArray("property_type").toString();

            MyApplication.  project_price_range = onj.getJSONArray("project_price_range").toString();


            ArrayList<String> lol = new ArrayList<String>();
            ArrayList<String> lol2 = new ArrayList<String>();

            for(int i=0;i<ar.length();i++)
            {

          JSONObject obj =  ar.getJSONObject(i);
         String banner_title =        obj.getString("banner_title");
                String banner_image =         obj.getString("banner_image");
                lol.add(banner_title);
                lol2.add(banner_image);
            }

            Intent inte = new Intent(Splash.this , Splash2.class);

            inte.putStringArrayListExtra("lol" , lol);

            inte.putStringArrayListExtra("lol2" , lol2);

startActivity(inte);

            finish();

        } catch (JSONException e) {
            e.printStackTrace();
            finish();
        }





    }
}, new Response.ErrorListener() {
    @Override
    public void onErrorResponse(VolleyError error) {


        finish();

    }
});

        MyApplication.mRequestQueue.add(req);

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
            bun.putString("title" , "FASFASF");
            fraggu fr =      new fraggu();
            fr.setArguments(bun);
            return fr;
        }

        @Override
        public int getCount() {
            return 10;
        }
    }



}
