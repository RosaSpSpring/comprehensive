package com.example.workcellsystem.web;

import android.media.Image;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HttpService {
    @GET("images/{number}.png")
    Call<Image> getImage(@Path("number") String number);
}
