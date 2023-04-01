package com.example.tryingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {


    private FirebaseAuth auth;
    private EditText signUpEmail,signUpPassword;
    private Button signUpButton;
    private TextView loginRedirectText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        signUpEmail = findViewById(R.id.LoginEmail);
        signUpPassword=findViewById(R.id.LoginPassword);
        signUpButton=findViewById(R.id.signUpBtn);
        loginRedirectText=findViewById(R.id.redirectToLogin);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = signUpEmail.getText().toString().trim();
                String pass = signUpPassword.getText().toString().trim();

                if(user.isEmpty()){
                    signUpEmail.setError("Email can not be empty");
                }
                if(pass.isEmpty()){
                    signUpPassword.setError("password can not be empty");
                }
                else{
                    auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUp.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp.this, LoginActivity.class));
                            } else {
                                Toast.makeText(SignUp.this, "Sign up Failed"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,LoginActivity.class));
            }
        });

    }
}