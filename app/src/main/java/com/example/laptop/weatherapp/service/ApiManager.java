package com.example.laptop.weatherapp.service;


import com.example.laptop.weatherapp.constants.Constants;
import com.example.laptop.weatherapp.model.Result;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/** MVP steps contd*/
/* Step 2 : Let the ApiManager implement the Interactor Interface **/
/* Step 3 :  Create an empty Constructor to call getConnection() method, goto -> Contract**/

public class ApiManager implements Interactor {

    public ApiManager() {
        getConnection();
    }

    public static IWeatherAPI getConnection(){

        Retrofit retrofit = null;
        OkHttpClient okHttpClient = null;
        /**
         * Used to print the log statements of the parsed json data in the logcat
         */
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        /**
         * Add HttpLoginInterecptor to okhttp
         */
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        if (retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    /**
                     * used to parse json to pojos
                     */
                    .addConverterFactory(GsonConverterFactory.create())
                    /**
                     * Display data received to RecyclerView
                     */
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    /**
                     * Add Okthhp as a friend
                     */
                    .client(okHttpClient)
                    .build();
        }
        return retrofit.create(IWeatherAPI.class);
    }

    @Override
    public Observable<Result> getResultUseCase(Integer city_id, String api_key) {
        return getConnection().getResult(city_id, api_key);
    }
}
