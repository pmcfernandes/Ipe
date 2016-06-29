// Scripting.java

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

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro
 */
public class Scripting {

    // Private variables
    private Properties props = null;    
   
    // Public variables
    public Media Media;
    public UI UI;
    public Clipboard Clipboard;
    
    /**
     * Private variables
     */
    private Stage _stage;

    /**
     *
     * @param stage
     */
    public Scripting(Stage stage) {
        this._stage = stage;
        
        // Instance a new objects to comunicate between web and desktop
        this.Clipboard = new Clipboard(this._stage);
        this.Media = new Media(this._stage);
        this.UI = new UI(this._stage);
    }

    /**
     * Get properties of application
     * 
     * @return 
     */
    public Properties getProperties() {
        if (props == null) {
            props = new Properties();
        }

        return props;
    }

    /**
     *
     * @param msg
     */
    public void error(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.showAndWait();
    }

    /**
     *
     * @param msg
     */
    public void info(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.showAndWait();
    }

    /**
     *
     * @param msg
     */
    public void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.showAndWait();
    }

    /**
     * Quit command
     */
    public void quit() {
        Platform.exit();
    }
}
