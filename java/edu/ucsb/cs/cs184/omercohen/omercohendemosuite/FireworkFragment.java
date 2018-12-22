package edu.ucsb.cs.cs184.omercohen.omercohendemosuite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
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
import android.view.SurfaceHolder;
import android.view.SurfaceView;
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


public class FireworkFragment extends Fragment {

    //keep track if surface exists or not
    boolean surfaceExists = false;
    SurfaceView sf;
    Canvas paintingCanvas;
    CanvasView cv;

    //app:layout_constraintBottom_toTopOf="@+id/web_layout"

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firework,container,false);

       // sf = (SurfaceView) view.findViewById(R.id.surfaceView);
         cv = (CanvasView) view.findViewById(R.id.canvasView);
        //sf.setOnTouchListener();

        //loadWebpage();
        if (savedInstanceState != null) {

            CanvasView reload_canv = (CanvasView) view.findViewById(R.id.canvasView);
            reload_canv.painting = savedInstanceState.getParcelable("myBitMap");
        }

        return  view;
    }




    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        CanvasView canv = (CanvasView) getActivity().findViewById(R.id.canvasView);
        outState.putParcelable("myBitMap", canv.painting);
        return;
    }


//    public boolean onTouchEvent(MotionEvent event) {
//        int r = (int)(Math.random() * 255);
//        int g = (int)(Math.random() * 255);
//        int b = (int)(Math.random() * 255);
//
//
//
//        Paint paint = new Paint();
//
//        if (event.getAction() == MotionEvent.ACTION_DOWN) { }
//
//        else if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_MOVE) { }
//
//        else if ((event.getAction() & MotionEvent.ACTION_MASK)== MotionEvent.ACTION_UP) { }
//        else if ((event.getActionMasked()) == MotionEvent.ACTION_POINTER_DOWN) { }
//
//        else if  ((event.getActionMasked()  & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_UP) { }
//
//        return true;
//    }


}
