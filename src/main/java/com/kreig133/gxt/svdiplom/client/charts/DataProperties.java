package com.kreig133.gxt.svdiplom.client.charts;

import java.util.Date;

import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * @author kreig133
 * @version 1.0
 */
public interface DataProperties extends PropertyAccess<Data> {
    ValueProvider<Data, String> name();

    ValueProvider<Data, Double> data1();

    ValueProvider<Data, Double> data2();

    @Editor.Path( "name" )
    ModelKeyProvider<Data> nameKey();
}
