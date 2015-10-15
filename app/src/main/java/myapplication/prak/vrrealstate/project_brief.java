package myapplication.prak.vrrealstate;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;

import myapplication.prak.vrrealstate.layouts.CircularImageView;

/**
 * Created by Prakhar on 7/12/2015.
 */
public class project_brief extends Fragment {
    int tab= 0;
    String title = "";
    CircularImageView logo;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tab  =   getArguments().getInt("tab", (0));
        title = getArguments().getString("title");

    }

    public project_brief()
    {

    }
    TextView caption;
    ViewPager vp;
    ArrayList<String> str;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.frag_det , null);
  vp = (ViewPager) view.findViewById(R.id.vpp);
        logo = (CircularImageView) view.findViewById(R.id.logo);
      //  TextView project_brief = (TextView) view.findViewById(R.id.project_brief);


         caption = (TextView) view.findViewById(R.id.caption);
    //    project_brief.setText(Html.fromHtml(getArguments().getString("project_brief", "No description")));
        logo.setBorderColor(Color.parseColor("#000000"));
        logo.setBorderWidth(10);
        logo.setSelectorColor(Color.parseColor("#000000"));
        logo.setSelectorStrokeColor(Color.parseColor("#ffffff"));
        logo.setSelectorStrokeWidth(10);
        caption.setText(title);
        WebView webView = (WebView) view.findViewById(R.id.webview);

        webView.loadData(getArguments().getString("project_brief", "No description") + "", "", "");

      String           logo2 = (getArguments().getString("logo"));
        Log.d("ytyr", logo2);
        MyApplication.imageLoader.get(logo2, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (response.getBitmap() != null) {
                    logo.setImageBitmap(response.getBitmap());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        str = (getArguments().getStringArrayList("project_images_img"));



Log.d("Check " + tab , "po2312po312o" + getArguments().getStringArrayList("project_images_desc"));

        vp.setOffscreenPageLimit(1);
        if( 0 == str.size())
            vp.setVisibility(View.GONE);
        vp.setAdapter(new adapt(getChildFragmentManager()));


        return view;


    }

    @Override
    public void onStart() {
        super.onStart();




    }
class adapt extends FragmentStatePagerAdapter
{

    public adapt(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {



        frag_iv_vp iv = new frag_iv_vp();
        Bundle bun = new Bundle();
        bun.putInt("tab", tab);
        tab  =   getArguments().getInt("tab" , (0));
        bun.putString("url" , str.get(position));
        iv.setArguments(bun);
      return   iv;

    }

    @Override
    public int getCount() {


        return str.size();
    }
}

}
