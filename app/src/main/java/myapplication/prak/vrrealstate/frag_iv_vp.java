package myapplication.prak.vrrealstate;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import myapplication.prak.vrrealstate.MyApplication;
import myapplication.prak.vrrealstate.R;

/**
 * Created by Prakhar on 7/14/2015.
 */
public class frag_iv_vp extends Fragment {
int tab = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tab =    getArguments().getInt("tab"  ,0);
    }

    ImageView iv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.vp_gallery ,null);

 iv = (ImageView) view.findViewById(R.id.ivv);
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        iv.setImageResource(R.drawable.image);


        String sy = "";
       switch (tab)
       {
           case 0:
               sy = "ub/admin/images/project_images/";
               break;

           case 1:
               sy = "ub/admin/images/floor_plans/";
               break;

           case 2:
               sy = "ub/admin/images/floor_plans/";
               break;

           case 3:
               sy = "ub/admin/images/location_map/";
               break;

           case 4:
               sy = "ub/admin/images/master_layout_plan/";
               break;

           case 5:
               sy = "ub/admin/images/floor_plans/";
               break;

        default:
               sy = "ub/admin/images/floor_plans/";
               break;

       }
Log.d("TAB " + tab , ""  +getActivity().getResources().getString(R.string.server) +sy+getArguments().getString("url"));

        MyApplication.imageLoader.get(getActivity().getResources().getString(R.string.server) +sy+getArguments().getString("url"), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if(response.getBitmap()!=null) {

                    iv.setScaleType(ImageView.ScaleType.FIT_XY);

                    iv.setImageBitmap(response.getBitmap());
                }

            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
    //    iv.setImageResource(R.drawable.images);
    }
}
