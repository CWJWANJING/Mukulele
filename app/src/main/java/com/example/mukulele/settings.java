package com.example.mukulele;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class settings extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // handling the menu selection
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.ic_learning) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    return true;
                }
                if (item.getItemId() == R.id.ic_petinfo) {
                    // go to pet info page
                    startActivity(new Intent(getApplicationContext(), petInfo.class));
                    return true;
                }
                if (item.getItemId() == R.id.ic_settings) {
                    // do nothing
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

    public void Logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), login.class));
        finish();
    }


}