package com.example.hrtfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PostSurvey extends AppCompatActivity {
    private Button toHRTButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_survey);

        toHRTButton = findViewById(R.id.btnGotoApp);

        toHRTButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenNavScreenActivity();
            }
        });


    }

    public void OpenNavScreenActivity(){
        Intent intent1 = new Intent(this,NavScreen.class);
        startActivity(intent1);
    }
}