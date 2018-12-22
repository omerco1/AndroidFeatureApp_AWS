package edu.ucsb.cs.cs184.omercohen.omercohendemosuite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static android.app.Activity.RESULT_OK;
import static edu.ucsb.cs.cs184.omercohen.omercohendemosuite.SpeechToTextFragment.RESULT_SPEECH;
import static edu.ucsb.cs.cs184.omercohen.omercohendemosuite.TextToSpeechFragment.hideSoftKeyboard;


public class ExampleFragment extends Fragment {

    WebView mWebView;
    String url;
    String input;
    EditText editTextWebView;
    ImageView imgView;
    View imageButton;
    Bitmap imageComic;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        url =  getString(R.string.exampleURL);
        View view = inflater.inflate(R.layout.example_fragment,container,false);
        //mWebView = (WebView) view.findViewById(R.id.webView);
        imgView = (ImageView) view.findViewById(R.id.imageView);

        //loadWebpage();
        if (savedInstanceState != null) {
            Bitmap bm = savedInstanceState.getParcelable("myBitMap");
            imgView.setImageBitmap(bm);
        }

        imageButton = (ImageButton) view.findViewById(R.id.imageButtonWeb);

        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editTextWebView = (EditText) getActivity().findViewById(R.id.editTextWeb);
                input =  editTextWebView.getText().toString();
                // Code here executes on main thread after user presses button
                Log.d("value is: ", input);
                Log.d(" Im here ", "im here now");
                //input = editTextWebView.;
                Integer val = Integer.valueOf(input);
                int value = val != null ? val.intValue() : -1;


                if ((value >= 1) && (value <= 2062)) {
                    //call reciever code
                } else {
                    Random rand = new Random();
                    value = rand.nextInt(2062) + 1;
                    Toast.makeText(getActivity(), "That Comic is not available.", Toast.LENGTH_LONG).show();
                }

                XkcdRetriever.XkcdBitmapResultListener p = new XkcdRetriever.XkcdBitmapResultListener() {
                    public void onImage(Bitmap imageComic) {
                        //get
                        imgView.setImageBitmap(imageComic);
                    }
                };

                XkcdRetriever recv = new XkcdRetriever();
                recv.getImage(value, p);
                //recv.getImage(value, );
            }
        });


        return  view;
    }

    public  void loadWebpage(){
        mWebView.loadUrl(url);
        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("myBitMap", imageComic);
        return;
    }


    public void webButtonClicked() {
        input = editTextWebView.toString();
    }

    public void setFragmentURL(String mURL){
        url = mURL;
    }
}
