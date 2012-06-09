package com.kreig133.gxt.svdiplom.client.model;

/**
 * @author kreig133
 * @version 1.0
 */
public class Measurment {

    private double time;
    private double temp;
    private boolean withNoise;

    public Measurment( double time, double temp, boolean withNoise ) {
        this.time = time;
        this.temp = temp;
        this.withNoise = withNoise;
    }

    public double getTime() {
        return time;
    }

    public double getTemp() {
        return temp;
    }

    public boolean isWithNoise() {
        return withNoise;
    }
}
