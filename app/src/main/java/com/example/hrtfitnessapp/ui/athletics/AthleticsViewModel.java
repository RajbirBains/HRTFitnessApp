package com.example.hrtfitnessapp.ui.athletics;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AthleticsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AthleticsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Have you worked out today?");
    }

    public LiveData<String> getText() {
        return mText;
    }
}