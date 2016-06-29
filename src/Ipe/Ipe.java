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
import Ipe.server.NativeHostServer;
import java.io.InputStream;
import java.net.URISyntaxException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Ipe extends Application {

    private Scene scene;
    public static NativeHostServer server;

    private void loadServer() throws URISyntaxException {
        // create server
        Ipe.server = new NativeHostServer("/");
    }

    private void loadManifest(Stage stage) {
        // load manifest
        ManifestInfo manifestInfo = new ManifestInfo();

        stage.setTitle(manifestInfo.getTitle());
        if (!manifestInfo.getIcon().isEmpty()) {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(manifestInfo.getIcon());
            if (inputStream != null) {
                stage.getIcons().add(new Image(inputStream));
            }
        }
    }

    /**
     *
     * @param primaryStage
     * @throws URISyntaxException
     */
    @Override
    public void start(Stage primaryStage) throws URISyntaxException, Exception {
        if (primaryStage == null) {
            throw new Exception("primaryStage is null or empty.");
        }

        this.loadServer();
        this.loadManifest(primaryStage);

        // create the scene       
        scene = new Scene(new WebKit("index.html", primaryStage), 750, 500, Color.web("#fff"));
        if (scene == null) {
            return;
        }
        
        primaryStage.setScene(scene);
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