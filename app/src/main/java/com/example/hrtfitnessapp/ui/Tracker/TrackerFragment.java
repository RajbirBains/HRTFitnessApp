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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private Button reset_button;
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

        // conditions for tracker:
        // check if theres a tracker child meaning that they have inputted a value before
        // check if any of the values are null or not (has to be populated ) as there can be a tracker child if we just inputted 1/6 values
        // if value previously filled then just get the value from database
        input0 = root.findViewById(R.id.squat);
        input1 = root.findViewById(R.id.bench);
        input2 = root.findViewById(R.id.deadlift);
        input3 = root.findViewById(R.id.fat);
        input4 = root.findViewById(R.id.carbs);
        input5 = root.findViewById(R.id.protein);

         ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //get values from database
                    Long a,b,c,d,e,f;
                    a = (Long) snapshot.child("Tracker").child("squat").getValue();
                    b = (Long) snapshot.child("Tracker").child("bench").getValue();
                    c = (Long) snapshot.child("Tracker").child("deadlift").getValue();
                    d = (Long) snapshot.child("Tracker").child("fat").getValue();
                    e = (Long) snapshot.child("Tracker").child("carbs").getValue();
                    f = (Long) snapshot.child("Tracker").child("protein").getValue();

                    System.out.println(a);
                    System.out.println(b);
                    System.out.println(c);
                    System.out.println(d);
                    System.out.println(e);
                    System.out.println(f);

                    //now we write to the fields
                    input0.setText(a.toString());
                    input1.setText(b.toString());
                    input2.setText(c.toString());
                    input3.setText(d.toString());
                    input4.setText(e.toString());
                    input5.setText(f.toString());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
         });

        try{
            save =  (Button)root.findViewById(R.id.tracker_savebutton);

        }catch (Exception e){
            System.out.println("idk bro");
        }
//        save =  (Button) t.findViewById(R.id.tracker_savebutton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liftsAndMacros.child("squat").setValue(Integer.parseInt(input0.getText().toString()));
                liftsAndMacros.child("bench").setValue(Integer.parseInt(input1.getText().toString()));
                liftsAndMacros.child("deadlift").setValue(Integer.parseInt(input2.getText().toString()));
                liftsAndMacros.child("fat").setValue(Integer.parseInt(input3.getText().toString()));
                liftsAndMacros.child("carbs").setValue(Integer.parseInt(input4.getText().toString()));
                liftsAndMacros.child("protein").setValue(Integer.parseInt(input5.getText().toString()));
            }
        });

        try{
            reset_button = (Button)root.findViewById(R.id.reset_button);

        }catch (Exception e){
            System.out.println("Hey Friend");
        }
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liftsAndMacros.child("squat").setValue(0);
                liftsAndMacros.child("bench").setValue(0);
                liftsAndMacros.child("deadlift").setValue(0);
                liftsAndMacros.child("fat").setValue(0);
                liftsAndMacros.child("carbs").setValue(0);
                liftsAndMacros.child("protein").setValue(0);
                input0.setText("0");
                input1.setText("0");
                input2.setText("0");
                input3.setText("0");
                input4.setText("0");
                input5.setText("0");
            }
        });


        return root;
    }
}

