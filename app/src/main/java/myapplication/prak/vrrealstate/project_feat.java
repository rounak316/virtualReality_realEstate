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
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Prakhar on 7/12/2015.
 */
public class project_feat extends Fragment {
    int tab= 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public project_feat()
    {

    }


    ArrayList<String> str;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.frag_det1 , null);
        view.setBackgroundColor(Color.parseColor("#ffffff"));
        WebView webview = (WebView) view.findViewById(R.id.webview);
       // TextView project_brief = (TextView) view.findViewById(R.id.project_brief);

      //  project_brief.setVisibility(View.GONE);


       // project_brief.setText(Html.fromHtml(getArguments().getString("project_brief", "No description")));

        webview.getSettings().setUseWideViewPort(false);
        webview.loadData(getArguments().getString("project_brief" ,"No description")+ "","","");



        str = (getArguments().getStringArrayList("project_images_img"));
Log.d("Check " + tab , "" + str);



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
