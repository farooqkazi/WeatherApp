package com.example.laptop.weatherapp.presenter;


import java.util.List;

/** MVP steps contd*/
/* Step 4 : Create a contract interface where we define roles for both View and Presenter goto -> MainActivity**/

public interface WeatherContract {

    interface IWeatherPresenter{

        void bind(IView view);
        void unbind();
        void fetchWeather();
    }

    interface IView{

        void receiveWeather(List<com.example.laptop.weatherapp.model.List> weather_list);
    }
}
