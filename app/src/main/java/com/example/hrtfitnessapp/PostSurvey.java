package com.example.hrtfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostSurvey extends AppCompatActivity {
    private Button toHRTButton;
    private DatabaseReference ref;
    private DatabaseReference liftsAndMacros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_survey);

        toHRTButton = findViewById(R.id.btnGotoApp);

        toHRTButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenNavScreenActivity();

                ref = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                liftsAndMacros = ref.child("Tracker");

                liftsAndMacros.child("squat").setValue(0);
                liftsAndMacros.child("bench").setValue(0);
                liftsAndMacros.child("deadlift").setValue(0);
                liftsAndMacros.child("fat").setValue(0);
                liftsAndMacros.child("carbs").setValue(0);
                liftsAndMacros.child("protein").setValue(0);

            }
        });


    }


    public void OpenNavScreenActivity(){
        Intent intent1 = new Intent(this,NavScreen.class);
        startActivity(intent1);


    }
}