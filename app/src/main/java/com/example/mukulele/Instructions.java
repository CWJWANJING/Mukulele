package com.example.mukulele;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class Instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        // The following code is based on this tutorial video:
        // https://www.youtube.com/watch?v=8Lq3HyBCuAA
        VideoView videoView = findViewById(R.id.instruction_video);
        String videopath = "android.resource://" + getPackageName() + "/" + R.raw.instruction_video;
        Uri uri = Uri.parse(videopath);
        videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }

    public void back(View view) {
//        startActivity(new Intent(this, UnityPlayerActivity.class));
        startActivity(new Intent(this, MainActivity.class));
//        startActivity(new Intent(getApplicationContext(), songChallenge.class));
    }
}