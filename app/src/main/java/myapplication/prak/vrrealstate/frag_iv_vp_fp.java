package myapplication.prak.vrrealstate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Prakhar on 7/14/2015.
 */
public class frag_iv_vp_fp extends Fragment {
ArrayList<String> tab =new ArrayList<String>();
    String sy = "";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sy =    getArguments().getString("url");
       // sy ="ub/admin/images/events/";
    }

    ImageView iv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.vp_gallery2 ,null);

 iv = (ImageView) view.findViewById(R.id.ivv);
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        iv.setImageResource(R.drawable.image);






        MyApplication.imageLoader.get(getActivity().getResources().getString(R.string.server) +"ub/admin/images/floor_plans/" +sy, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if(response.getBitmap()!=null) {

         //      iv.setScaleType(ImageView.ScaleType.FIT_XY);

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
