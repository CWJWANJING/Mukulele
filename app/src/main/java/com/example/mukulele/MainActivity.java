package com.example.mukulele;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // handling the menu selection
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.ic_learning) {
                    // do nothing
                    return true;
                }
                if (item.getItemId() == R.id.ic_petinfo) {
                    // go to pet info page
                    startActivity(new Intent(getApplicationContext(), petInfo.class));
                    return true;
                }
                if (item.getItemId() == R.id.ic_settings) {
                    // go to settings page
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
////        getMenuInflater().inflate(R.menu.menu_main, menu);
//        Log.i("onCreate", "menu");
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        String msg = "";
//        // show people the score, extracts from Firebase
//        switch (item.getItemId()){
//            case R.id.ic_coins:
//                msg = "number of coins";
//                break;
//            case R.id.ic_pet:
//                msg = "cat's experience points";
//                break;
//        }
//        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
//        return super.onOptionsItemSelected(item);
//    }

    public void Logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), login.class));
        finish();
    }

    public void SongChallenge1(View view) {
        startActivity(new Intent(getApplicationContext(), songChallenge.class));
    }

}