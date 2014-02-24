// Clipboard.java

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

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javafx.stage.Stage;

public class Clipboard {

    // Private variables
    private java.awt.datatransfer.Clipboard c;
    private Stage _stage;
    
    /**
     * Constructor for instance
     */
    public Clipboard(Stage stage) {
        this._stage = stage;    
        c = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    /**
     * Set text to clipboard
     * 
     * @param text 
     */
    public void setText(String text) {
        StringSelection selection = new StringSelection(text);
        c.setContents(selection, selection);
    }

    /**
     * Get text from clipboard
     * 
     * @return 
     */
    public Object getText() {
        try {
            return c.getContents(null).getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
            return "";
        }
    }
}
