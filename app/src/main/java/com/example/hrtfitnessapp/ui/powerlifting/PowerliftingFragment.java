package com.example.hrtfitnessapp.ui.powerlifting;

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

public class PowerliftingFragment extends Fragment {

    private PowerliftingViewModel powerliftingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        powerliftingViewModel =
                new ViewModelProvider(this).get(PowerliftingViewModel.class);
        View root = inflater.inflate(R.layout.nav_screen_fragment_powerlifting, container, false);
        final TextView textView = root.findViewById(R.id.text_powerlifting1);
        powerliftingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}