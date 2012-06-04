/**
 * Sencha GXT 3.0.0 - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.kreig133.gxt.svdiplom.client.charts;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Chart.Position;
import com.sencha.gxt.chart.client.chart.Legend;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.series.LineSeries;
import com.sencha.gxt.chart.client.chart.series.Primitives;
import com.sencha.gxt.chart.client.draw.Color;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.path.PathSprite;
import com.sencha.gxt.chart.client.draw.sprite.Sprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Resizable;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

import java.util.List;

public class LineExample implements IsWidget {

    private static final DataProperties dataAccess = GWT.create(DataProperties.class);
    private Chart<Data> chart;
    private int MAX_PADDLE_ON_CHART = 20;

    @Override
    public Widget asWidget() {
        final ListStore<Data> store = new ListStore<Data>(dataAccess.nameKey());

        TextSprite titleY = new TextSprite("Температура");
        titleY.setFontSize( 18 );

        TextSprite titleX = new TextSprite("Лопатка");
        titleX.setFontSize(18);

        PathSprite odd = new PathSprite();
        odd.setOpacity( 1 );
        odd.setFill( new Color( "#ddd" ) );
        odd.setStroke( new Color( "#bbb" ) );
        odd.setStrokeWidth( 0.5 );

        NumericAxis<Data> axisY = new NumericAxis<Data>();
        axisY.setPosition( Position.LEFT );
        axisY.addField( dataAccess.data1() );
        axisY.addField( dataAccess.data2() );
        axisY.setTitleConfig( titleY );
        axisY.setMinorTickSteps( 1 );
        axisY.setDisplayGrid( true );
        axisY.setGridOddConfig( odd );
        axisY.setMinimum( 500 );
        axisY.setMaximum( 2000 );

        NumericAxis<Data> axisX = new NumericAxis<Data>();
        axisX.setPosition( Position.BOTTOM );
        axisX.addField( dataAccess.data1() );
        axisX.addField( dataAccess.data2() );
        axisX.setTitleConfig( titleX );
        axisX.setMinorTickSteps( 1 );
        axisX.setDisplayGrid( true );
        axisX.setGridOddConfig( odd );
        axisX.setMinimum( 0 );
        axisX.setMaximum( MAX_PADDLE_ON_CHART );

        Sprite marker = Primitives.square(0, 0, 6);
        marker.setFill(new RGB(194,0,36));

        final LineSeries<Data> series2 = new LineSeries<Data>();
        series2.setYAxisPosition(Position.LEFT);
        series2.setYField(dataAccess.data2());
        series2.setStroke(new RGB(240,165,10));
        series2.setShowMarkers(true);
        series2.setSmooth(true);
        marker = Primitives.circle(0, 0, 6);
        marker.setFill(new RGB(240,165,10));
        series2.setMarkerConfig(marker);
        series2.setHighlighting(true);


        final LineSeries<Data> series3 = new LineSeries<Data>();
        series3.setYAxisPosition(Position.LEFT);
        series3.setYField(dataAccess.data1());
        series3.setStroke(new RGB(32, 68, 186));
        series3.setShowMarkers(true);
        series3.setSmooth(true);
//        series3.setFill(new RGB(32, 68, 186));
        marker = Primitives.diamond(0, 0, 6);
        marker.setFill(new RGB(32, 68, 186));
        series3.setMarkerConfig(marker);
        series3.setHighlighting(true);


        final Legend<Data> legend = new Legend<Data>();
        legend.setPosition( Position.RIGHT );
        legend.setItemHighlighting( true );
        legend.setItemHiding( true );

        chart = new Chart<Data>();
        chart.setStore( store );
        chart.setShadowChart( true );
        chart.setAnimated( true );
        chart.addAxis( axisY );
        chart.addAxis( axisX );
        chart.addSeries( series3 );
        chart.addSeries( series2 );
        chart.setLegend( legend );


        ContentPanel panel = new ContentPanel();
        panel.getElement().getStyle().setMargin( 10, Unit.PX );
        panel.setCollapsible( true );
        panel.setHeadingText( "Line Chart" );
        panel.setPixelSize( 1000, 500 );
        panel.setBodyBorder( true );



        VerticalLayoutContainer layout = new VerticalLayoutContainer();
        panel.add( layout );

        Resizable resize = new Resizable(panel);
        resize.setMinHeight(400);
        resize.setMinWidth(400);

        chart.setLayoutData( new VerticalLayoutData( 1, 1 ) );
        layout.add( chart );

        return panel;
    }

    public void updateChart( List<Data> data ) {
        chart.getStore().clear();
        chart.getStore().addAll( data.subList( 0, MAX_PADDLE_ON_CHART ) );
        chart.redrawChart();
    }
}
