package com.example.cardagger2example.dagger;


import com.example.cardagger2example.car.Engine;
import com.example.cardagger2example.car.PetrolEngine;

import dagger.Binds;
import dagger.Module;

/**
 * we can't use normal provides method because it's need instance of module but dagger never create
 * instance of it  but we can use static provides method
 *
 * we accomplish runtime inject as we did before with DieselEngine um another way so we should
 * use bindInstance over module constructor argument whenever possible
 */
@Module
public abstract class PetrolEngineModule {

    /* redundant replace with abstract and bind

    @Provides
    Engine provideEngine(PetrolEngine petrolEngine) {
        return petrolEngine;
    }*/

    /**
     * Binds methods are more concise because they are declared as abstract methods without a body,
     * and they are more efficient because Dagger doesn't have to invoke them or even instantiate
     * their containing module. it's instantiate {@param petrolEngine} directly
     *
     * whenever you just bind an implementation to interface for example we need an engine an petrol
     * engine then we should use binds instead of provides
     */
    @Binds
    abstract Engine bindEngine(PetrolEngine petrolEngine);

}
