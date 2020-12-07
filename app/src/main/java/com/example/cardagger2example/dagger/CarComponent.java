package com.example.cardagger2example.dagger;


import com.example.cardagger2example.MainActivity;
import com.example.cardagger2example.car.Car;

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
 */
@Component(modules = {WheelsModule.class, DieselEngineModule.class})
public interface CarComponent {

    /**
     * We can get Car from module but since we have the Car class and can inject the constructor
     * that not necessary
     */
    Car getCar();

    void inject(MainActivity mainActivity);
}
