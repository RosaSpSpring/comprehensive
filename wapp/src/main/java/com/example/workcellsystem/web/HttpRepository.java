package com.example.workcellsystem.web;

import android.media.Image;

import androidx.lifecycle.MutableLiveData;

import com.example.workcellsystem.data.HttpEnum;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HttpRepository {

    private HttpService service;

    public HttpRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpEnum.Default.getUrl())
                .build();

        service =  retrofit.create(HttpService.class);
    }

    public MutableLiveData<Image> getImage(int i) {
        MutableLiveData<Image> image = new MutableLiveData<>();
        service.getImage(String.valueOf(i)).enqueue(new Callback<Image>() {
            @Override
            public void onResponse(Call<Image> call, Response<Image> response) {
                if(response.isSuccessful()) {
                    image.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Image> call, Throwable t) {

            }
        });
        return image;
    }

    public HttpService getService() {
        return service;
    }
}
