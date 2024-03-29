package com.example.mukulele;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmail = findViewById(R.id.registerEmail);
        mPassword = findViewById(R.id.registerPassword);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.textView);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        // if user already logged in, directly go to main activity
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        // account information validation
        mRegisterBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required.");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password must have >= 6 characters.");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // register the user into firebase
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(register.this, "User Created.", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                }else{
                                    Toast.makeText(register.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE); // once error shown, progressbar disappear
                                }
                            }
                        }
                );
            }
        });

        mLoginBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), login.class));
            }
        });

    }
}