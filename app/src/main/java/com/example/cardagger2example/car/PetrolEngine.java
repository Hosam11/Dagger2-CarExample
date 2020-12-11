package com.example.cardagger2example.car;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;


public class PetrolEngine implements Engine {
    private static final String TAG = "Car";

    private final int horsePower;
    private final int engineCapacity;

    @Inject
    public PetrolEngine(@Named("horsePower") int horsePower,
                        @Named("engineCapacity") int engineCapacity) {
        this.horsePower = horsePower;
        this.engineCapacity = engineCapacity;
    }

    @Override
    public void start() {
        Log.d(TAG, "Petrol engine started: " + this
                + "\n horsePower= " + horsePower
                + "\n engineCapacity= " + engineCapacity
        );
    }
}
