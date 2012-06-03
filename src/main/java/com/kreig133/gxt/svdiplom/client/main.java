package com.kreig133.gxt.svdiplom.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class main implements EntryPoint {
    public void onModuleLoad() {
        SvMain w = new SvMain();
        RootPanel.get().add(w);
    }
}
