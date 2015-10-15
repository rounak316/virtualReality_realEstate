package myapplication.prak.vrrealstate;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class search extends AppCompatActivity {
    adapter adap;
    ArrayList<String> plo = new ArrayList<String>();
    ArrayList<String> plo2 =new ArrayList<String>();
    private String vr_image_str;
    ArrayList<Project_STORAGE> arrays=new ArrayList<Project_STORAGE>();
    void Parse(String response)
    {
        arrays = new ArrayList<Project_STORAGE>();
        if(response==null)
        {

            return;
        }


        try {


            JSONArray proj = new JSONArray(response);
            if(proj.length()==0)
            {

                Snackbar.make(findViewById(android.R.id.content), "No projects found", Snackbar.LENGTH_LONG);

             //   finish();

            }
            for(int i=0;i<proj.length();i++)
            {

                ArrayList<String> jnj = new ArrayList<String>();
                ArrayList<String> jnj2 = new ArrayList<String>();

                JSONObject tmp =   proj.getJSONObject(i);



                if(tmp.has("cons_upd")) {




                    Log.d("tqeul", "" + tmp.getJSONObject("cons_upd"));
                    JSONObject obn = tmp.getJSONObject("cons_upd");


                    Iterator<String> sas =obn.keys();

                    while (sas.hasNext())
                    {




                        String next= sas.next() ;

                        jnj.add(next);

                        Log.d("tqeul 44", "" + next + " "+   obn.getJSONArray(next));

                        JSONArray knm =    obn.getJSONArray(next);

                        jnj2.add(knm.toString());


                        obn.getJSONArray(next);


                    }



                }
                plo.add(jnj2.toString());
                plo2.add(jnj.toString());
                JSONArray ar =  tmp.getJSONArray("project_type");


                String[] project_type = new String[ar.length()];

                for(int ii=0;ii<ar.length();ii++)

                {

                    Log.d("as2" , "" +  ar.getString(ii));
                    project_type[ii] = ar.getString(ii);
//                    project_type_desc[ii] = ar.getJSONObject(ii).getString("desc");
                }

                ar =    tmp.getJSONArray("floor_plans");

                String[] floor_plans = new String[ar.length()];


                String[] floor_plans_img = new String[ar.length()];
                String[] floor_plans_desc = new String[ar.length()];

                for(int ii=0;ii<ar.length();ii++)

                {


                    floor_plans_img[ii] = ar.getJSONObject(ii).getString("img");
                    floor_plans_desc[ii] = ar.getJSONObject(ii).getString("desc");
                    Log.d("chec" , " " +ar.getJSONObject(ii).getString("img") );

                }
                ar =    tmp.getJSONArray("construction_update");
                String[] construction_update_img = new String[ar.length()];
                String[] construction_update_desc = new String[ar.length()];
                for(int ii=0;ii<ar.length();ii++)

                {



                    construction_update_img[ii] = ar.getJSONObject(ii).getString("img");
                    construction_update_desc[ii] = ar.getJSONObject(ii).getString("desc");
                }
                ar =     tmp.getJSONArray("master_layout_plan");



                String[] master_layout_plan_img = new String[ar.length()];
                String[] master_layout_plan_desc = new String[ar.length()];
                for(int ii=0;ii<ar.length();ii++)

                {

                    master_layout_plan_img[ii] = ar.getJSONObject(ii).getString("img");
                    master_layout_plan_desc[ii] = ar.getJSONObject(ii).getString("desc");


                }
                ar =     tmp.getJSONArray("project_images");
                String[] img_i = new String[ar.length()];
                String[] img_d = new String[ar.length()];
                for(int ii=0;ii<ar.length();ii++)

                {

                    img_i[ii] = ar.getJSONObject(ii).getString("img");
                    img_d[ii] = ar.getJSONObject(ii).getString("desc");


                }


                vr_image_str =    tmp.getString("vr_image");
                Log.d("vr_image_str" , vr_image_str);
                Project_STORAGE projsd = new Project_STORAGE(vr_image_str,  tmp.getString("project_id"),
                        tmp.getString("project_name"),
                        tmp.getString("project_title"),
                        tmp.getString("logo"),
                        tmp.getString("cover_image"),


                        tmp.getString("project_description"),
                        tmp.getString("city"),
                        tmp.getString("project_area"),

                        tmp.getString("project_price_range"),
                        tmp.getString("property_type"),
                        tmp.getString("property"),
                        tmp.getString("location_map"),
                        tmp.getString("project_features"),
                        tmp.getString("location_description"),
                        tmp.getString("layout_plan_description"),
                        tmp.getString("map_url"),
                        tmp.getString("latitude"),
                        tmp.getString("longitude"),
                        tmp.getString("eBrochure"),
                        tmp.getString("is_spotlight"),
                        tmp.getString("status"),
                        tmp.getString("DateCreated"),
                        master_layout_plan_img , master_layout_plan_desc,
                        construction_update_img,construction_update_desc,
                        floor_plans_img,floor_plans_desc,


                        project_type  ,
                        img_i , img_d,  tmp.getString("walkthrough")
                );

                arrays.add(projsd);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Snackbar.make(recView, "error " + e , Snackbar.LENGTH_LONG).show();
            Log.d("JSONException" , e.toString());
        }
        finally {
            adap.notifyDataSetChanged();
        }
    }

    Spinner spin1 , spin2, spin3;

    ArrayList<String> menu1, menu2 , menu3;
    ArrayList<String> menu1id, menu2id , menu3id;
View search;
    SwipeRefreshLayout swipe;
    class adapter extends RecyclerView.Adapter<adapter.vh>
    {
        @Override
        public vh onCreateViewHolder(ViewGroup parent, int viewType) {



            View view = getLayoutInflater().inflate(R.layout.enti_style2, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            vh VH = new vh(view);
            return VH;
        }

        @Override
        public void onBindViewHolder(final vh holder, final int position) {
            final Project_STORAGE str =  arrays.get(position);


            Bundle bun = new Bundle();
            bun.putString("project_brief", str.project_description);
            bun.putString("walkthrough",str.walky);
            bun.putString("project_features", str.project_features);

            bun.putString("logo", str.logo);
            bun.putString("map_url", str.map_url);
            bun.putString("city", str.city);
            bun.putString("cover_image", str.cover_image);
            bun.putString("latitude", str.latitude);
            bun.putString("longitude", str.longitude);
            bun.putString("layout_plan_description", str.layout_plan_description);
            bun.putString("location_description", str.location_description);
            bun.putString("property_type", str.property_type);


            bun.putStringArrayList("project_type", new ArrayList<String>(Arrays.asList(str.project_type)));



            bun.putString("lullu" ,  plo.get(position).toString());

            bun.putString("pullu" ,  plo2.get(position).toString());



            Log.d("pillu "+ position , plo.get(position).toString());
            Log.d("pillu2 "+ position , plo2.get(position).toString());
            bun.putStringArrayList("project_images_img", new ArrayList<String>( Arrays.asList(str.project_images_img)));
            bun.putStringArrayList("project_images_desc", new ArrayList<String>( Arrays.asList(str.project_images_desc)));

            bun.putStringArrayList("construction_update_img", new ArrayList<String>( Arrays.asList(str.construction_update_img)));
            bun.putStringArrayList("construction_update_desc", new ArrayList<String>( Arrays.asList(str.construction_update_desc)));


            bun.putStringArrayList("floor_plans_img", new ArrayList<String>( Arrays.asList(str.floor_plans_img)));
            Log.d("floor_plans_img 2" ,Arrays.asList(str.floor_plans_img) + " ");
            bun.putStringArrayList("floor_plans_desc", new ArrayList<String>( Arrays.asList(str.floor_plans_desc)));

            bun.putStringArrayList("master_layout_plan_img", new ArrayList<String>( Arrays.asList(str.master_layout_plan_img)));
            bun.putStringArrayList("master_layout_plan_desc", new ArrayList<String>( Arrays.asList(str.master_layout_plan_desc)));

            final Intent inty =  new Intent(search.this , nextact.class);
            inty.putExtras(bun);
            holder.VP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(inty);
                }
            });



            holder.VR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.d("govr" , str.vr_image_str + "||");
                    if(str.vr_image_str.length() < 3)
                        return;
                    final String names[] =str.vr_image_str.split(",");

                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(search.this);

                    ListView lv = (ListView) new ListView(search.this);


                    alertDialog.setView(lv);
                    alertDialog.setTitle("Choose Image");

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(search.this,android.R.layout.simple_list_item_1,names);
                    lv.setAdapter(adapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                            Intent nm = new Intent(search.this , virtual_reality.class);
                            nm.putExtra("vr_url" ,names[i] );
                            startActivity(nm);



                        }
                    });

                    alertDialog.show();





                }
            });
            holder.cap.setText  ( Html.fromHtml(str.project_name));
            holder.title.setText(Html.fromHtml( str.project_title));
            holder.desc.setText(Html.fromHtml( str.project_description));
            holder.iv.setImageResource(R.drawable.image);
            holder.iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            MyApplication.imageLoader.get(getResources().getString(R.string.server) +"/ub/admin/images/cover_image/"+str.cover_image, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    if(response.getBitmap()!=null) {

                        holder.iv.setScaleType(ImageView.ScaleType.FIT_XY);

                        holder.iv.setImageBitmap(response.getBitmap());
                    }
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });


        }

        @Override
        public int getItemCount() {
            return arrays.size();
        }

        class vh extends RecyclerView.ViewHolder
        {View VR , VP;
            ImageView iv;
            TextView cap , title,desc;
            public vh(View itemView) {
                super(itemView);
                cap = (TextView) itemView.findViewById(R.id.caption);
                title = (TextView) itemView.findViewById(R.id.title);
                desc = (TextView) itemView.findViewById(R.id.desc);
                iv = (ImageView) itemView.findViewById(R.id.iv);
                VR =  itemView.findViewById(R.id.VR);

                VP = itemView.findViewById(R.id.VP);

            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        started = false;
        swipe = (SwipeRefreshLayout)findViewById(R.id.swiper);


        recView = (RecyclerView) findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        menu1 =new ArrayList<String>();
        menu2 =new ArrayList<String>();
        menu3 =new ArrayList<String>();



        menu1id =new ArrayList<String>();
        menu2id =new ArrayList<String>();
        menu3id =new ArrayList<String>();

        menu1id.add("%25");
        menu2id.add("%25");
        menu3id.add("%25");

        menu1.add("Any Type");
        menu2.add("Any Location");
        menu3.add("Any Range");
        search = findViewById(R.id.search);
search.setVisibility(View.GONE);
        String city =MyApplication.city ;
        String property_type =MyApplication.property_type ;

        String project_price_range =MyApplication.project_price_range ;

        try {
            JSONArray ar =new JSONArray(city);

            for(int i=0;i<ar.length();i++)
            {
                menu2.add(ar.getJSONObject(i).getString("collection_name"));
                menu2id.add(ar.getJSONObject(i).getString("collection_type_id"));
            }
         ar =new JSONArray(property_type);

            for(int i=0;i<ar.length();i++)
            {

                menu1.add(ar.getJSONObject(i).getString("collection_name"));
                menu1id.add(ar.getJSONObject(i).getString("collection_type_id"));

            }
    ar =new JSONArray(project_price_range);

            for(int i=0;i<ar.length();i++)
            {
                menu3.add(ar.getJSONObject(i).getString("collection_name"));
                menu3id.add(ar.getJSONObject(i).getString("collection_type_id"));


            }




        spin1 = (Spinner) findViewById(R.id.project_type);
        spin2 = (Spinner) findViewById(R.id.loc);

        spin3 = (Spinner) findViewById(R.id.price_range);
            spin1.setSelected(false);  spin2.setSelected(false);  spin3.setSelected(false);


            spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spin1.setSelected(true);
                    spin1.setSelection(i);
                    if (started)
                        search.performClick();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spin2.setSelected(true);
                    spin2.setSelection(i);

                    if(started)
                    search.performClick();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spin3.setSelected(true);
                    spin3.setSelection(i);
                    started = true;
                    if(started)
                        search.performClick();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        spin1.setAdapter(new SpinnerAdapter() {
            @Override
            public View getDropDownView(int i, View view, ViewGroup viewGroup) {

                View row = getLayoutInflater().inflate(R.layout.tvv, null,
                        false);

                TextView tv = (TextView) row.findViewById(R.id.tv);
                tv.setText("" + menu1.get(i) );

                return row;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getCount() {
                return menu1.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {

                View row = getLayoutInflater().inflate(R.layout.tvv, null,
                        false);

          TextView tv = (TextView) row.findViewById(R.id.tv);
if(spin1.isSelected())

    tv.setText("" + menu1.get(i) );
                else

                tv.setText("Any Type");
                return row;
            }

            @Override
            public int getItemViewType(int i) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        });

        spin2.setAdapter(new SpinnerAdapter() {
            @Override
            public View getDropDownView(int i, View view, ViewGroup viewGroup) {

                View row = getLayoutInflater().inflate(R.layout.tvv, null,
                        false);

                TextView tv = (TextView) row.findViewById(R.id.tv);

                tv.setText("" + menu2.get(i) );

                return row;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getCount() {
                return menu2.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {

                View row = getLayoutInflater().inflate(R.layout.tvv, null,
                        false);

                TextView tv = (TextView) row.findViewById(R.id.tv);
                if(spin2.isSelected())

                    tv.setText("" + menu2.get(i) );
                else
                tv.setText("Any Location");
                return row;
            }

            @Override
            public int getItemViewType(int i) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        });
        spin3.setAdapter(new SpinnerAdapter() {
            @Override
            public View getDropDownView(int i, View view, ViewGroup viewGroup) {

                View row = getLayoutInflater().inflate(R.layout.tvv, null,
                        false);

                TextView tv = (TextView) row.findViewById(R.id.tv);

                tv.setText("" + menu3.get(i));
                return row;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getCount() {



                return menu3.size() ;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {

                View row = getLayoutInflater().inflate(R.layout.tvv, null,
                        false);

                TextView tv = (TextView) row.findViewById(R.id.tv);
                if(spin3.isSelected())

                    tv.setText("" + menu3.get(i) );
                else
                tv.setText("Any Range");
                return row;
            }

            @Override
            public int getItemViewType(int i) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        });
        } catch (JSONException e) {
            e.printStackTrace();
            finish();
        }







        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Log.d("selected candidates " ,menu1id.get(spin1.getSelectedItemPosition()) +  " " +menu2id.get(spin2.getSelectedItemPosition()) +  " " +menu3id.get(spin3.getSelectedItemPosition()) +  " " );

                final ProgressDialog pr =        ProgressDialog.show(search.this, "Searching", "Please wait..");
                pr.setCancelable(true);
                pr.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        pr.hide();

                        MyApplication.mRequestQueue.cancelAll("search");
                    }
                });

                int a=0 , b=0 , c=0;






                String itm1="" , itm2="" , itm3="";


    itm1 = menu1id.get(spin1.getSelectedItemPosition());
                itm2 = menu2id.get(spin2.getSelectedItemPosition());
                itm3 = menu3id.get(spin3.getSelectedItemPosition());


                Log.d("pass kia hai ", "price="+itm3+"&city="+itm2+"&property_type="+itm1);
                StringRequest rq = new StringRequest(getResources().getString(R.string.server)+ "ub/api/SEARCH.PHP?price="+itm3+"&city="+itm2+"&property_type="+itm1, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

if(!pr.isShowing())
    return;


                        pr.hide();

                        Log.d("RESPONSE BITCH", response);
                        Intent dws =       new Intent(search.this , search2.class);
                        dws. putExtra("response", response);

                       // startActivity(dws);

fetchData(response);



                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        pr.hide();

                        Log.d("RESPONSE BITCH ERROR " , error + " ");

                    }
                });

                rq.addMarker("search");
                MyApplication.mRequestQueue.add(rq);

            }
        });
        swipe = (SwipeRefreshLayout)findViewById(R.id.swiper);
swipe.setEnabled(false);
        recView = (RecyclerView) findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this));
        adap = new adapter();
        recView.setAdapter(adap);
     //   search.performClick();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search2, menu);
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
boolean started = false;
    RecyclerView recView;

    class Project_STORAGE
    {

        final String[] project_images_desc,project_images_img;
        final String vr_image_str,walky;
        String project_id, project_name,project_title,logo,cover_image,project_description,city,project_area,project_price_range,property_type,property,location_map,project_features,location_description,layout_plan_description
                ,map_url,latitude,longitude,eBrochure,is_spotlight,status,DateCreated;

        String[] master_layout_plan_img,master_layout_plan_desc , construction_update_img,construction_update_desc,floor_plans_img,floor_plans_desc,project_type; //,project_type_desc;

        Project_STORAGE(String vr_image_str, String project_id, String project_name,String project_title,String logo,String cover_image,String project_description,String city,String project_area,String project_price_range,String property_type,String property,String location_map,String project_features,String location_description,String layout_plan_description
                ,String map_url,String latitude,String longitude,String eBrochure,String is_spotlight,String status,String DateCreated,

                        String[] master_layout_plan_img ,   String[] master_layout_plan_desc ,String[]  construction_update_img,String[]  construction_update_desc, String[] floor_plans_img, String[] floor_plans_desc,String[] project_type,String[] project_images_img , String[] project_images_desc , String walky)
        {
            this.vr_image_str = vr_image_str;
            this.project_id = project_id;
            this.project_name =project_name;
            this.project_title=project_title;
            this.logo=logo;
            this.walky=walky;

            this.cover_image=cover_image;

            this.project_description =project_description;
            this.   city =city;
            this.project_area =project_area;
            this.   project_price_range =project_price_range;
            this.property_type =property_type;
            this.   property =property;
            this.location_map =location_map;
            this.   project_features =project_features;
            this.location_description =location_description;
            this.   layout_plan_description =layout_plan_description;
            this.   map_url =map_url;
            this.           latitude =latitude;
            this.longitude =longitude;
            this.   eBrochure= eBrochure;
            this.is_spotlight =is_spotlight;
            this.   status =status;
            this.DateCreated =DateCreated;



            Log.d("floor_plans_img aya" ,""  +new ArrayList<String>(Arrays.asList(floor_plans_img))  );


            this.  master_layout_plan_img =master_layout_plan_img;
            this.master_layout_plan_desc =master_layout_plan_desc;
            this.construction_update_img= construction_update_img;
            this.construction_update_desc= construction_update_desc;
            this.floor_plans_img =floor_plans_img;
            this.floor_plans_desc =floor_plans_desc;
            this.project_type =project_type;

            this. project_images_desc =   project_images_desc;
            this. project_images_img  = project_images_img;


        }

    }

    void fetchData(String rewponse)
    {


        Parse(rewponse);


//        StringRequest req = new StringRequest(StringRequest.Method.GET, "http://jaipurmakaan.com/ub/api/projects.php", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                swipe.setRefreshing(false);
//                Parse(response);
//                Log.d("response", response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Snackbar.make(recView, "Error updating content " + error, Snackbar.LENGTH_LONG).show();
//                swipe.setRefreshing(false);
//            }
//        });
//
//        MyApplication.mRequestQueue.add(req);

    }

}
