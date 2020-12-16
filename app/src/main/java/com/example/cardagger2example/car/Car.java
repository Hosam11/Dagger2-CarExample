package com.example.cardagger2example.car;

import android.util.Log;

import com.example.cardagger2example.dagger.PerActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * [order of injection] = Constructor > Filed > Method
 */

/**
 * @PerActivty this will work exactly as singleton application it tell dagger to create only single
 * instance of a car within the same component there is nothing special about singleton all the
 * scope annotation work the same so if you want use this for AppComponent dagger will still create
 * application singleton it doesn't know what PerActivity means it's doesn't know that it suppose
 * to get rid of this car when activity is destroy we are responsible of realizing the new scope
 * and the way we do this by create a second component which is only lives as long as activity
 * while the component that we declared in our application class lives as long as application
 */
@PerActivity
//@Singleton
public class Car {
    private static final String TAG = "Car";
    private final Wheels wheels;
    private final Engine engine;
    private final Driver driver;

    /**
     * Constructor injection
     * Inject: in order for dagger to know the way to instantiate the car class we have to mock our
     * constructor that suppose to use
     * <p>
     * [Update]
     * - We change Engine to interface rather than Class so @Inject constructor doesn't work
     * - To solve that create classes that implement the interface and @Inject their constructors
     * there will be another problem that dagger will not know which class to instantiate so
     * we will make a module for class we wanted
     */
    @Inject
    public Car(Driver driver, Wheels wheels, Engine engine) {
        this.wheels = wheels;
        this.engine = engine;
        this.driver = driver;
    }

    public void drive() {
        engine.start();
        Log.d(TAG, driver + " drives : " + this);
    }

    /**
     * Method Injection:
     * By annotating a non-private method with @Inject, we tell Dagger to call it as part of the
     * injection process and provide its arguments as dependencies
     * <p>
     * In combination with constructor injection, this happens automatically after the constructor
     * finished. Without constructor injection, it happens when the members-injection method is
     * called on the component.
     * In both cases, the method (or methods) is called after all @Inject annotated fields are
     * injected.
     * A use case for method injection is if we want to pass the fully constructed object itself
     * to the dependency with “this”, so we don’t let it escape from the constructor
     *
     * @param remote
     */
    @Inject
    public void enableRemote(Remote remote) {
        remote.setListener(this);
    }
}
