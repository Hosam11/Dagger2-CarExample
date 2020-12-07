package com.example.cardagger2example.car;


/**
 * assume that we don't own this class it's come from third party so we can't annotate it with @Inject
 */
public class Wheels {

    Rims rims;
    Tires tires;

    public Wheels(Rims rims, Tires tires) {
        this.rims = rims;
        this.tires = tires;
    }
}
