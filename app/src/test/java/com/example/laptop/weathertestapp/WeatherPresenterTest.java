package com.example.laptop.weathertestapp;

import com.example.laptop.weatherapp.constants.Constants;
import com.example.laptop.weatherapp.model.List;
import com.example.laptop.weatherapp.model.Result;
import com.example.laptop.weatherapp.presenter.WeatherContract;
import com.example.laptop.weatherapp.presenter.WeatherPresenter;
import com.example.laptop.weatherapp.service.IWeatherAPI;
import com.example.laptop.weatherapp.service.Interactor;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeatherPresenterTest {

    @Mock
    @Inject
    IWeatherAPI iWeatherAPI;

    @Mock
    WeatherContract.IView iWeatherView;

    @Mock
    Interactor interactor;

    @InjectMocks
    WeatherPresenter presenter;

    @Mock
    List list;

    @Mock
    java.util.List<List> lists;

    @Mock
    Result result;

    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        iWeatherView = mock(WeatherContract.IView.class);

        presenter = new WeatherPresenter(interactor);

        list = new List();

        lists = new ArrayList<>();
        lists.add(list);

        result = new Result();
        result.setList(lists);
    }

    @After
    public void tearDown() throws Exception{

        RxAndroidPlugins.reset();
    }

    @Test
    public void testWeatherPresenter() throws Exception {

        when(interactor.getResultUseCase(Constants.CITY_ID, Constants.API_KEY))
                .thenReturn(Observable.just(result));

        presenter.bindView(iWeatherView);
        presenter.fetchWeather();
        presenter.unbind();

        Mockito.verify(iWeatherView).shouldShowDataInRecyclerView(lists);
    }


}


