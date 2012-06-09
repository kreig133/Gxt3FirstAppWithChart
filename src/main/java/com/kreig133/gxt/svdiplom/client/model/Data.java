package com.kreig133.gxt.svdiplom.client.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author kreig133
 * @version 1.0
 */
public class Data implements Serializable {

    private Double time;
    private List<Double> measures;

    public Data( Double time, List<Double> measures ) {
        this.time = time;
        this.measures = measures;
    }

    public Double getTime() {
        return time;
    }

    public List<Double> getMeasures() {
        return measures;
    }
}
