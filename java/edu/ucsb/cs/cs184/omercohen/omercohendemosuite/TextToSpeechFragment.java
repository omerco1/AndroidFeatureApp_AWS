package edu.ucsb.cs.cs184.omercohen.omercohendemosuite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import static android.app.Activity.RESULT_OK;


public class TextToSpeechFragment extends Fragment {

    EditText inputText;
    Button speakButton;
    TextToSpeech tts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.text_to_speech,container,false);

        inputText = (EditText) view.findViewById(R.id.editTextToSpeech);
        speakButton = (Button) view.findViewById(R.id.textToSpeechButton);


        Log.d("value is: ", getString(R.string.defaultEditText));
        inputText.setText(getString(R.string.defaultEditText));
        Log.d("NOTe: ", "testing here 22");

        //loadWebpage();
        if (savedInstanceState != null) {
            //Bitmap bm = savedInstanceState.getParcelable("myBitMap");
            //imgView.setImageBitmap(bm);
        }

        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
                    return false;
                }
            });
        }



        speakButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                //Courtesy of: https://www.tutorialspoint.com/android/android_text_to_speech.html
                 tts = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR) {
                            Log.d("tts status: ", "good to go!");
                            tts.setLanguage(Locale.US);
                            //tts.setLanguage(Locale.US);
                            tts.speak(inputText.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                });




            }
        });




        return  view;
    }





    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putParcelable("myBitMap", imageComic);
        return;
    }

    //Hides keyboard when input complete
    //courtesy of: https://stackoverflow.com/questions/4165414/how-to-hide-soft-keyboard-on-android-after-clicking-outside-edittext
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }


}
