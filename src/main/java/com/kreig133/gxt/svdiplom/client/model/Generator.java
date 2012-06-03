package com.kreig133.gxt.svdiplom.client.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kreig133
 * @version 1.0
 */
public class Generator {
    public final double STEP = 0.1;

    protected double currentTime = 0.0;

    private static final int PADDLE_COUNT = 80;
    final Paddle[] paddles = new Paddle[ PADDLE_COUNT ];

    public Generator( double temp ) {
        RGauss gTemp       = new RGauss    ( temp   , 50.0   );
        for( int i = 0; i < PADDLE_COUNT; i ++ ) {
            paddles[ i ] = new Paddle( this, gTemp.next() );
        }
    }

    public Data next() {
        try {
            List<Double> tempList = new ArrayList<Double>( PADDLE_COUNT );
            for ( Paddle paddle : paddles ) {
                tempList.add( paddle.next() );
            }
            return new Data( currentTime, tempList );
        } finally {
            currentTime += STEP;
        }
    }


}
