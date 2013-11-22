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

import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro
 */
public class Scripting {

    /**
     * Public variables
     */
    public Properties Properties = new Properties();
    public Media Media;

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
        this.Media = new Media(this._stage);
    }

    /**
     *
     * @param msg
     */
    public void error(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * 
     * @param msg 
     */
    public void info(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * 
     * @param msg 
     */
    public void alert(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.DEFAULT_OPTION);
    }

    /**
     *
     */
    public void quit() {
        this._stage.close();
    }
}
