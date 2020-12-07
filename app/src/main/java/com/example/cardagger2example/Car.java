package com.example.cardagger2example;

import android.util.Log;

import javax.inject.Inject;

/**
 * [order of injection] = Constructor > Filed > Method
 */

public class Car {
    private static final String TAG = "Car";
    private final Wheels wheels;
    private final Engine engine;

    /**
     * Constructor injection
     * Inject: in order for dagger to know the way to instantiate the car class we have to mock our
     * constructor that suppose to use
     *
     * @param wheels
     * @param engine
     */
    @Inject
    public Car(Wheels wheels, Engine engine) {
        this.wheels = wheels;
        this.engine = engine;
    }

    public void drive() {
        Log.d(TAG, "driving.. : " + this);
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
