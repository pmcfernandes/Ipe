// WebKit.java

// Copyright (C) 2013 Pedro Fernandes

// This program is free software; you can redistribute it and/or modify it under the terms of the GNU 
// General Public License as published by the Free Software Foundation; either version 2 of the 
// License, or (at your option) any later version.

// This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
// even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See 
// the GNU General Public License for more details. You should have received a copy of the GNU 
// General Public License along with this program; if not, write to the Free Software Foundation, Inc., 59 
// Temple Place, Suite 330, Boston, MA 02111-1307 USA

package Ipe.browser;

import java.net.URISyntaxException;

import Ipe.scripting.Scripting;
import Ipe.Ipe;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

/**
 *
 * @author Pedro
 */
public class WebKit extends Region {

    private final WebView browser = new WebView();
    private final WebEngine webEngine = browser.getEngine();

    /**
     *
     * @return
     */
    private ChangeListener<State> getChangeListener(final Stage stage) {
        return new ChangeListener<State>() {
            @Override
            public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
                JSObject win = (JSObject) webEngine.executeScript("window");
                win.setMember("app", new Scripting(stage));
            }
        };
    }

    /**
     *
     * @param url
     * @param stage
     * @throws URISyntaxException
     */
    public WebKit(String url, Stage stage) throws URISyntaxException {
        this.getChildren().add(browser);
        this.browser.setContextMenuEnabled(false);

        // Start WebEngine
        webEngine.setOnAlert(new EventHandler<WebEvent<String>>() {
            @Override
            public void handle(WebEvent<String> arg0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText(arg0.getData());

                alert.showAndWait();
            }
        });

        webEngine.getLoadWorker().stateProperty().addListener(getChangeListener(stage));
        webEngine.load(Ipe.server.getRootUri() + url);
    }

    /**
     *
     */
    @Override
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
    }

    /**
     *
     * @param height
     * @return
     */
    @Override
    protected double computePrefWidth(double height) {
        return 750;
    }

    /**
     *
     * @param width
     * @return
     */
    @Override
    protected double computePrefHeight(double width) {
        return 500;
    }
}
