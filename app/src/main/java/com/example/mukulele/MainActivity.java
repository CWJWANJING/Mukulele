package com.example.mukulele;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.unity3d.player.UnityPlayerActivity;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // handling the menu selection
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new mainFragement()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.ic_pet:
                            selectedFragment = new petFragement();
                            Log.d("pet selected", "pet");
                            break;
                        case R.id.ic_settings:
                            selectedFragment = new settingsFragement();
                            Log.d("settings selected", "settings");
                            break;
                        case R.id.ic_learning:
                            selectedFragment = new mainFragement();
                            Log.d("learning selected", "learning");
                            break;
                        case R.id.ic_store:
                            selectedFragment = new storeFragement();
                            Log.d("store selected", "store");
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                            selectedFragment).commit();
                    return true;
                }
            };

    public void SongChallenge1(View view) {
//        startActivity(new Intent(this, UnityPlayerActivity.class));
        startActivity(new Intent(this, unityActivity.class));
//        startActivity(new Intent(getApplicationContext(), songChallenge.class));
    }
}