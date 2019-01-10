package com.example.fadiabrehman.fbapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    ProgressBar progressBar;
    EditText email;
    EditText password;
    Button signUp;
    Button login;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar2);
        progressBar = findViewById(R.id.progressBar);
        email = findViewById(R.id.etEmail);
       password = findViewById(R.id.etPassword);
        signUp = findViewById(R.id.btnSignup);
        login = findViewById(R.id.btnLogin);

        toolbar.setTitle(R.string.app_name);



        firebaseAuth = FirebaseAuth.getInstance();
        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
           firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                   .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {

                                Toast.makeText(MainActivity.this,  "Registered Successfully",
                                        Toast.LENGTH_LONG).show();
                                email.setText("");
                                password.setText("");
                           }
                           else {
                                Toast.makeText(MainActivity.this,  task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                       }


                   });

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
    }
}
