package com.example.hrtfitnessapp.ui.Tracker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrackerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TrackerViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is tracker fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}