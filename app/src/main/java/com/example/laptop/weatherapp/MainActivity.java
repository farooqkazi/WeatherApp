package com.example.laptop.weatherapp;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.laptop.weatherapp.helper.ItemsMarginDecorator;
import com.example.laptop.weatherapp.helper.NetworkCheck;
import com.example.laptop.weatherapp.model.List;
import com.example.laptop.weathertestapp.R;
import com.example.laptop.weatherapp.adapter.WeatherAdapter;
import com.example.laptop.weatherapp.di.MyApp;
import com.example.laptop.weatherapp.presenter.WeatherContract;
import com.example.laptop.weatherapp.presenter.WeatherPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
/** MVP steps contd*/
/* Step 5 : Let the MainActivity or Fragment implement the IView **/
/* Step 6 : Implement Methods goto -> Presenter **/
/* Step 11 : Initialize the Presenter in onCreate **/
/* Step 12 : Initialize the Interactor **/
/* Step 13 : Bind the Presenter in establishConnection() **/
/* Step 15 : Unbind the Presenter in onDestroy() **/

public class MainActivity extends AppCompatActivity implements WeatherContract.IView {

    @Inject WeatherPresenter presenter;
    SwipeRefreshLayout swiperLayout;
    WeatherPresenter weatherPresenter;

    Unbinder unbinder;

    @BindView(R.id.rvWeather) RecyclerView rvWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        //Step 14 : Injection Activity
        ((MyApp)getApplication()).getWeatherComponent().inject(this);

        presenter.bindView(this);
        initialiseRecyclerView();
        presenter.fetchWeather();
//        callService();

        swiperLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        swiperLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //callService();

            }
        });


    }

    /*private void callService() {
        if (NetworkCheck.isNetworkAvailable(this)) {
            weatherPresenter.fetchWeather();

        }
        else{

        }

    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        presenter.unbind();
    }

    private void initialiseRecyclerView() {
        rvWeather.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvWeather.addItemDecoration(new ItemsMarginDecorator(getApplication().getResources().getDimensionPixelSize(R.dimen.item_margin)));
    }

    @Override
    public void shouldShowDataInRecyclerView(java.util.List<List> weather_list) {

        rvWeather.setAdapter(new WeatherAdapter(weather_list, R.layout.row_weather, getApplicationContext()));
    }
}
