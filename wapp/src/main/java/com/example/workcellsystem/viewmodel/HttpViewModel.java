package com.example.workcellsystem.viewmodel;

import android.media.Image;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.example.workcellsystem.web.HttpRepository;

public class HttpViewModel extends ViewModel {
    private SavedStateHandle mState;
    private HttpRepository httpRepository;

    public HttpViewModel(SavedStateHandle mState, HttpRepository httpRepository) {
        this.mState = mState;
        this.httpRepository = httpRepository;
    }

    private int id = 1;
    private LiveData<Image> image = httpRepository.getImage(id);

}
