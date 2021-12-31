package com.example.hrtfitnessapp.ui.athletics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hrtfitnessapp.R;

public class AthleticsFragment extends Fragment {

    private AthleticsViewModel athleticsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        athleticsViewModel =
                new ViewModelProvider(this).get(AthleticsViewModel.class);
        View root = inflater.inflate(R.layout.nav_screen_fragment_athletics, container, false);
        final TextView textView = root.findViewById(R.id.text_home_workout_counter);
        athleticsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}