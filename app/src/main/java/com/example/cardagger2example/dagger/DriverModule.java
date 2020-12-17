package com.example.cardagger2example.dagger;

import com.example.cardagger2example.car.Driver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * We can provide this module constructor to provides a runtime arguments and in real app
 * you usually do this to get application context into the app component which only available
 * at runtime
 */
@Module
public  class DriverModule {

    private String driverName;

    public DriverModule(String driverName) {
        this.driverName = driverName;
    }
    /* DOESN'T work that creates error: [Dagger/DependencyCycle] Found a dependency cycle:
    @Binds
    abstract Driver bindDriver(@Singleton Driver driver);
    */

    @Provides
    // @PerActivity
    @Singleton
     Driver providesDriver() {
        return new Driver(driverName);
    }
}
