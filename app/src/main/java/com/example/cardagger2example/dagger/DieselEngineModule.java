package com.example.cardagger2example.dagger;

import com.example.cardagger2example.car.DieselEngine;
import com.example.cardagger2example.car.Engine;

import dagger.Module;
import dagger.Binds;

@Module
public abstract class DieselEngineModule  {

    @Binds
    abstract Engine bindEngine(DieselEngine engine);

}
