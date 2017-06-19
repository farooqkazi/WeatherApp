package com.example.laptop.weatherapp.di;


import com.example.laptop.weatherapp.MainActivity;

import dagger.Component;
/** Dagger Steps Contd **/
// Step 4 : Create a new Component Interface.
// Step 5 : Annotate Component.
// Step 6 : Add a dependency to the Movie Component by adding Movie Module class.
// Step 7 : Mention where it needs to be Injected, goto ->

@Component(dependencies = WeatherModule.class)
public interface WeatherComponent {

    void inject(MainActivity mainActivity);
}
