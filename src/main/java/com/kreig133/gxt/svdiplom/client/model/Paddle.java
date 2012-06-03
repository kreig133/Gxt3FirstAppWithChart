package com.kreig133.gxt.svdiplom.client.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kreig133
 * @version 1.0
 */
public class Paddle {
    private final double temp;

    private final RExponenta gDuration   = new RExponenta( 0.35   , 0.050  );
    private final RGauss     gAmplitude  = new RGauss    ( 400.0  , 400.0  );
    private final RGauss     gFrequency  = new RGauss    ( 1500.0 , 1400.0 );

    private double startNoise;
    private double endNoise;
    private double amplitude;

    private List<Measurment> measurmentList = new ArrayList<Measurment>( 100 );
    
    private Generator generator;

    public Paddle( Generator generator, double temp ) {
        this.generator = generator;
        this.temp = temp;
    }

    public List<Measurment> getMeasurmentList() {
        return measurmentList;
    }

    public Double next(){
        amplitude = gAmplitude.next();

        if (  endNoise <= generator.currentTime ) {
            startNoise = generator.currentTime + ( 1000.0 / gFrequency.next() );
            amplitude = gAmplitude.next();
            endNoise = startNoise + gDuration.next();
        }

        final boolean withNoise = ( startNoise <= generator.currentTime ) && ( generator.currentTime <= endNoise );

        final Measurment measurment = new Measurment(
                generator.currentTime,
                withNoise ? temp + amplitude : temp,
                withNoise
        );

        measurmentList.add( measurment );

        return measurment.getTemp();
    }
}
