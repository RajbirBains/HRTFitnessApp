package com.example.hrtfitnessapp.ui.Tracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hrtfitnessapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TrackerFragment extends Fragment {

    private TrackerViewModel trackerViewModel;
    private DatabaseReference ref;
    private DatabaseReference liftsAndMacros;
    private Button save;
    private EditText input0;
    private EditText input1;
    private EditText input2;
    private EditText input3;
    private EditText input4;
    private EditText input5;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        trackerViewModel =
                new ViewModelProvider(this).get(TrackerViewModel.class);
        View root = inflater.inflate(R.layout.nav_fragment_tracker, container, false);
        final TextView textView = root.findViewById(R.id.text_tracker);
        trackerViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        ref = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        liftsAndMacros = ref.child("Tracker");


        try{
            save =  (Button)root.findViewById(R.id.tracker_savebutton);

        }catch (Exception e){
            System.out.println("idk bro");
        }
//        save =  (Button) t.findViewById(R.id.tracker_savebutton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input0 = root.findViewById(R.id.squat);
                liftsAndMacros.child("squat").setValue(Integer.parseInt(input0.getText().toString()));

                input1 = root.findViewById(R.id.bench);
                liftsAndMacros.child("bench").setValue(Integer.parseInt(input1.getText().toString()));

                input2 = root.findViewById(R.id.deadlift);
                liftsAndMacros.child("deadlift").setValue(Integer.parseInt(input2.getText().toString()));

                input3 = root.findViewById(R.id.fat);
                liftsAndMacros.child("fat").setValue(Integer.parseInt(input3.getText().toString()));

                input4 = root.findViewById(R.id.carbs);
                liftsAndMacros.child("carbs").setValue(Integer.parseInt(input4.getText().toString()));

                input5 = root.findViewById(R.id.protein);
                liftsAndMacros.child("protein").setValue(Integer.parseInt(input5.getText().toString()));
            }
        });


        return root;
    }
}

