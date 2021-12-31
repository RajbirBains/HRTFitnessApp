package com.example.hrtfitnessapp.ui.bodybuilding;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BodyBuildingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BodyBuildingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}