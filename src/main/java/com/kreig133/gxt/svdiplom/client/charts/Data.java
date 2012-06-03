
/**
 * Sencha GXT 3.0.0 - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.kreig133.gxt.svdiplom.client.charts;

public class Data {

    private String name;
    private double data1;
    private double data2;


    public Data(String name, double data1, double data2) {
        super();
        this.name = name;
        this.data1 = data1;
        this.data2 = data2;

    }

    public double getData1() {
        return data1;
    }

    public double getData2() {
        return data2;
    }

    public String getName() {
        return name;
    }

    public void setData1(double data1) {
        this.data1 = data1;
    }

    public void setData2(double data2) {
        this.data2 = data2;
    }

    public void setName(String name) {
        this.name = name;
    }
}
