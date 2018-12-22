package edu.ucsb.cs.cs184.omercohen.omercohendemosuite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import static android.app.Activity.RESULT_OK;


public class VideoPlayBackFragment extends Fragment {

    Boolean check;
    FloatingActionButton fab;
    VideoView vv;
    View view;

    LayoutInflater inf;
    ViewGroup cont;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.video_play_back, container, false);

        cont = container;
        inf = inflater;

        check = false;
        vv = (VideoView) view.findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://"+ getActivity().getPackageName() +"/"+R.raw.bigbuck);
        vv.setVideoURI(uri);


        Log.d("orientation notes: ", "im in portrait mode");




        //loadWebpage();
        if (savedInstanceState != null) {
            //Bitmap bm = savedInstanceState.getParcelable("myBitMap");
            //imgView.setImageBitmap(bm);
        }

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_play);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(check==false) {
                    vv.start();

                    fab.setImageResource(R.drawable.ic_pause);
                    check = true;
                } else {
                    vv.pause();
                    fab.setImageResource(R.drawable.ic_play);
                    check = false;
                }

            }
        });


        return  view;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            view = inf.inflate(R.layout.video_play_back_land, cont, false);
            //(R.layout.video_play_back_land);
//            fab = (FloatingActionButton) view.findViewById(R.id.fab);
//            fab.setImageResource(R.drawable.ic_play);

//
            Toast.makeText(getContext(), "Landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
//            view = inf.inflate(R.layout.video_play_back, cont, false);
            //getActivity().setContentView(R.layout.video_play_back);

//            fab = (FloatingActionButton) view.findViewById(R.id.fab);
//            fab.setImageResource(R.drawable.ic_play);
            Toast.makeText(getContext(), "Portrait", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putParcelable("myBitMap", imageComic);
        return;
    }

}
