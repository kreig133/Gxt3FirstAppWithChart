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

    public static void main( String[] args ) {
        final RExponenta rExponenta = new RExponenta( 200, 50 );

        double[] range = new double[1000000];

        double sum = 0.0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for ( int i = 0; i < range.length; i++ ) {
            sum += ( range[ i ] = rExponenta.next() );
            if ( min > range[ i ] ) min = range[ i ];
            if ( max < range[ i ] ) max = range[ i ];
        }

        double mOzhid = sum / range.length;

        System.out.println( "mOzhid = " + mOzhid );
        System.out.println( "max = " + max );
        System.out.println( "min = " + min );
    }
}
