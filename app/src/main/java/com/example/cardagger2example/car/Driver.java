package com.example.cardagger2example.car;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * If a class doesn't get from constructor then you should annotate the @Provides method that return
 * the class with @Singleton
 *
 * This annotation only work with in the same component as soon as we create another instance form
 * component it's get another value
 */
@Singleton
public class Driver {

    // assume  we don't own this class it's come from third party so we can't annotate it with
    // @Inject instead we get it form @Provide


    /*@Inject
    public Driver() {
    }*/
}
