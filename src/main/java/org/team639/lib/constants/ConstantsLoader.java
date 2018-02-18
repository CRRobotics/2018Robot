package org.team639.lib.constants;

import java.io.*;
import java.util.Properties;

public class ConstantsLoader {
    private Properties constants;

    public ConstantsLoader(String file) {
        constants = new Properties();
        FileInputStream is;
        try {
            is = new FileInputStream(file);
            constants.load(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getDouble(String key, double def) {
        try {
            return Double.parseDouble(constants.getProperty(key));
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public int getInt(String key, int def) {
        try {
            return Integer.parseInt(constants.getProperty(key));
        } catch (NumberFormatException e) {
            return def;
        }
    }
}
