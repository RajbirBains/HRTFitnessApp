package com.example.hrtfitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ResetActivity extends AppCompatActivity {
    private EditText editTextResetEmail;
    private FirebaseAuth mAuth;
    private Button resetButton;
    private Button returnButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        mAuth = FirebaseAuth.getInstance();
        editTextResetEmail = (EditText) findViewById(R.id.EmailToReset);

        returnButton = (Button) findViewById(R.id.returnlogin);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackToLogin();
            }
        });

        resetButton = (Button) findViewById(R.id.resetbutton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetpassword();
            }
        });
    }
    private void resetpassword (){
        String email = editTextResetEmail.getText().toString().trim();

        if (email.isEmpty()) {
            editTextResetEmail.setError("Email is required");
            editTextResetEmail.requestFocus();
            return;
        }

        //Check for valid form email
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextResetEmail.setError("Please provide a valid email");
            editTextResetEmail.requestFocus();
            return;
        }
        mAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ResetActivity.this, "Reset link sent to your email", Toast.LENGTH_SHORT).show();
                BackToLogin();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ResetActivity.this, "Failed to Reset your Password", Toast.LENGTH_SHORT).show();
            }

        });
    }
    private void BackToLogin(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}