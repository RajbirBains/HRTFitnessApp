package com.example.hrtfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SurveyQuestion1 extends AppCompatActivity {
    private Button nextButton, prevButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_question1);

        nextButton = findViewById(R.id.btnNextQ1);
        prevButton = findViewById(R.id.btnPrevQ1);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itNext = new Intent();
                itNext.setClass(SurveyQuestion1.this, SurveyQuestion2.class);
                startActivity(itNext);
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itPrev = new Intent();
                itPrev.setClass(SurveyQuestion1.this, SurveyStart.class);
                startActivity(itPrev);
            }
        });
    }
}