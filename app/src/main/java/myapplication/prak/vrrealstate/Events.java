package myapplication.prak.vrrealstate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

/**
 * Created by Prakhar on 7/12/2015.
 */
public class Events extends Fragment {

    RecyclerView recView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.event_frag , null);
        swipe = (SwipeRefreshLayout) view;
        recView = (RecyclerView) view.findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adap = new adapter();
        recView.setAdapter(adap);

        Parse(getActivity().getSharedPreferences("pref",1).getString( "events" , null));


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

        StringRequest req = new StringRequest(StringRequest.Method.GET, "http://jaipurmakaan.com/ub/api/events.php", new Response.Listener<String>() {
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
    ArrayList<Event_STORAGE> arrays=new ArrayList<Event_STORAGE>();
    void Parse(String response)
    {
        arrays = new ArrayList<Event_STORAGE>();
        if(response==null)
        {

            return;
        }
if(getActivity()!=null)
        getActivity().getSharedPreferences("pref",1).edit().putString("events",response).commit();


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

                arrays.add(projsd);
            }
Log.d("arrays" , arrays.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Snackbar.make(recView, "error " + e , Snackbar.LENGTH_LONG).show();
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



            View view = getActivity().getLayoutInflater().inflate(R.layout.eventtit, null);
view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            vh VH = new vh(view);
            return VH;
        }

        @Override
        public void onBindViewHolder(final vh holder, int position) {



            final Event_STORAGE str =  arrays.get(position);

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




sint.putExtra("pos",0);
                    startActivity(sint);
                }
            });
            holder.VP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent sint = new Intent(getActivity(), event_before_gall_bef.class);
                    sint.putStringArrayListExtra("img", new ArrayList<>(Arrays.asList(str.GALLERY)));
                    sint.putExtra("event_title", str.event_title);
                    sint.putExtra("event_title", str.cover_image_title);
                    sint.putExtra("cap", str.event_title);
                    sint.putExtra("cover_image", str.cover_image);
                    sint.putExtra("desc", str.event_description);


                    sint.putExtra("pos", 0);
                    startActivity(sint);
                }
            });
            holder.title.setText(str.cover_image_title);
            holder.cap.setText(str.event_title);
         //   holder.title.setText(str.event_title);
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

        @Override
        public int getItemCount() {
            return arrays.size();
        }

        class vh extends RecyclerView.ViewHolder
        {
            View VP;
            ImageView iv;
TextView cap , title,desc;
            public vh(View itemView) {
                super(itemView);
                cap = (TextView) itemView.findViewById(R.id.caption);
                title = (TextView) itemView.findViewById(R.id.title);
                desc = (TextView) itemView.findViewById(R.id.desc);
                iv = (ImageView) itemView.findViewById(R.id.iv);
                VP =itemView.findViewById(R.id.VP);
                VP.setClickable(true);
            }
        }

    }

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

}




