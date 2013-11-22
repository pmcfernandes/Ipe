// Ipe.java

// Copyright (C) 2013 Pedro Fernandes

// This program is free software; you can redistribute it and/or modify it under the terms of the GNU 
// General Public License as published by the Free Software Foundation; either version 2 of the 
// License, or (at your option) any later version.

// This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
// even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See 
// the GNU General Public License for more details. You should have received a copy of the GNU 
// General Public License along with this program; if not, write to the Free Software Foundation, Inc., 59 
// Temple Place, Suite 330, Boston, MA 02111-1307 USA

package Ipe;

import Ipe.browser.WebKit;
import java.net.URISyntaxException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Ipe.server.NativeHostServer;

public class Ipe extends Application {

    private Scene scene;
    private NativeHostServer server;

    /**
     *
     * @param primaryStage
     * @throws URISyntaxException
     */
    @Override
    public void start(Stage primaryStage) throws URISyntaxException {        
        // load manifest
        ManifestInfo manifestInfo = new ManifestInfo();

        // create server
        server = new NativeHostServer("/");

        // create the scene       
        scene = new Scene(new WebKit(server, primaryStage), 750, 500, Color.web("#666970"));
        primaryStage.setScene(scene);
        primaryStage.setTitle(manifestInfo.getTitle());

        if (!manifestInfo.getIcon().isEmpty()) {
            primaryStage.getIcons().add(new Image(this.getClass().getClassLoader().getResourceAsStream(manifestInfo.getIcon())));
        }
        
        primaryStage.show();
    }

    /**
     *
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        server.stop(); // stop server
        super.stop();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}