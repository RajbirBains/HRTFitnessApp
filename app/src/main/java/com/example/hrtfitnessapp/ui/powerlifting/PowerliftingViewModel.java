package com.example.hrtfitnessapp.ui.powerlifting;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PowerliftingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PowerliftingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}