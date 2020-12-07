package com.example.cardagger2example.dagger;
import com.example.cardagger2example.car.Rims;
import com.example.cardagger2example.car.Tires;
import com.example.cardagger2example.car.Wheels;

import dagger.Module;
import dagger.Provides;

/**
 * Modules are simple Java classes that we annotate with @Module and add to a component.
 * They contain methods that are annotated with @Provides and which define how objects get
 * instantiated and configured.
 *
 * These provider methods can have their very own dependencies as arguments, which Dagger will
 * then provide if possible.
 *
 * By making provider methods static, we can get a performance boost because our component doesn't
 * have to instantiate the module to call them.
 */
@Module
public class WheelsModule {

    @Provides
    static Rims provideRims() {
        return new Rims();
    }

    @Provides
    static Tires provideTires(){
        Tires tires = new Tires();
        tires.inflate();
        return tires;
    }


    /**
     * when dagger call this method it will pass  {@param rims} and {@param tires} that
     * will get them from those methods  provideRims() and provideTires()
     */
    @Provides
    static Wheels provideWheels(Rims rims, Tires tires) {
        return new Wheels(rims, tires);
    }


}
