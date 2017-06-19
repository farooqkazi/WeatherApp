package com.example.laptop.weatherapp.service;

import com.example.laptop.weatherapp.constants.Constants;
import com.example.laptop.weatherapp.model.Result;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by laptop on 12/06/2017.
 */

public interface IWeatherAPI {

    @GET(Constants.WEATHER_URL)
    Observable<Result> getResult(@Query("id") Integer city_id,
                                 @Query("APPID") String api_key);
}
