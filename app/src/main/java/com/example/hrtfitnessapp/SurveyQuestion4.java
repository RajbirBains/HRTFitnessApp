package com.example.hrtfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.chip.ChipGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SurveyQuestion4 extends AppCompatActivity {
    private Button finishButton, prevButton;
    private Boolean hasClickedOne;
    private ChipGroup chipGroup;
    private DatabaseReference ref;
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
                    ref = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    ref.child("SurveyStatus").setValue(true);
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