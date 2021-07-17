package com.example.mukulele;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class settingsFragement extends Fragment {

    Dialog dialog;

    FirebaseAuth fAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_settings,
                container, false);
        Button button = (Button) view.findViewById(R.id.logOut);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fAuth.signOut();
                Toast.makeText(view.getContext(), "Logged out.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(view.getContext(), login.class));
            }
        });
        return view;
    }
}
