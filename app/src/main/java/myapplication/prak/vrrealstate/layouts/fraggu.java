package myapplication.prak.vrrealstate.layouts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import myapplication.prak.vrrealstate.MyApplication;
import myapplication.prak.vrrealstate.R;

/**
 * Created by Prakhar on 7/16/2015.
 */
public class fraggu extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



     View view = inflater.inflate(R.layout.tamtar , null);
  final ImageView iv = (ImageView) view.findViewById(R.id.iv); // iv
        TextView tv = (TextView) view.findViewById(R.id.title);



        tv.setText("" + getArguments().getString("title",""));

      String url =   getArguments().getString("url","");


        url = getActivity().getResources().getString(R.string.server) +"ub/admin/images/banner_image/" + url;

        MyApplication.imageLoader.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {

                if(response.getBitmap()!=null)

                iv.setImageBitmap(response.getBitmap());

            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } );

        return view;
    }
}
