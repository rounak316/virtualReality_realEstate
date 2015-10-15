package myapplication.prak.vrrealstate;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class cons_upd_gallery_frag extends Fragment {
RecyclerView gallery;


    View myfab ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


    Bundle bundle =    getArguments();

  View view = inflater.inflate(R.layout.activity_event_gallery, null);

        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
myfab =  view.findViewById(R.id.myFAB);


        myfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();


            }
        });




           view.setLayoutParams(param);

        final ArrayList<String> ar2 = bundle.getStringArrayList("img");
        final ArrayList<String> ar = new ArrayList<>();
        int POS =bundle.getInt("pos", 0);
        final ArrayList<Integer> typ = bundle.getIntegerArrayList("typ");

        int i = -1;
        for(String o : ar2)
        { i++;




            if(typ.get(i) == 00) {
                if(POS>=i)
                {

                    POS--;

                }
                continue;
            }
            ar.add(o);

        }


         vp = (ViewPager) view.findViewById(R.id.vp);
//
//        vp.setPageMargin(-100);
//        vp.setHorizontalFadingEdgeEnabled(true);
//        vp.setFadingEdgeLength(30);

        vp.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                if (typ.get(position) == 00) {


                }

                frag_iv_vp_cons fra = new frag_iv_vp_cons();
                Bundle bun = new Bundle();
                bun.putString("url", ar.get(position));
                Log.d("EVENT GA", "" + ar);
                fra.setArguments(bun);

                return fra;
            }

            @Override
            public int getCount() {
                return ar.size();
            }
        });



        vp.setCurrentItem(POS);

        return view;



    }

    @Override
    public void onStart() {
        super.onStart();

        ScaleAnimation sc = new ScaleAnimation(0,1,0,1, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,.5f);
        sc.setInterpolator(new AnticipateOvershootInterpolator());
        sc.setDuration(300);
        myfab.startAnimation(sc);
        vp.startAnimation(AnimationUtils.loadAnimation(getActivity() , R.anim.abc_slide_in_bottom));

    }

    ViewPager vp;
    @Override
    public void onStop() {
        super.onStop();
        if(vp!=null)
        {if(getActivity()!=null)
        {
            vp.startAnimation(AnimationUtils.loadAnimation(getActivity() , R.anim.abc_slide_out_bottom));


        }


        }

    }
}
