package com.example.cardagger2example;

import android.app.Application;

import com.example.cardagger2example.dagger.ActivityComponent;
import com.example.cardagger2example.dagger.AppComponent;
import com.example.cardagger2example.dagger.DaggerAppComponent;
import com.example.cardagger2example.dagger.DriverModule;

import dagger.internal.DaggerCollections;

public class CarExampleApp extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * we don't have any stateful module and no binds instance method so we don't pass anything
         * to this component the driverModule will be create and instantiate automatically
         */
        // component = DaggerAppComponent.create();
        component = DaggerAppComponent.factory().create(new DriverModule("Hossam"));

    }

    public AppComponent getCarComponent(){
        return component;

    }
}
