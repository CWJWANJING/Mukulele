package com.example.mukulele;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.q42.android.scrollingimageview.ScrollingImageView;

public class songChallenge extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ScrollingImageView scrollingBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_challenge);

        // handling the menu selection
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.ic_learning) {
                    // go to learning page
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    return true;
                }
                if (item.getItemId() == R.id.ic_petinfo) {
                    return true;
                }
                if (item.getItemId() == R.id.ic_settings) {
                    return true;
                }
                if (item.getItemId() == R.id.ic_store) {
                    return true;
                }
                return false;
            }
        });

        scrollingBackground = (ScrollingImageView) findViewById(R.id.scrolling_background);
//        scrollingBackground.stop();
//        scrollingBackground.start();


    }

}