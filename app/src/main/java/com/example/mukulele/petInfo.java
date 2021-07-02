package com.example.mukulele;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class petInfo extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);

        // handling the menu selection
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation_settings);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.ic_petinfo) {
                    // do nothing
                    return true;
                }
                if (item.getItemId() == R.id.ic_learning) {
                    // go to learning page
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    return true;
                }
                if (item.getItemId() == R.id.ic_settings) {
                    // go to settings
                    startActivity(new Intent(getApplicationContext(), settings.class));
                    return true;
                }
                if (item.getItemId() == R.id.ic_store) {
                    // go to store page
                    startActivity(new Intent(getApplicationContext(), store.class));
                    return true;
                }
                return false;
            }
        });

    }
}