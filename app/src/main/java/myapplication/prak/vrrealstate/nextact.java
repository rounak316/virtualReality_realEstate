package myapplication.prak.vrrealstate;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;

public class nextact extends AppCompatActivity {
DrawerLayout dlayout;
    ViewPager vp;
    TabLayout tablayout;
String url;
double lat =0, lon=0;
    String project_brief,project_features;
ArrayList<String> project_images_img = new ArrayList<String>();
    ArrayList<String> project_images_desc = new ArrayList<String>();
    String logo="";
    ArrayList<String> floor_plans_img = new ArrayList<String>();

    ArrayList<String> floor_plans_desc = new ArrayList<String>();
String laty="" , longy="";

    ArrayList<String> master_plans_img = new ArrayList<String>();

    ArrayList<String> master_plans_desc = new ArrayList<String>();


    ArrayList<String> tamat = new ArrayList<String>();
    ArrayList<ArrayList<String>> tmpo2 = new ArrayList<ArrayList<String>>();
    String lullu , pullu;
String title = "";
    FloatingActionButton myFAB;;
       @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_next);
NavigationView nview = (NavigationView) findViewById(R.id.nav);

nview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        if (menuItem.getItemId() == R.id.about) {

            dlayout.closeDrawer(Gravity.LEFT);

            startActivity(new Intent(nextact.this , AboutUs.class));

            return true;
        }


        if (menuItem.getItemId() == R.id.contact) {

            startActivity(new Intent(nextact.this , ContactUs.class));
            dlayout.closeDrawer(Gravity.LEFT);

            return true;
        }


        return false;
    }
});

           myFAB = (FloatingActionButton) findViewById(R.id.myFAB);



        Bundle bun =getIntent().getExtras();

       final String walky =     bun.getString("walkthrough");
         //  Toast.makeText(nextact.this , "" + walky  ,1000).show();
           if( walky!=null  && walky.length() > 6)
           {

               myFAB.setVisibility(View.VISIBLE);
               myFAB.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(walky.trim())));
                   }
               });

           }
else

           {

         //      myFAB.setVisibility(View.GONE);
           }
        title =         bun.getString("title");


        lullu =   bun.getString("lullu", "[]");

        pullu =   bun.getString("pullu", "[]");
        try {
            JSONArray jolly = new JSONArray(lullu);
ArrayList<String> tmpo   = new ArrayList<String>();

            for(int h=0;h<jolly.length();h++)
            {
                tmpo   = new ArrayList<String>();

            String jh =     String.valueOf(jolly.getJSONArray(h));

              JSONArray lol =  new JSONArray(jh);

                for(int hh=0;hh<lol.length();hh++)
                {
                      tmpo.add(String.valueOf(lol.getString(hh)));


                }
                tmpo2.add(tmpo);
            }
            Log.d("pill545u2 " , " " + lullu);




        } catch (JSONException e) {
            e.printStackTrace();
        }

        tamat = new ArrayList<>(Arrays.asList(  pullu.substring(1 , pullu.length()-1 ).split(",") ) );
        Log.d("pillu2 ", "" +   tamat);
        Log.d("pillu ", "" + tmpo2);

        project_brief=   bun.getString("project_brief", null);

        project_features =   bun.getString("project_features", null);

      logo  =   bun.getString("logo", "");

        bun.getString("city");

           Log.d("CITY VISITED", "" + bun.getString("city"));

        bun.getString("cover_image");
      Log.d("puppy lat", bun.getString("latitude"));
           Log.d("puppy long", bun.getString("longitude"));

           laty = bun.getString("latitude" , 0 + "");
           longy = bun.getString("longitude" , 0 + "");
        bun.getString("layout_plan_description");
        bun.getString("location_description");
        bun.getString("property_type");
       url = bun.getString("map_url");

        bun.getStringArrayList("project_type");






        project_images_img =   bun.getStringArrayList("project_images_img");
           project_images_desc =   bun.getStringArrayList("project_images_desc");

        bun.getStringArrayList("construction_update_img");
        bun.getStringArrayList("construction_update_desc");


        floor_plans_img =   bun.getStringArrayList("floor_plans_img");
        floor_plans_desc =  bun.getStringArrayList("floor_plans_desc");

        master_plans_img =     bun.getStringArrayList("master_layout_plan_img");
        master_plans_desc =     bun.getStringArrayList("master_layout_plan_desc");



        tablayout = (TabLayout) findViewById(R.id.tabla);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




           toolbar.setSubtitle("" + bun.getString("city"));

        dlayout = (DrawerLayout) findViewById(R.id.dlayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this ,dlayout , toolbar,R.string.open,R.string.close);
toggle.syncState();
vp= (ViewPager) findViewById(R.id.vp);
vp.setOffscreenPageLimit(10);

         nav = (ImageView) findViewById(R.id.header);
Log.d("pats",getResources().getString(R.string.server) +"ub/admin/images/cover_image/" + logo);

        MyApplication.imageLoader.get( getResources().getString(R.string.server) +"ub/admin/images/cover_image/" + logo, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                nav.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //ub/admin/images/cover_image/

        {



            dope = 0;
            {

                int position = 0;

         bun = new Bundle();
                Fragment frag ;

                frag    =          new project_brief();
                bun.putInt("tab", (position));
                bun.putString("project_brief", project_brief);

                bun.putString("title", title);


                bun.putString("project_brief", project_brief);

                bun.putString("logo", getResources().getString(R.string.server) + "ub/admin/images/cover_image/" + logo);

                bun.putStringArrayList("project_images_img", project_images_img);
                bun.putStringArrayList("project_images_desc", project_images_desc);



                frag.setArguments(bun);

                frags.add(frag);
                frags_title.add("Project Brief");



      nview.getMenu().findItem(R.id.pb).setVisible(true);

                final int crox = dope;
                nview.getMenu().findItem(R.id.pb).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        vp.setCurrentItem(crox, true);
//                        dope++;
                        dlayout.closeDrawer(Gravity.LEFT);
                        return false;
                    }
                });
                dope++;



            }


            {
                int position = 1;


          bun = new Bundle();
                Fragment frag ;

                frag =  new project_feat();
                bun.putInt("tab", (position));
                bun.putString("project_brief", project_features);
                bun.putStringArrayList("project_images_img", floor_plans_img);
                frag.setArguments(bun);

                if(project_features.length() > 10) {
                    frags.add(frag);
                    frags_title.add("Features");
                    nview.getMenu().findItem(R.id.fe).setVisible(true);

                    final int crox = dope;
                    nview.getMenu().findItem(R.id.fe).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {


                            vp.setCurrentItem(crox, true);
//                            dope++;
                            dlayout.closeDrawer(Gravity.LEFT);
                            return false;
                        }
                    });
                    dope++;
                }




            }






            {
                int position = 2;

                 bun = new Bundle();
                Fragment frag ;


                frag =          new project_plans();
                bun.putInt("tab", (position));
                bun.putStringArrayList("floor_plans_desc", floor_plans_desc);
                bun.putStringArrayList("project_images_img", floor_plans_img);
                frag.setArguments(bun);

                if(floor_plans_img.size()!=0) {
                    frags.add(frag);
                    frags_title.add("Floor Plans");
                    nview.getMenu().findItem(R.id.fp).setVisible(true);
                    final int crox = dope;
                    nview.getMenu().findItem(R.id.fp).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

//                            final int crox = dope;
                            vp.setCurrentItem(crox, true);

                            dlayout.closeDrawer(Gravity.LEFT);
                            return false;
                        }
                    });
                    dope++;
                }



            }




            {


                {

                    int position = 3;


                    bun = new Bundle();
                    Fragment frag;
                    try {
                        lat = Double.parseDouble(laty);
                        lon= Double.parseDouble(longy);
                    } catch (Exception e)
                    {
                        Log.d("kutte" , " " +e);
                        lat = 0;
                        lon = 0;
                    }
                    frag = new map_frag();

                    Bundle bdun = new Bundle();
                    bdun.putString("url", url);
                    bdun.putString("title", title);
                    bdun.putDouble("latitude", lat);

                    bdun.putDouble("longitude" , lon);
                    Log.d("kutte" , "" + lat + " " + lon);




                    frag.setArguments(bdun);

                    if(url.length() > 3) {
                        nview.getMenu().findItem(R.id.glp_new).setVisible(true);
                        frags.add(frag);
                        frags_title.add("Graphic Location Map");
                        final int crox = dope;

                        nview.getMenu().findItem(R.id.glp_new).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {

//                            final int crox = dope;
                                vp.setCurrentItem(crox, true);

                                dlayout.closeDrawer(Gravity.LEFT);
                                return false;
                            }
                        });
                        dope++;
                    }



                }

            }








            {

                int position = 4;


                 bun = new Bundle();
                Fragment frag ;



                frag =          new construction_update();
                bun.putInt("tab", (position));
                bun.putInt("fab", (position));
                bun.putStringArrayList("loop", tamat);

                for(int i=0;i<tmpo2.size() ; i++)
                {
                    bun.putStringArrayList("poop"+i, tmpo2.get(i));

                }

                Log.d("gsfgds3", tmpo2 + "");
                bun.putStringArrayList("tamat.size", floor_plans_desc);
                bun.putStringArrayList("project_images_img", floor_plans_img);
                frag.setArguments(bun);




                if(tmpo2.size()!=0) {
                    nview.getMenu().findItem(R.id.cu).setVisible(true);
                    final int crox = dope;
                    frags.add(frag);
                    frags_title.add("Construction Update");


                    nview.getMenu().findItem(R.id.cu).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

//                            final int crox = dope;
                            vp.setCurrentItem(crox, true);

                            dlayout.closeDrawer(Gravity.LEFT);
                            return false;
                        }
                    });
                    dope++;
                }

            }





            {

                int position = 5;


                bun = new Bundle();
                Fragment frag;
                try {
                    lat = Double.parseDouble(laty);
                    lon= Double.parseDouble(longy);
                } catch (Exception e)
                {
                    Log.d("kutte" , " " +e);
                    lat = 0;
                    lon = 0;
                }
                frag = new maps_location();

                Bundle bdun = new Bundle();
                bdun.putString("url", url);
                bdun.putString("title", title);
                bdun.putDouble("latitude", lat);

                bdun.putDouble("longitude", lon);
                Log.d("kutte", "" + lat + " " + lon);




                frag.setArguments(bdun);

                if(lat!=0 || lon !=0) {
                    nview.getMenu().findItem(R.id.glp).setVisible(true);
                    frags.add(frag);
                    frags_title.add("Location");
                    final int crox = dope;

                    nview.getMenu().findItem(R.id.glp).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

//                            final int crox = dope;
                            vp.setCurrentItem(crox, true);

                            dlayout.closeDrawer(Gravity.LEFT);
                            return false;
                        }
                    });
                    dope++;
                }



            }











            adapter adap = new adapter(getSupportFragmentManager());







            vp.setAdapter(adap);



            tablayout.setTabsFromPagerAdapter(adap);
            tablayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(vp));
            vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout){

                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);

                  if  (  myFAB.getVisibility()  != View.GONE)
                  {

                      if(position<=1 &&  myFAB.getVisibility() ==View.INVISIBLE  )
                      {
                          myFAB.setVisibility(View.VISIBLE);
                          ScaleAnimation anim = new ScaleAnimation(0,1,0,1, Animation.RELATIVE_TO_SELF,.5f,Animation.RELATIVE_TO_SELF,.5f);
                          anim.setInterpolator(new AnticipateOvershootInterpolator());
                          anim.setDuration(300);
                          myFAB.startAnimation(anim);

                      }
                      else if (   (!(position<=1)) &&  myFAB.getVisibility() !=View.INVISIBLE  )
                      {
                          myFAB.setVisibility(View.INVISIBLE);


                          ScaleAnimation anim = new ScaleAnimation(1,0,1,0, Animation.RELATIVE_TO_SELF,.5f,Animation.RELATIVE_TO_SELF,.5f);
                          anim.setInterpolator(new AnticipateOvershootInterpolator());
                          anim.setDuration(300);
                          myFAB.startAnimation(anim);
                      }

                  }

                }
            });
        }

    }








    ImageView nav;

    int dope = -1;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

if(id==R.id.home)
{

    finish();
    return true;
}
if(id==R.id.search)
{
    startActivity(new Intent(this , search.class));



}

        if (id == R.id.about) {

            dlayout.closeDrawer(Gravity.LEFT);

            startActivity(new Intent(nextact.this, AboutUs.class));

            return true;
        }


        if (id == R.id.contact) {

            startActivity(new Intent(nextact.this , ContactUs.class));
            dlayout.closeDrawer(Gravity.LEFT);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    ArrayList<Fragment> frags = new ArrayList<Fragment>();
    ArrayList<String> frags_title = new ArrayList<String>();




    class adapter extends FragmentStatePagerAdapter
    {

        @Override
        public CharSequence getPageTitle(int position) {

             return   frags_title.get(position);

        }

        public adapter(android.support.v4.app.FragmentManager fm) {

            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            return   frags.get(position);
        







        }

        @Override
        public int getCount() {
            return frags.size();
        }
    }

}
