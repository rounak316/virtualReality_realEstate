package myapplication.prak.vrrealstate;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Prakhar on 7/12/2015.
 */
public class project_plans extends Fragment {

    ArrayList<String> lol = new ArrayList<String>();
    ArrayList<String> lol2 = new ArrayList<String>();

    int tab= 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    public project_plans()
    {

    }
    RecyclerView view;
    ViewPager vp;
    ArrayList<String> str;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


         view = new RecyclerView(getActivity());
        view.setBackgroundColor(Color.parseColor("#ffffff"));
        view.setLayoutManager(new GridLayoutManager( project_plans. this.getActivity() ,2));
        view.setAdapter(new adap());



        lol =  getArguments().getStringArrayList("project_images_img");
        lol2 =  getArguments().getStringArrayList("floor_plans_desc");
int tab = getArguments().getInt("tab");
        return view;


    }

    @Override
    public void onStart() {
        super.onStart();




    }
    class adap extends RecyclerView.Adapter<adap.Vh>
    {


        @Override
        public Vh onCreateViewHolder(ViewGroup parent, int viewType) {
            View b = getActivity().getLayoutInflater().inflate(R.layout.frag_det2  ,null);

     ImageView ivb = (ImageView) b.findViewById(R.id.iv2);

      //      ivb.setLayoutParams(new FrameLayout.LayoutParams(view.getWidth() / 2, view.getWidth() / 2));
      //      ivb.setScaleType(ImageView.ScaleType.CENTER_INSIDE);




            b.setLayoutParams(new RecyclerView.LayoutParams(view.getWidth() /2, RecyclerView.LayoutParams.WRAP_CONTENT));

            return new Vh(b);
        }

        @Override
        public void onBindViewHolder(final Vh holder, final int position) {


            holder.iv.setScaleType(ImageView.ScaleType.FIT_XY);
            holder.iv.setImageResource(R.drawable.image);
            String url=getActivity().getResources().getString(R.string.server) +"ub/admin/images/floor_plans/" + lol.get(position);
Log.d("floorplans dsad " , url);
            MyApplication.imageLoader.get(url, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {

               if(response!=null) {
                   holder.iv.setScaleType(ImageView.ScaleType.FIT_XY);
                   holder.iv.setImageBitmap(response.getBitmap());


                   holder.iv.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Intent sint = new Intent(getActivity(), fp_upd_gallery.class);


                           sint.putStringArrayListExtra("img", lol);

                           sint.putExtra("pos", position);
                           startActivity(sint);
                       }
                   });


               }
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });



String str = "";


       //     holder.a.setText("Title");//+lol.get(position));
            holder.a.setVisibility(View.GONE);

            if(lol2.get(position)!=null && !lol2.get(position).equals("null") )
            holder.b.setText(""+lol2.get(position));
else

                holder.b.setText("");
        }

        @Override
        public int getItemCount() {
            return lol.size();
        }

        class Vh extends RecyclerView.ViewHolder
        {
TextView a , b;
            ImageView iv;
            public Vh(View itemView) {
                super(itemView);

           iv = (ImageView) itemView.findViewById(R.id.iv2);
               a= (TextView) itemView.findViewById(R.id.caption2);
                b= (TextView) itemView.findViewById(R.id.project_brief2);
            }
        }

    }

}
