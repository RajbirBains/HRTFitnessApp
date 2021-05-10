package com.example.hrtfitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText editTextEmail, editTextPassword, editTextUserName, editTextConfirmPassword;
    private Button signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Email input field
        editTextEmail = (EditText) findViewById(R.id.EmailAddress);

        //Password input field
        editTextPassword = (EditText) findViewById(R.id.Password);

        //Username input field
        editTextUserName = (EditText) findViewById(R.id.PersonName);

        editTextConfirmPassword = (EditText) findViewById(R.id.ConfirmPassword);
        mAuth = FirebaseAuth.getInstance();
        signUpButton = (Button) findViewById(R.id.SignUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openLoginActivity();
                registerUser();
            }
        });
        //This method create user data to store on firebase


        ///The progress create new user
    }
        public void openLoginActivity () {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    private void registerUser () {
        final String EmailAddress = editTextEmail.getText().toString().trim();
        String Password = editTextPassword.getText().toString().trim();
        final String PersonName = editTextUserName.getText().toString().trim();

        //Check for email input
        if (EmailAddress.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        //Check for valid form email
        if (!Patterns.EMAIL_ADDRESS.matcher(EmailAddress).matches()) {
            editTextEmail.setError("Please provide a valid email");
            editTextEmail.requestFocus();
            return;
        }

        //Check for password input
        if (Password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        //Check password length
        if (Password.length() < 6) {
            editTextPassword.setError("Min password length should be 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        //Check username input
        if (PersonName.isEmpty()) {
            editTextUserName.setError("Username is required");
            editTextUserName.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(EmailAddress, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Check if all information fill in the field
                        if (task.isSuccessful()) {
                           //UserInformation storage: EmailAddress, PersonName;
                            UserInformation user = new UserInformation(EmailAddress, PersonName);


                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    //Show confirmation that new account has been register
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUpActivity.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        user.sendEmailVerification();
                                        Toast.makeText(SignUpActivity.this, "Please check your email to verify your account", Toast.LENGTH_LONG).show();
                                        //Redirect to login menu
                                        openLoginActivity();
                                    }
                                    //Show error
                                    else {
                                        Toast.makeText(SignUpActivity.this, "Failed to register! Please try again!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        //Show error
                        else {
                            Toast.makeText(SignUpActivity.this, "Failed to register! Please try again!", Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }

    }