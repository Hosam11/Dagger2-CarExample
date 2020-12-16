package com.example.cardagger2example.dagger;

import com.example.cardagger2example.car.DieselEngine;
import com.example.cardagger2example.car.Engine;

import dagger.Module;
import dagger.Provides;

/**
 * [Update ## part8 ##] there is an efficient way to achieve same result as we do it here but will
 * keep that code to review
 *
 * instead to pass the horsePower to the module then pass that module to the builder we can pass
 * the horsePower to the module directly and that what we do in the PertrolEngine
 */
@Module
public class DieselEngineModule {
    // so we can pass it at runtime
    private final int horsePower;

    public DieselEngineModule(int horsePower) {
        this.horsePower = horsePower;
    }
    /*
        //[Old Code >>2<<] //
        since we have not @Inject constructor anymore we can't use @Binds  instead we have use @Provides
        because bind doesn't support any configuration and the method need body
        @Binds
        abstract Engine bindEngine(DieselEngine engine);
    */

    /**
     * [Don't] provideEngine(int horsePower)
     * Since we instantiate horsePower we don't have to pass it as  argument to this method
     * We also create provides method for horsePower if will use in other places as well like AppContext
     *
    @Provides
    Engine provideEngine(){
        return new DieselEngine(horsePower);
    }
    */

    /**
     * [Update ## part8 ##] instead of use the horsePower directly we can create a Provides methods
     * dagger now know how to get horsePower from (from @Inject Constructor)that need to
     * DieselEngine
     */
    @Provides
    Engine provideEngine(DieselEngine dieselEngine) {
        return dieselEngine;
    }

    /**
     * this add it to dependency graph directly which have benefit that dagger will use it whenever
     * this value is needed where before we only this one time in the constructor and now we call
     * make dagger inject the constructor of {@link DieselEngine} directly and we don't have to do
     * it manually anymore
     *
     * now we tell dagger whenever Int value in needed you can get it from this Provides method
     * keep in mind we don't say when ever horse power needed
     */
    @Provides
    int provideHorsePower() {
        return horsePower;
    }
}
