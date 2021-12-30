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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private Button signIn;
    private FirebaseAuth mAuth;
    private Button signUpButton;
    private Button ResetButton;
    private DatabaseReference ref;
    private boolean surveyCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        surveyCompleted = false;

        //Email input field
        editTextEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);

        //Password input field
        editTextPassword = (EditText) findViewById(R.id.editTextTextPassword);

        // Gets instance for firebase authentication (Needed to check credentials)
        mAuth = FirebaseAuth.getInstance();

        signUpButton = (Button) findViewById(R.id.btnSignUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSignUpActivity();
            }
        });


        //Sign in button
        signIn = findViewById(R.id.logInButton);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInUser();
            }
        });

        ResetButton = findViewById(R.id.ResetPassword);
        ResetButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenResetPage();
            }
        }));
    }

    //Method use for user to sign into their account
    private void signInUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //Check for email input
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        //Check for valid form email
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide a valid email");
            editTextEmail.requestFocus();
            return;
        }

        //Check for password input
        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        //Check password length
        if (password.length() < 6) {
            editTextPassword.setError("Min password length should be 6 characters");
            editTextPassword.requestFocus();
        }

        //Begin sign in user
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //When all credential input correctly
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user.isEmailVerified()){

                        // Creates a Shared Preferences file locally on the device to store whether remember me was checked or not
//                        if(rememberMe.isChecked()){ // Is checked
//                            SharedPreferences sharedPreferences = getSharedPreferences("remember me", MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putString("remember", "true"); // Store that "remember me" is true
//                            editor.apply(); // Send to local file
//                        }else if(!rememberMe.isChecked()){
//                            SharedPreferences sharedPreferences = getSharedPreferences("remember me", MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putString("remember", "false"); // Store that "remember me" is false
//                            editor.apply(); // Send to local file
//
//                        }

                        ref = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

                        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                surveyCompleted = (boolean) snapshot.child("SurveyStatus").getValue();
                                System.out.println(surveyCompleted);
                                Intent it = new Intent();
                                if(surveyCompleted){
                                    it.setClass(LoginActivity.this, NavScreen.class);
                                    System.out.println("Here");
                                }else{
                                    it.setClass(LoginActivity.this, SurveyStart.class);
                                    System.out.println("no");
                                }
                                startActivity(it);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                    else{
                        user.sendEmailVerification();
                        Toast.makeText(LoginActivity.this, "Please check your email to verify your account", Toast.LENGTH_LONG).show();
                    }
                }
                //Show error
                else{
                   Toast.makeText(LoginActivity.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void OpenSignUpActivity(){
        Intent intent1 = new Intent(this,SignUpActivity.class);
        startActivity(intent1);
    }
    public void OpenResetPage(){
        Intent intent = new Intent(this, ResetActivity.class);
        startActivity(intent);
    }

}