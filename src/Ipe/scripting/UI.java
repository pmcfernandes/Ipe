// UI.java

// Copyright (C) 2013 Pedro Fernandes

// This program is free software; you can redistribute it and/or modify it under the terms of the GNU 
// General Public License as published by the Free Software Foundation; either version 2 of the 
// License, or (at your option) any later version.

// This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
// even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See 
// the GNU General Public License for more details. You should have received a copy of the GNU 
// General Public License along with this program; if not, write to the Free Software Foundation, Inc., 59 
// Temple Place, Suite 330, Boston, MA 02111-1307 USA
package Ipe.scripting;

import Ipe.browser.WebKit;
import java.net.URISyntaxException;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UI {

    // Private variables
    private Stage _stage;

    /**
     * Constructor for instance
     *
     * @param stage
     */
    public UI(Stage stage) {
        this._stage = stage;
    }

    public void createDialog(String url, String title, int width, int height) throws URISyntaxException {
        Stage stage = new Stage();
        Scene scene = new Scene(new WebKit(url, this._stage), width, height, Color.web("#666970"));

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this._stage);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
