package myapplication.prak.vrrealstate;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Prakhar on 7/15/2015.
 */
public class construction_update extends Fragment {
    ArrayList<Integer>  typez ;//= new ArrayList<Integer>();
    ArrayList<String>  images ;//= new ArrayList<String>();

    ArrayList<String> loop ; //= new     ArrayList<String> ();
    ArrayList<  ArrayList<String>> poop; // = new   ArrayList<  ArrayList<String>> ();


    RecyclerView recView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        typez = new ArrayList<Integer>();

        images = new ArrayList<String>();
        loop = new     ArrayList<String> ();
        poop = new   ArrayList<  ArrayList<String>> ();



Bundle bun = getArguments();
        loop =   bun.getStringArrayList("loop");

        for(int i=0;i<loop.size() ; i++)
        {
            poop.add(bun.getStringArrayList("poop" + i));

        }

        if(loop.size()==1)
        {
            if(loop.get(0).length()<1)
            {
                loop = new     ArrayList<String> ();
                poop = new   ArrayList<  ArrayList<String>> ();


            }

        }
int cnt = 0;
        for(int i=0;i<loop.size() ; i++)
        {



            typez.add(0);

            images.add("" +loop.get(i));

            images.addAll(poop.get(i));

            cnt++;

            for(int ip=0;ip<poop.get(i).size() ; ip++)
            {
                typez.add(1);


                cnt++;
            }



        }


        Log.d("tattu "," " +  loop.size()+ " " + loop +" "  + " " + poop.size() + " " + " " + poop );
        recView = new RecyclerView(getActivity());

        recView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        recView.setLayoutManager(new GridLayoutManager(getActivity() , 2   , LinearLayoutManager.VERTICAL , false));
        recView.setBackgroundColor(Color.parseColor("#ffffff"));
        recView.setAdapter(new adap());
        return recView;
    }


    @Override
    public void onStart() {
        super.onStart();

    }


    class adap extends RecyclerView.Adapter<adap.vh>
    {

        @Override
        public vh onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            switch (viewType)
            {
                case 0:
                    view = getActivity().getLayoutInflater().inflate(R.layout.frag_det22, null);

                    break;

                default:
                    view = getActivity().getLayoutInflater().inflate(R.layout.frag_det33, null);

                    break;

            }


            view.setLayoutParams(new ViewGroup.LayoutParams(recView.getWidth()/2, recView.getWidth()/2));

            return new vh(view);
        }

        @Override
        public void onBindViewHolder(final vh holder, final int position) {
            if(getItemViewType(position)==1) {


                holder.iv2.setImageResource(R.drawable.loader);

                MyApplication.imageLoader.get(getResources().getString(R.string.server)+"ub/admin/images/construction_update/"+"" + images.get(position), new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                        if(response.getBitmap()!=null)

                            holder.iv2.setImageBitmap(   response.getBitmap());

                        holder.iv2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                ScaleAnimation anim=new ScaleAnimation(1,0.9f,1,0.9f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,.5f);

                                anim.setDuration(300);




                                anim.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {



                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {

                                        cons_upd_gallery_frag frag_gal ;
                                        frag_gal = new cons_upd_gallery_frag();

                                        Bundle bundle = new Bundle();
                                        bundle.putStringArrayList("img", images);
                                        bundle.putIntegerArrayList("typ", typez);
                                        bundle.putInt("pos", position);


                                        frag_gal.setArguments(bundle);


                                        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("gallery").add(R.id.parenty, frag_gal).commit();



                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {

                                    }
                                });
                                anim.setInterpolator(new OvershootInterpolator());
                                v.startAnimation(anim);



                            }
                        });

                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        holder.iv2.setImageResource(R.drawable.loader);
                    }
                });
return;

            }


            final ScaleAnimation anim=new ScaleAnimation(1,0.9f,1,0.9f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,.5f);

            anim.setDuration(300);
            anim.setInterpolator(new OvershootInterpolator());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.itemView.startAnimation(anim);
                }
            });




            holder.tv.setText("" + images.get(position));






        }

        @Override
        public int getItemCount() {
            return typez.size();
        }

        @Override
        public int getItemViewType(int position) {
            if(typez.get(position)==0)
                return 0;

            return 1;
        }

        class vh extends RecyclerView.ViewHolder
        {
TextView tv;
            ImageView iv2;
            public vh(View itemView)
            {
                super(itemView);

                tv = (TextView) itemView.findViewById(R.id.caption2);

                 iv2 = (ImageView) itemView.findViewById(R.id.iv2);

            }
        }
    }
}
