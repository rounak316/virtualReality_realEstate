package myapplication.prak.vrrealstate;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

public class event_before_gall extends AppCompatActivity {
RecyclerView gallery;



    class ra extends  RecyclerView.Adapter<ra.vh>
    {
        class vh extends RecyclerView.ViewHolder
        {

            public vh(View itemView) {
                super(itemView);
            }
        }


        @Override
        public ra.vh onCreateViewHolder(ViewGroup parent, int viewType) {

            ImageView iv = new ImageView(event_before_gall.this);
            iv.setLayoutParams(new LinearLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels / 3, getResources().getDisplayMetrics().widthPixels / 3));

            return new vh(iv);
        }

        @Override
        public void onBindViewHolder(final ra.vh holder, final int position) {


            ((ImageView) (holder.itemView)).setScaleType(ImageView.ScaleType.FIT_XY);
            ((ImageView) (holder.itemView)).setPadding(20,20,20,20); //ImageView.ScaleType.FIT_XY);
            ((ImageView)(holder.itemView)).setImageResource(R.drawable.loader);

            MyApplication.imageLoader.get(getResources().getString(R.string.server) + "ub/admin/images/events/" + ar.get(position), new ImageLoader.ImageListener() {


                @Override
                public void onErrorResponse(VolleyError error) {
                    ((ImageView) (holder.itemView)).setScaleType(ImageView.ScaleType.FIT_XY);
                    ((ImageView) (holder.itemView)).setPadding(20,20,20,20); //ImageView.ScaleType.FIT_XY);
                    ((ImageView)(holder.itemView)).setImageResource(R.drawable.loader);
                }

                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {

                    ((ImageView) (holder.itemView)).setScaleType(ImageView.ScaleType.FIT_XY);
                    ((ImageView) (holder.itemView)).setPadding(10, 10, 10, 10);
                    ((ImageView) (holder.itemView)).setImageBitmap(response.getBitmap());
                    ((ImageView) (holder.itemView)).setClickable(true);
                    ((ImageView) (holder.itemView)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent inu = new Intent(event_before_gall.this , event_gallery.class);
                            inu.putExtras(getIntent().getExtras());
                            inu.putExtra("pos" , position);
startActivity(inu);
                        }
                    });
                }
            });
        }

        @Override
        public int getItemCount() {
            return ar.size();
        }
    }

     ArrayList<String> ar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ar = getIntent().getStringArrayListExtra("img");





        setContentView(R.layout.activity_event_sec);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Gallery");



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        RecyclerView rview = (RecyclerView) findViewById(R.id.recView);;
        rview.setLayoutManager(new GridLayoutManager(this, 3));

rview.setAdapter(new ra());





        //




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Home) {
            finish();

            return true;
        }

        if (id == R.id.Search) {
            startActivity(new Intent(this , myapplication.prak.vrrealstate.search.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
