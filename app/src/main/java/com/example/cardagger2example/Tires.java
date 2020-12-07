package com.example.cardagger2example;

import android.util.Log;

/**
 * assume that we don't own this class it's come from third party so we can't annotate it with @Inject
 */
public class Tires {

    private static final String TAG = "Car";

    public void inflate(){
        Log.d(TAG, "Tires inflated: " + this);
    }
}
