package com.example.cardagger2example.dagger;


import com.example.cardagger2example.car.Driver;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @Singleton because provide all object that  are scoped with @Singleton which is Driver because
 * we want to tell dagger that we want to get this object from appComponent and not fromActivity
 * component  which has PerActivity annotation
 * <p>
 * Usually we user @Singleton on the app component because it's outer scope  and otherwise the term
 * Singleton might be misleading and you can ignore @Singleton completely and create a custom scope
 * for app component
 * <p>
 * We want connect the 2 component, we want to instantiate the ActivityComponent in our
 * activity whenever you need a driver don't instantiate yourself instead get it from app component
 * and we have to define what we want to expose to outside from appComponent otherwise our
 * ActivityComponent doesn't have access to it
 * <p>
 * Our AppComponent contains the DriverModule so it's responsible for provides a driver and our
 * ActivityComponent contains EngineModule and WheelsModule
 * <p>
 * The SubComponent {@link ActivityComponent} can access whole object graph of it's parent right now
 * it's only the {@link Driver} from DriverModule but if you need more object from
 * {@link AppComponent} ActivityComponent can access to all of them where is the component that
 * depends on component vi component decadency can only access object that parent component exposes
 * explicitly like getDriver() method in AppComponent
 */

@Singleton
@Component(modules = DriverModule.class)
public interface AppComponent {

    // Driver getDriver();

    /* Factory method
    DieselEngineModule takes horsePower as an constructor argument and we have to pass it at runtime
    */
    //ActivityComponent getActivityComponent(DieselEngineModule dieselEngineModule);

    ActivityComponent.MyFactory getActivityComponentFactory();

    @Component.Factory
    interface MyFactory {
        // if AppComponent have a component dependency then we should pass it as argument
        AppComponent create(DriverModule driverModule);
    }

}



