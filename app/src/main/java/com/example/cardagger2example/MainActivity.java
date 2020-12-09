package com.example.cardagger2example;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cardagger2example.car.Car;
import com.example.cardagger2example.dagger.CarComponent;
import com.example.cardagger2example.dagger.DaggerCarComponent;
import com.example.cardagger2example.dagger.DieselEngineModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    /**
     * Field Injection:
     * By annotating a non-private, non-final member variable with @Inject, we tell Dagger to set
     * its value by referencing it directly.
     * Field injection is mainly used to inject variables into Android framework types that the
     * system instantiates and on which we can’t do constructor injection, like activities,
     * BroadcastReceivers and fragments.
     * <p>
     * To trigger the injection process on an already instantiated object (like an activity),
     * we have to create and call a members-injection method in our component that takes this
     * particular class as an argument.
     */
    @Inject
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
          this create method is only available if none of the module in your component take args
         over the constructor
        */
        // CarComponent carComponent = DaggerCarComponent.create();
        CarComponent carComponent = DaggerCarComponent.builder()
                .dieselEngineModule(new DieselEngineModule(100))
                .build();


        //[Old Code#1#] car = carComponent.getCar();
        // take mainActivity and inject the member variable car
        carComponent.inject(this);
        car.drive();
    }
}