package myapplication.prak.vrrealstate;

import android.app.Instrumentation;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.test.UiThreadTest;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {
DrawerLayout dlayout;
    TabLayout tablayout;
    NavigationView navigationView;

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;

    }

    @Override
    public void onBackPressed() {
        AlertDialog diaBox = AskOption();
        diaBox.show();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = (NavigationView) findViewById(R.id.navview);
        final ViewPager vp= (ViewPager) findViewById(R.id.vp);

        tablayout = (TabLayout) findViewById(R.id.tabla);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dlayout = (DrawerLayout) findViewById(R.id.dlayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this ,dlayout , toolbar,R.string.open,R.string.close);
toggle.syncState();

vp.setOffscreenPageLimit(3);
        adapter adap = new adapter(getSupportFragmentManager());
        vp.setAdapter(adap);

        tablayout.setTabsFromPagerAdapter(adap);
        tablayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(vp));
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        try {
            testTap();
            Log.d("tap", "occured");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            Log.d("tap", "catched " + throwable.getMessage());
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id =  menuItem.getItemId();
                if (id == R.id.spotlight) {

                    vp.setCurrentItem(0,true);


                    dlayout.closeDrawer(Gravity.LEFT);


                    return true;
                }


                if (id == R.id.projects) {


                    vp.setCurrentItem(1,true);
                    dlayout.closeDrawer(Gravity.LEFT);

                    return true;
                }


                if (id == R.id.events) {


                    vp.setCurrentItem(2,true);
                    dlayout.closeDrawer(Gravity.LEFT);


                    return true;
                }
                if (id == R.id.about) {

                    dlayout.closeDrawer(Gravity.LEFT);

                    startActivity(new Intent(MainActivity.this , AboutUs.class));

                    return true;
                }


                if (id == R.id.contact) {

                    startActivity(new Intent(MainActivity.this , ContactUs.class));
                    dlayout.closeDrawer(Gravity.LEFT);

                    return true;
                }
                menuItem.setChecked(true);
                return true;
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

            startActivity(new Intent(this , search.class));

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    class adapter extends android.support.v4.app.FragmentPagerAdapter
    {

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Spotlight";
                case 1:

                    return "Projects";
                case 2:

                    return "Events & News";
                default:
                    return "Spotlight";
            }
        }

        public adapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            switch (position)
            {

                case 0:
                    return new SpotLight();
                case 1:
                    return new Project();

                case 2:

                    return new Events();
                default:
                    return new Project();
            }


        }

        @Override
        public int getCount() {
            return 3;
        }
    }
    @UiThreadTest
    public void testTap() throws Throwable
    {
        //Required by MotionEvent.obtain according to JavaDocs
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis();

        Instrumentation i = new Instrumentation();

        //Setup the info needed for our down and up events to create a tap
        MotionEvent downEvent = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, 300, 20, 0);
        MotionEvent upEvent = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 300, 20, 0);

        //Send the down/up tap event
        i.sendPointerSync(downEvent);
        i.sendPointerSync(upEvent);

        //Delay to see the results
        Thread.currentThread().sleep(3000);
    }


}
