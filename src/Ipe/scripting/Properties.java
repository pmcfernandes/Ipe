// Properties.java

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

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import windows.prefs.INIFile;

/**
 *
 * @author Pedro
 */
public class Properties {

    private INIFile file;

    /**
     *
     */
    public Properties() {
        loadProperties();
    }

    /**
     *
     */
    private void loadProperties() {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("manifest.ini");

        try {
            file = new INIFile(stream);
        } catch (IOException ex) {
            Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param name
     * @return
     */
    public String getString(String name) {
        return file.getString("properties", name, "");
    }

    /**
     *
     * @param name
     * @return
     */
    public double getDouble(String name) {
        return file.getDouble("properties", name, (double) 0);
    }

    /**
     *
     * @param name
     * @return
     */
    public int getInt(String name) {
        return file.getInt("properties", name, 0);
    }

    /**
     *
     * @param name
     * @return
     */
    public Boolean hasProperty(String name) {
        String str = file.getString("properties", name, "");
        return str.isEmpty() != true;
    }
}
