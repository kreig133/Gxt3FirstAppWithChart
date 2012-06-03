package com.kreig133.gxt.svdiplom.client.model;

import java.util.Random;

/**
 * @author kreig133
 * @version 1.0
 */
public class RExponenta {
    private Random rand = new Random();

    private double M;

    private double min;

    public RExponenta( double m, double min ) {
        M = m;
        this.min = min;
    }

    public double next(){
        return  ( min - M ) * Math.log( rand.nextDouble() ) + min;
    }
}
