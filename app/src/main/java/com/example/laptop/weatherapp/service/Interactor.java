package com.example.laptop.weatherapp.service;


import com.example.laptop.weatherapp.model.Result;

import io.reactivex.Observable;
/** MVP begins*/
/* Step 1 : Create an Interactor Interface with same Observable but without Get, goto -> ApiManager **/
public interface Interactor {

    Observable<Result> getResultUseCase(Integer city_id, String api_key);
}
