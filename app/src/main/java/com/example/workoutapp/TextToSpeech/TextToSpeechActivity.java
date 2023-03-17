package com.example.workoutapp.TextToSpeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.workoutapp.databinding.ActivityTextToSpeechBinding;

import java.util.Locale;


public class TextToSpeechActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private ActivityTextToSpeechBinding binding;
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTextToSpeechBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tts= new TextToSpeech(getApplicationContext(), this);

        binding.btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.etEnteredText.getTextSize()<=0){
                    Toast.makeText(getApplicationContext(),"Enter a text to speak", Toast.LENGTH_SHORT).show();
                }else{
                    //TODO
                    speakOut(String.valueOf(binding.etEnteredText.getText()));
                 }
            }
        });

    }

    @Override
    public void onInit(int status) {
        if(status==TextToSpeech.SUCCESS){
            int result=tts.setLanguage(Locale.ENGLISH);
            if(result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","Initialization failed");
            }
        }

    }

    private void speakOut(String text){
        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null,"");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(tts!=null){
            tts.stop();
            tts.shutdown();
        }
    }
}