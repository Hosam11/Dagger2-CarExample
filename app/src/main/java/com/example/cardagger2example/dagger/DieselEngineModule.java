package com.example.cardagger2example.dagger;

import com.example.cardagger2example.car.DieselEngine;
import com.example.cardagger2example.car.Engine;

import dagger.Module;
import dagger.Binds;
import dagger.Provides;

@Module
public  class DieselEngineModule  {
    // so we can pass it at runtime
    private int horsePower;

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
     * [Don't] provideDiselEngine(int horsePower)
     * Since we instantiate horsePower we don't declare in parameters
     * We also create provides method for horsePower if will use in other places as well like AppContext
     */
    @Provides
    Engine provideEngine(){
        return new DieselEngine(horsePower);
    }
}
