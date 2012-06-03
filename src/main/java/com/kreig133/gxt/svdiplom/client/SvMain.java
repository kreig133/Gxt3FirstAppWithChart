package com.kreig133.gxt.svdiplom.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.kreig133.gxt.svdiplom.client.charts.LineExample;
import com.kreig133.gxt.svdiplom.client.model.Data;
import com.kreig133.gxt.svdiplom.client.model.DataProperties;
import com.kreig133.gxt.svdiplom.client.model.Generator;
import com.sencha.gxt.cell.core.client.NumberCell;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kreig133
 * @version 1.0
 */
public class SvMain extends Composite{

    interface SvMainWidgetUiBinder extends UiBinder<Widget, SvMain> { }

    private static SvMainWidgetUiBinder uiBinder = GWT.create( SvMainWidgetUiBinder.class );

    Double[] filteredTemp = new Double[ 80 ];

    @UiField(provided = true)
    ListStore<Data> store;
    @UiField(provided = true)
    ColumnModel<Data> cm;
    @UiField
    GridView view;
    @UiField
    Grid dataTable;
    @UiField
    LineExample chart;

    private final int MAX_COUNT = 80;;

    public SvMain() {
        store = new ListStore<Data>(new ModelKeyProvider<Data>() {
            @Override
            public String getKey( Data o ) {
                return o.toString();
            }
        } );
        DataProperties dataProperties = GWT.create( DataProperties.class );
        ColumnConfig<Data, Double> columnConfig = new ColumnConfig<Data, Double>( dataProperties.time(), 60,
                "Время" );
        columnConfig.setCell( new NumberCell<Double>( NumberFormat.getFormat( "#0.00" ) ) );

        List<ColumnConfig<Data, ?>> list = new ArrayList<ColumnConfig<Data, ?>>();
        list.add( columnConfig );

        for( int i = 0; i < MAX_COUNT; i ++ ) {
            list.add( new MeasureColumnConfig( i ) );
        }
        cm = new ColumnModel<Data>( list );
        initWidget( uiBinder.createAndBindUi( this ) );

        startEmulation( 950.0 );
    }

    private int count = 0;
    private void startEmulation( Double temp ) {
        count = 0;
        final Generator generator = new Generator( temp );
        for ( int i = 0; i < filteredTemp.length; i++ ) {
            filteredTemp[ i ] = Double.MAX_VALUE;
        }

        Timer timer = new Timer() {
            @Override
            public void run() {
                if ( count < 100 ) {
                    Data next = generator.next();
                    dataTable.getStore().add( next );
                    filterTemp( next );
                    chart.updateChart( convertForChart( next ) );
                } else {
                    cancel();
                }
                count++;
            }
        };
        timer.scheduleRepeating( 1000 );
    }

    private void filterTemp( Data next ) {
        for ( int i = 0; i < filteredTemp.length; i++ ) {
            if ( filteredTemp[ i ] > next.getMeasures().get( i ) ) {
                filteredTemp[ i ] = next.getMeasures().get( i );
            }
        }
    }

    private List<com.kreig133.gxt.svdiplom.client.charts.Data> convertForChart( Data data ) {
        List<com.kreig133.gxt.svdiplom.client.charts.Data> datas =
                new ArrayList<com.kreig133.gxt.svdiplom.client.charts.Data>(MAX_COUNT);
        for ( int i = 0; i < MAX_COUNT; i++ ) {
            datas.add( new com.kreig133.gxt.svdiplom.client.charts.Data(
                    Integer.toString( i ) ,
                    data.getMeasures().get( i ),
                    filteredTemp[ i ]
            ) );
        }
        return datas;
    }
    class MeasureColumnConfig extends ColumnConfig<Data, Double>{
        public MeasureColumnConfig( final int index ) {
            super( new ValueProvider<Data, Double>() {
                @Override
                public Double getValue( Data object ) {
                    return object.getMeasures().get( index );
                }

                @Override
                public void setValue( Data object, Double value ) {
                    Double aDouble = object.getMeasures().get( index );
                    aDouble = value;
                }

                @Override
                public String getPath() {
                    return null;
                }
            }, 55, "Лоп." + index  );
            setCell( new NumberCell<Double>( NumberFormat.getFormat( "#.00" ) ) );
        }
    }

}