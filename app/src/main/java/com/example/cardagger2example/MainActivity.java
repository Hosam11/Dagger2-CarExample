package com.example.cardagger2example;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cardagger2example.car.Car;
import com.example.cardagger2example.dagger.ActivityComponent;
import com.example.cardagger2example.dagger.DaggerActivityComponent;

import javax.inject.Inject;

/* About generated classes
-> @Provide method always has to be executed because the annotation process doesn't know what happened
   in the method body it doesn't know that we just want to return the implementation for interface
-> but @Bind method are abstract so dagger knows there is no method body nothing
to execute and it just call the constructor directly

-> dagger creates _Factory for each @Inject constructor and _Provide#ClassNameFactory for
    each @Provides method
-> there is no factory created for @Bind  methods because dagger doesn't have to call code for it and it just
   directly call the constructor instead
-> Factory classes knows how to created corresponding object
->Provider<> is the wrapper object that know how to created instance of the object they are wrapped
 */

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
    Car car1, car2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Car", "----------------------------------------");
        /*
        this create method is only available if none of the module in your component take args
        over the constructor
        // CarComponent carComponent = DaggerCarComponent.create();
        */

        /* [before singleton]
        CarComponent carComponent = DaggerCarComponent.builder()
        // .dieselEngineModule(new DieselEngineModule(100))
        .horsePower(150)
        .engineCapacity(170)
        .build();
        */

        /*
        this work in case you want a driver to be singleton through the application scope
        and car not singleton so we defined the carComponent in application class.

        ActivityComponent carComponent = ((CarExampleApp) getApplication()).getCarComponent();
        */

        /* now we have an ActivityComponent that contain an AppComponent internally where it's get
        our driver from.
        */
        ActivityComponent component = DaggerActivityComponent.builder()
                .horsePower(100)
                .engineCapacity(150)
                // we have to pass an AppComponent because we define that out Activity component
                // depends on AppComponent to work
                .appComponent(((CarExampleApp) getApplication()).getCarComponent())
                .build();

        //[Old Code#1#] car = carComponent.getCar();
        // take mainActivity and inject the member variable car
        component.inject(this);
        car1.drive();
        car2.drive();
    }
}