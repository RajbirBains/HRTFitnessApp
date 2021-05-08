package com.example.hrtfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class PostSurvey extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_survey);
    }

    public void OpenNavScreenActivity(){
        Intent intent1 = new Intent(this,NavScreen.class);
        startActivity(intent1);
    }
}