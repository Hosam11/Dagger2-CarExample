package com.example.cardagger2example.car;

import android.util.Log;

import javax.inject.Inject;

public class  DieselEngine implements Engine {
    private static final String TAG = "Car";

    private int horsePower;
    /*
    //[Old Code >>2<<] before ad horsePower Member  //
    @Inject
    public DieselEngine (){}
    */

    /**
     * we don't know value of {@param horsePower} so we want to pass it at runtime when we updated
     * the component, since dagger can't instantiate instructor directly we can remove  @Inject
     * because we have to call the constructor and pass horsePower value
     */
    // [Updated] @Inject
    @Inject
    public DieselEngine(int horsePower) {
        this.horsePower = horsePower;
    }

    @Override
    public void start() {
        Log.d(TAG, " Diesel engine started: " + this + " horsePower: " + horsePower);
    }
}
