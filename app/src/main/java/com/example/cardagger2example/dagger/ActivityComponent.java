package com.example.cardagger2example.dagger;


import com.example.cardagger2example.MainActivity;
import com.example.cardagger2example.car.Car;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Component Used on an interface. This interface is used by Dagger 2 to generate code
 * which uses the modules to fulfill the requested dependencies.
 * <p>
 * we don't need to specify how getCar() work because dagger do that for us
 * by compile code generation
 * <p>
 * We create a new Java interface that we annotate with @Component and which has 1 method
 * declaration that returns our requested fully instantiated object.
 * Dagger will then at compile time generate the implementation for this so-called provision method
 * so we can use it in our activity.
 * In order for this to work, we have to tell Dagger how to create all the necessary dependencies.
 * <p>
 * One way of doing this is with the help of constructor injection, for which we have to annotate
 * all the constructors Dagger has to know about with @Inject.
 * This javax annotation is standardized under JSR 330.
 * Dagger will then internally generate a directed acyclic graph (DAG) that contains all the
 * dependencies in the correct order.
 * For this, it doesn't use any Java reflection like earlier dependency injection frameworks
 * (like Spring, Guice or Dagger 1).
 * <p>
 * if there is any dependency in this component use singleton so we have to annotate this interface
 * singleton
 * dependencies: tell dagger whenever we create an ActivityComponent it need an AppComponent to
 * work because it's where you get Driver from and the way we get this AppComponent to
 * ActivityComponent via setter method that generate automatically
 */
//@Singleton
@PerActivity
@Component(dependencies = AppComponent.class, modules = {WheelsModule.class, PetrolEngineModule.class})
public interface ActivityComponent {

    /**
     * //[Old Code#1#] //
     * We can get Car from module but since we have the Car class and can inject the constructor
     * that not necessary
     */
    Car getCar();

    void inject(MainActivity mainActivity);

    /**
     * define api for CarComponent by ourselves so we can call different method on
     * DaggerComponent.Builder() and we have to add build method that return CarComponent
     */
    @Component.Builder
    interface MyBuilder {

        // we don't need body for that method dagger will implement automatically just have to
        // declare it because we have overriding the builder definition
        ActivityComponent build();

        /**
         * With @BindsInstance we can get variable to our dependency graph at runtime which has the
         * same effect  as pass a value at runtime to the module and then provides it over provides
         * method but it's more efficient because dagger don't need to create instance of the
         * <p>
         * So we can turn the {@link  DieselEngineModule} back to abstract module tha contain a
         * binds method as we added before  and remove all of hoursPower part but we leave it at is
         * it so we can return to code later and review it
         */
        @BindsInstance
        MyBuilder horsePower(@Named("horsePower") int horsePower);

        @BindsInstance
        MyBuilder engineCapacity(@Named("engineCapacity") int engineCapacity);

        // declare setter method for AppComponent
        MyBuilder appComponent(AppComponent appComponent);
    }
}
