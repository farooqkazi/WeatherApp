package com.example.laptop.weatherapp.di;


import com.example.laptop.weatherapp.service.Interactor;
import com.example.laptop.weatherapp.service.ApiManager;

import dagger.Module;
import dagger.Provides;
/** Dagger begins */
// Dagger is informed how to construct an Interactor to provide for the Presenter.

// Step 1.1 : Create a new class Module
// Step 2 : Annotate a Module.
// Step 3 : Get Interactor Object by creating a new method, goto -> Component.
@Module
public class WeatherModule {

    @Provides
    public Interactor getInteractorObject(){

        return new ApiManager();
    }
}
