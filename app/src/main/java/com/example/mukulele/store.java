package com.example.mukulele;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class store extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        // handling the menu selection
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation_settings);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.ic_learning) {
                    // go to learning page
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    return true;
                }
                if (item.getItemId() == R.id.ic_petinfo) {
                    // go to pet info page
                    startActivity(new Intent(getApplicationContext(), petInfo.class));
                    return true;
                }
                if (item.getItemId() == R.id.ic_settings) {
                    // go to settings
                    startActivity(new Intent(getApplicationContext(), settings.class));
                    return true;
                }
                if (item.getItemId() == R.id.ic_store) {
                    // do nothing
                    return true;
                }
                return false;
            }
        });

    }
}