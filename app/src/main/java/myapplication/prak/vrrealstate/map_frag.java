package myapplication.prak.vrrealstate;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import myapplication.prak.vrrealstate.layouts.touchiv;

/**
 * Created by Prakhar on 7/14/2015.
 */
public class map_frag extends Fragment {
int lat = 0  , lon = 0;
    String url = "";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url =getArguments().getString("url"  ,"");
        lat =    getArguments().getInt("long"  ,0);
        lon =    getArguments().getInt("lat"  ,0);
    }

    ImageView iv;
void imageLoad()
{

    MyApplication.imageLoader.get(""+url, new ImageLoader.ImageListener() {
        @Override
        public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {


            ib.setImageBitmap(response.getBitmap());
//            yourId.setVisibility(View.GONE);
            Log.d("RESPONSE 2312", response + " ");
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d("RESPONSE 2312" , error + " ");
//            yourId.setVisibility(View.VISIBLE);
            ib.setImageResource(R.drawable.loader);
            imageLoad();

        }
    });

}


//ProgressBar yourId;
    touchiv ib;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

      View  view = inflater.inflate(R.layout.glp , null);

        ib = (touchiv) view.findViewById(R.id.iv);
//        yourId = (ProgressBar) view.findViewById(R.id.yourId);
        ib.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageLoad();


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
