package myapplication.prak.vrrealstate.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;

import com.google.vrtoolkit.cardboard.CardboardActivity;

import org.rajawali3d.cardboard.RajawaliCardboardRenderer;
import org.rajawali3d.cardboard.RajawaliCardboardView;

import java.util.Locale;

import myapplication.prak.vrrealstate.virtual_reality;


public class MainActivity extends CardboardActivity {
    TextToSpeech   ttob;
    RajawaliCardboardRenderer renderer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
final int count =   getIntent().getIntExtra("cnt", 0);
        final RajawaliCardboardView view = new RajawaliCardboardView(this);


        setContentView(view);
        setCardboardView(view);



          renderer = new MyRenderer(this);

        view.setRenderer(renderer);
        view.setSurfaceRenderer(renderer);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLOCL", "CLICLITY");


                Intent inty= new Intent(MainActivity.this , virtual_reality.class);
                inty.putExtra("pos", count + 1);
                inty.putExtra("walky", getIntent().getStringExtra("walky"));

                inty.putStringArrayListExtra("vr_url", getIntent().getStringArrayListExtra("vr_url"));
//                renderer.changeImage();
                finish();
                startActivity(inty );

            }
        });


        ttob=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            @SuppressWarnings("deprecation")
            public void onInit(int status) {
                ttob.setLanguage(Locale.UK);

                ttob.speak("" + getIntent().getStringExtra("DICY"), TextToSpeech.QUEUE_FLUSH, null);

            }
        }
        );

    }

    @Override
    protected void onStop() {
        super.onStop();
        ttob.stop();
        ttob.shutdown();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ttob.stop();
        ttob.shutdown();
    }
}
