package com.example.multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickCerdo(View view) {
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.cerdo);
        mediaPlayer.start();
    }

    public void onClickGato(View view) {
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.gato);
        mediaPlayer.start();
    }
}
