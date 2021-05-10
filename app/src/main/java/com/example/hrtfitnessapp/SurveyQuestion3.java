package com.example.hrtfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.chip.ChipGroup;

public class SurveyQuestion3 extends AppCompatActivity {
    private Button nextButton, prevButton;
    private Boolean hasClickedOne;
    private ChipGroup chipGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_question3);

        hasClickedOne = false;

        nextButton = findViewById(R.id.btnNextQ3);
        prevButton = findViewById(R.id.btnPrevQ3);

        chipGroup = findViewById(R.id.surv1ChipGroupQ3);

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
                    itNext.setClass(SurveyQuestion3.this, SurveyQuestion4.class);
                    startActivity(itNext);
                }
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itPrev = new Intent();
                itPrev.setClass(SurveyQuestion3.this, SurveyQuestion2.class);
                startActivity(itPrev);
            }
        });
    }
}