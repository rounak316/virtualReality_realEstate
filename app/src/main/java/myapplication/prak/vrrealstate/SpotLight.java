package myapplication.prak.vrrealstate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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

/**
 * Created by Prakhar on 7/12/2015.
 */
public class SpotLight extends Fragment {
    class Event_STORAGE
    {

        String event_id, event_title,event_date,cover_image,cover_image_title,event_description,is_spotlight,status,DateCreated;//,property_type,property,location_map,project_features,location_description,layout_plan_description


        String[] GALLERY;

        Event_STORAGE( String event_id,String event_title, String event_date,String cover_image,String cover_image_title,String event_description,String is_spotlight,String status,String DateCreated,String[] GALLERY)
        {
            this.  event_id=event_id;
            this. event_title=event_title;
            this.event_date=event_date;
            this.cover_image=cover_image;
            this.cover_image_title=cover_image_title;
            this.event_description=event_description;

            this.is_spotlight=is_spotlight;
            this.status=status;
            this.DateCreated=DateCreated;
            this.GALLERY=GALLERY;

        }

    }
    RecyclerView recView;
    private String vr_image_str;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.event_frag , null);
        swipe = (SwipeRefreshLayout) view;
        recView = (RecyclerView) view.findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(getActivity()));









        adap = new adapter();
        recView.setAdapter(adap);

        Parse(getActivity().getSharedPreferences("pref",1).getString( "spotlight" , null));


        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData();

            }
        });
        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(true);
                fetchData();
            }
        });

        return view;
    }


    void fetchData()
    {

        StringRequest req = new StringRequest(StringRequest.Method.GET,getResources().getString(R.string.server)+ "ub/api/index.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                swipe.setRefreshing(false);
                Parse(response);

Log.d("response", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(recView,"Error updating content " + error,Snackbar.LENGTH_LONG).show();
swipe.setRefreshing(false);
            }
        });

        MyApplication.mRequestQueue.add(req);

    }
    adapter adap;
    @Override
    public void onStart() {
        super.onStart();



//        ProgressDialog.show(getActivity(), "Loading Content", "Please wait for a moment..", true, true, new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialogInterface) {
//                Snackbar.make(recView,"Cancelled",Snackbar.LENGTH_LONG).show();
//            }
//        });



    }


    ArrayList<String> plo = new ArrayList<String>();
    ArrayList<String> plo2 =new ArrayList<String>();



    ArrayList<Event_STORAGE> _arrays=new ArrayList<Event_STORAGE>();
    void Parse2(String response)
    {


        try {


            JSONArray proj = new JSONArray(response);

            for(int i=0;i<proj.length();i++)
            {
                JSONObject tmp =   proj.getJSONObject(i);


                JSONArray ar;
                ar =    tmp.getJSONArray("GALLERY");

                String[] GALLERY = new String[ar.length()];

                for(int ii=0;ii<ar.length();ii++)

                {
                    GALLERY[ii] = ar.getString(ii);
                }


                //   event_id, event_title,event_date,cover_image,cover_image_title,event_description,is_spotlight,status,DateCreated;//,property_type,property,location_map,project_features,location_description,layout_plan_description


                Event_STORAGE projsd = new Event_STORAGE(  tmp.getString("event_id"),
                        tmp.getString("event_title"),
                        tmp.getString("event_date"),
                        tmp.getString("cover_image"),
                        tmp.getString("cover_image_title"),


                        tmp.getString("event_description"),
                        tmp.getString("is_spotlight"),
                        tmp.getString("status"),

                        tmp.getString("DateCreated"),GALLERY

                );

                _arrays.add(projsd);
            }
            Log.d("arrays" , _arrays.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Snackbar.make(recView, "error " + e , Snackbar.LENGTH_LONG).show();
        }
        finally {
            adap.notifyDataSetChanged();
        }
    }
    void Parse(String respone)
    {
        arrays = new ArrayList<Project_STORAGE>();
        if(respone==null)
        {

            return;
        }
        _arrays = new ArrayList<Event_STORAGE>();


        if(getActivity()!=null)
            getActivity().getSharedPreferences("pref",1).edit().putString("spotlight",respone).commit();


        try {
            JSONObject ar = new JSONObject(respone);
String res1 =  ar.getJSONArray("proj").toString();
           String res2 =        ar.getJSONArray("event").toString();

            Log.d("dasdasdas" , res1 + " " );
            Parse1(res1);
           Parse2(res2);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("dasdasdas" , e.getCause() + " " );
        }





    }

    ArrayList<Project_STORAGE> arrays=new ArrayList<Project_STORAGE>();
    void Parse1(String response)
    {


        try {


            JSONArray proj = new JSONArray(response);

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

                   if(    ar.getJSONObject(ii).getString("desc") ==null)
                       floor_plans_desc[ii] ="";
                   else

                    floor_plans_desc[ii] = ar.getJSONObject(ii).getString("desc");
                    Log.d("chec" , " " +ar.getJSONObject(ii).getString("img") );

                }
                        ar =    tmp.getJSONArray("construction_update");
                String[] construction_update_img = new String[ar.length()];
                String[] construction_update_desc = new String[ar.length()];
                for(int ii=0;ii<ar.length();ii++)

                {



                    construction_update_img[ii] = ar.getJSONObject(ii).getString("img");

                    if(  ar.getJSONObject(ii).getString("desc")==null )
                        construction_update_desc[ii] = "";
                        else


                    construction_update_desc[ii] = ar.getJSONObject(ii).getString("desc");
                }
                        ar =     tmp.getJSONArray("master_layout_plan");



                String[] master_layout_plan_img = new String[ar.length()];
                String[] master_layout_plan_desc = new String[ar.length()];
                for(int ii=0;ii<ar.length();ii++)

                {

                    master_layout_plan_img[ii] = ar.getJSONObject(ii).getString("img");

                    if(  ar.getJSONObject(ii).getString("desc") ==null)
                        master_layout_plan_desc[ii] =null;
                    else
                                master_layout_plan_desc[ii] = ar.getJSONObject(ii).getString("desc");


                }
                ar =     tmp.getJSONArray("project_images");
                String[] img_i = new String[ar.length()];
                String[] img_d = new String[ar.length()];
                for(int ii=0;ii<ar.length();ii++)

                {

                    img_i[ii] = ar.getJSONObject(ii).getString("img");

                    if(ar.getJSONObject(ii).getString("desc")==null)
                        img_d[ii] ="";
                    else
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
                        img_i , img_d
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


    SwipeRefreshLayout swipe;
    class adapter extends RecyclerView.Adapter<adapter.vh>
    {
        @Override
        public vh onCreateViewHolder(ViewGroup parent, int viewType) {


if(viewType==0) {
    View view = getActivity().getLayoutInflater().inflate(R.layout.enti, null);
    view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    vh VH = new vh(view);
    return VH;
}

          else {
                View view = getActivity().getLayoutInflater().inflate(R.layout.eventtit, null);
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                vh VH = new vh(view);
                return VH;
            }


        }

        @Override
        public int getItemViewType(int position) {

            if(position<arrays.size())
                return 0;


            return 1;
        }

        @Override
        public void onBindViewHolder(final vh holder, final int position) {
if(getItemViewType(position) == 0)
            {
                holder.VR.setVisibility(View.VISIBLE);
            final Project_STORAGE str = arrays.get(position);


            Bundle bun = new Bundle();
            bun.putString("project_brief", str.project_description);

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


            bun.putString("lullu", plo.get(position).toString());

            bun.putString("pullu", plo2.get(position).toString());


            Log.d("pillu " + position, plo.get(position).toString());
            Log.d("pillu2 " + position, plo2.get(position).toString());
            bun.putStringArrayList("project_images_img", new ArrayList<String>(Arrays.asList(str.project_images_img)));
            bun.putStringArrayList("project_images_desc", new ArrayList<String>(Arrays.asList(str.project_images_desc)));

            bun.putStringArrayList("construction_update_img", new ArrayList<String>(Arrays.asList(str.construction_update_img)));
            bun.putStringArrayList("construction_update_desc", new ArrayList<String>(Arrays.asList(str.construction_update_desc)));


            bun.putStringArrayList("floor_plans_img", new ArrayList<String>(Arrays.asList(str.floor_plans_img)));
            Log.d("floor_plans_img 2", Arrays.asList(str.floor_plans_img) + " ");
            bun.putStringArrayList("floor_plans_desc", new ArrayList<String>(Arrays.asList(str.floor_plans_desc)));

            bun.putStringArrayList("master_layout_plan_img", new ArrayList<String>(Arrays.asList(str.master_layout_plan_img)));
            bun.putStringArrayList("master_layout_plan_desc", new ArrayList<String>(Arrays.asList(str.master_layout_plan_desc)));
bun.putString("title" , (str.project_title));
            final Intent inty = new Intent(getActivity(), nextact.class);
            inty.putExtras(bun);
holder.itemView.setClickable(true);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(inty);
                    }
                });
            holder.VP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(inty);
                }
            });

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(inty);
                    }
                });

            holder.VR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.d("govr", str.vr_image_str + "||");
                    if (str.vr_image_str.length() < 3)
                        return;
                    final String names[] = str.vr_image_str.split(",");

                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(SpotLight.this.getActivity());

                    ListView lv = (ListView) new ListView(SpotLight.this.getActivity());


                    alertDialog.setView(lv);
                    alertDialog.setTitle("Choose Image");

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(SpotLight.this.getActivity(), android.R.layout.simple_list_item_1, names);
                    lv.setAdapter(adapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                            Intent nm = new Intent(SpotLight.this.getActivity(), virtual_reality.class);
                            nm.putExtra("vr_url", names[i]);
                            startActivity(nm);


                        }
                    });

                    alertDialog.show();


                }
            });
            holder.cap.setText(Html.fromHtml(str.project_name));
            holder.title.setText(Html.fromHtml(str.project_title));
            holder.desc.setText(Html.fromHtml(str.project_description));
            holder.iv.setImageResource(R.drawable.image);
            holder.iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            MyApplication.imageLoader.get(getActivity().getResources().getString(R.string.server) + "/ub/admin/images/cover_image/" + str.cover_image, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    if (response.getBitmap() != null) {

                        holder.iv.setScaleType(ImageView.ScaleType.FIT_XY);

                        holder.iv.setImageBitmap(response.getBitmap());
                    }
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });


        }
            else
{

    holder.VR.setVisibility(View.GONE);

int position2 = position - arrays.size() ;
    final Event_STORAGE str =  _arrays.get(position2);

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent sint = new Intent(getActivity() , event_before_gall_bef.class);
            sint.putStringArrayListExtra("img", new ArrayList<>(Arrays.asList(str.GALLERY)));
            sint.putExtra("event_title", str.event_title);
            sint.putExtra("event_title",str.cover_image_title);
            sint.putExtra("cap",str.event_title);
            sint.putExtra("cover_image",str.cover_image);
            sint.putExtra("desc",str.event_description);




            sint.putExtra("pos", 0);
            startActivity(sint);
        }
    });

    holder.VP.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent sint = new Intent(getActivity() , event_before_gall_bef.class);
            sint.putStringArrayListExtra("img", new ArrayList<>(Arrays.asList(str.GALLERY)));
            sint.putExtra("event_title", str.event_title);
            sint.putExtra("event_title",str.cover_image_title);
            sint.putExtra("cap",str.event_title);
            sint.putExtra("cover_image",str.cover_image);
            sint.putExtra("desc",str.event_description);




            sint.putExtra("pos", 0);
            startActivity(sint);
        }
    });

    holder.cap.setText(str.event_title);
    holder.title.setText(str.event_title);
    holder.desc.setText(str.event_description);
    holder.iv.setImageResource(R.drawable.image);
    holder.iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
    MyApplication.imageLoader.get( getActivity().getResources().getString(R.string.server) +"ub/admin/images/event_cover_image/"+str.cover_image, new ImageLoader.ImageListener() {
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
        }

        @Override
        public int getItemCount() {
            return arrays.size()+_arrays.size();
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

    class Project_STORAGE
    {

         final String[] project_images_desc,project_images_img;
         final String vr_image_str;
        String project_id, project_name,project_title,logo,cover_image,project_description,city,project_area,project_price_range,property_type,property,location_map,project_features,location_description,layout_plan_description
                ,map_url,latitude,longitude,eBrochure,is_spotlight,status,DateCreated;

        String[] master_layout_plan_img,master_layout_plan_desc , construction_update_img,construction_update_desc,floor_plans_img,floor_plans_desc,project_type; //,project_type_desc;

Project_STORAGE(String vr_image_str, String project_id, String project_name,String project_title,String logo,String cover_image,String project_description,String city,String project_area,String project_price_range,String property_type,String property,String location_map,String project_features,String location_description,String layout_plan_description
        ,String map_url,String latitude,String longitude,String eBrochure,String is_spotlight,String status,String DateCreated,

                         String[] master_layout_plan_img ,   String[] master_layout_plan_desc ,String[]  construction_update_img,String[]  construction_update_desc, String[] floor_plans_img, String[] floor_plans_desc,String[] project_type,String[] project_images_img , String[] project_images_desc)
{
this.vr_image_str = vr_image_str;
   this.project_id = project_id;
    this.project_name =project_name;
    this.project_title=project_title;
    this.logo=logo;

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

}




