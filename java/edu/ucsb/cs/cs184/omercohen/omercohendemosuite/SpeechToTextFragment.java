package edu.ucsb.cs.cs184.omercohen.omercohendemosuite;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

import static android.app.Activity.RESULT_OK;


public class SpeechToTextFragment extends Fragment {
    protected static final int RESULT_SPEECH = 1;
    ImageButton micButton;
    TextView speechText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.speech_to_text,container,false);

        micButton = (ImageButton) view.findViewById(R.id.speechToTextImageButton);
        speechText = (TextView) view.findViewById(R.id.speechToTextTextView);
        speechText.setText("Tap on mic to speak");

        //loadWebpage();
        if (savedInstanceState != null) {
            //Bitmap bm = savedInstanceState.getParcelable("myBitMap");
            //imgView.setImageBitmap(bm);
        }

        //Speech to text code courtesy of: https://viralpatel.net/blogs/android-speech-to-text-api/
        micButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    speechText.setText("");
                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(getActivity(),
                            "Opps! Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT);
                    t.show();
                }


            }
        });


        return  view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    speechText.setText(text.get(0));
                }
                break;
            }

        }
    }



    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putParcelable("myBitMap", imageComic);
        return;
    }


}
