package com.example.hrtfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.chip.ChipGroup;

public class SurveyQuestion1 extends AppCompatActivity {
    private Button nextButton, prevButton;
    private ChipGroup chipGroup;
    private Boolean hasClickedOne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_question1);

        hasClickedOne = false;

        nextButton = findViewById(R.id.btnNextQ1);
        prevButton = findViewById(R.id.btnPrevQ1);

        chipGroup = findViewById(R.id.surv1ChipGroupQ1);

        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                hasClickedOne = true;
            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasClickedOne){
                    Intent itNext = new Intent();
                    itNext.setClass(SurveyQuestion1.this, SurveyQuestion2.class);
                    startActivity(itNext);
                }
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