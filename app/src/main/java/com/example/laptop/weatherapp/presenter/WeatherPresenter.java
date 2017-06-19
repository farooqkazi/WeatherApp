package com.example.laptop.weatherapp.presenter;


import com.example.laptop.weatherapp.constants.Constants;
import com.example.laptop.weatherapp.model.Result;
import com.example.laptop.weatherapp.service.Interactor;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
/** MVP steps contd*/
/* Step 7 : Create a New class and implement the presenter_interface declared in Contract **/
/* Step 8 : Implement Methods **/
/* Step 9 : Create an object of IView **/
/* Step 10 : Create an object of Interactor goto -> MainActivity **/

public class WeatherPresenter implements WeatherContract.IWeatherPresenter{

    private Interactor interactor;
    private WeatherContract.IView weatherView;
    private CompositeDisposable compositeDisposable;

    @Inject
    public WeatherPresenter(Interactor interactor){
        this.interactor = interactor;
    }


    @Override
    public void bindView(WeatherContract.IView view) {
        this.weatherView = view;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void unbind() {
        this.weatherView = null;
        this.compositeDisposable.dispose();
    }

    @Override
    public void fetchWeather() {

        compositeDisposable.add(interactor.getResultUseCase(Constants.CITY_ID, Constants.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Result>() {

                    @Override
                    public void onNext(Result result) {

                        weatherView.shouldShowDataInRecyclerView(result.getList());
                    }

                    @Override
                    public void onError(Throwable e) {

                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }
}
