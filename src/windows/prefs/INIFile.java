// INIFile.java

// Copyright (C) 2013 Pedro Fernandes

// This program is free software; you can redistribute it and/or modify it under the terms of the GNU 
// General Public License as published by the Free Software Foundation; either version 2 of the 
// License, or (at your option) any later version.

// This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
// even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See 
// the GNU General Public License for more details. You should have received a copy of the GNU 
// General Public License along with this program; if not, write to the Free Software Foundation, Inc., 59 
// Temple Place, Suite 330, Boston, MA 02111-1307 USA

package windows.prefs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author PFernandes
 */
public final class INIFile {

    private final Pattern _section = Pattern.compile("\\s*\\[([^]]*)\\]\\s*");
    private final Pattern _keyValue = Pattern.compile("\\s*([^=]*)=(.*)");
    private final Map< String, Map< String, String>> _entries = new HashMap<>();

    /**
     * Constructor for instance
     * 
     * @param stream
     * @throws IOException
     */
    public INIFile(InputStream stream) throws IOException {
        load(stream);
    }

    /**
     * Load INI file into memory
     * 
     * @param stream
     * @throws IOException
     */
    public void load(InputStream stream) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            String section = null;
            while ((line = br.readLine()) != null) {
                Matcher m = _section.matcher(line);
                if (m.matches()) {
                    section = m.group(1).trim();
                } else if (section != null) {
                    m = _keyValue.matcher(line);
                    if (m.matches()) {
                        String key = m.group(1).trim();
                        String value = m.group(2).trim();
                        Map<String, String> kv = _entries.get(section);
                        
                        if (kv == null) {
                            _entries.put(section, kv = new HashMap<>());
                        }
                        kv.put(key, value);
                    }
                }
            }
        }
    }

    /**
     * Get string value
     * 
     * @param section
     * @param key
     * @param defaultValue
     * @return
     */
    public String getString(String section, String key, String defaultValue) {
        Map< String, String> kv = _entries.get(section);
        if (kv == null) {
            return defaultValue;
        } else {
            return kv.get(key);
        }
    }

    /**
     * Get integer value
     * 
     * @param section
     * @param key
     * @param defaultValue
     * @return
     */
    public int getInt(String section, String key, int defaultValue) {
        Map< String, String> kv = _entries.get(section);
        if (kv == null) {
            return defaultValue;
        } else {
            return Integer.parseInt(kv.get(key));
        }
    }

    /**
     * Get float value
     * 
     * @param section
     * @param key
     * @param defaultValue
     * @return
     */
    public float getFloat(String section, String key, float defaultValue) {
        Map< String, String> kv = _entries.get(section);
        if (kv == null) {
            return defaultValue;
        } else {
            return Float.parseFloat(kv.get(key));
        }
    }

    /**
     * Get double value
     * 
     * @param section
     * @param key
     * @param defaultValue
     * @return
     */
    public double getDouble(String section, String key, double defaultValue) {
        Map<String, String> kv = _entries.get(section);
        if (kv == null) {
            return defaultValue;
        } else {
            return Double.parseDouble(kv.get(key));
        }
    }
}