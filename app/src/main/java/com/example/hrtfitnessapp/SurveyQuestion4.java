package com.example.hrtfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.chip.ChipGroup;

public class SurveyQuestion4 extends AppCompatActivity {
    private Button finishButton, prevButton;
    private Boolean hasClickedOne;
    private ChipGroup chipGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_question4);

        finishButton = findViewById(R.id.btnFinishSurv);
        prevButton = findViewById(R.id.btnPrevQ4);

        hasClickedOne = false;

        chipGroup = findViewById(R.id.surv1ChipGroupQ4);

        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                hasClickedOne = true;
            }
        });


        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasClickedOne){
                    Intent itNext = new Intent();
                    itNext.setClass(SurveyQuestion4.this, PostSurvey.class);
                    startActivity(itNext);
                }
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itPrev = new Intent();
                itPrev.setClass(SurveyQuestion4.this, SurveyQuestion3.class);
                startActivity(itPrev);
            }
        });
    }
}