package myapplication.prak.vrrealstate;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Gallery;

import java.util.ArrayList;
import java.util.Arrays;

public class event_gallery extends AppCompatActivity {
RecyclerView gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_gallery);
int pos = getIntent().getIntExtra("pos" , 0);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
      final ArrayList<String> ar = getIntent().getStringArrayListExtra("img");
  ViewPager vp = (ViewPager) findViewById(R.id.vp);

        vp.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                frag_iv_vp_event fra =      new frag_iv_vp_event();
                Bundle bun = new Bundle();
                bun.putString("url", ar.get(position));


                Log.d("EVENT GA", "" );
fra.setArguments(bun);

                return fra;
            }

            @Override
            public int getCount() {
                return ar.size();
            }
        });
        vp.setCurrentItem(pos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_gallery, menu);
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
}
