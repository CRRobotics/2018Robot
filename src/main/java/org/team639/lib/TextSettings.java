package org.team639.lib;

import org.team639.robot.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;


public class TextSettings {

    private static Properties prop = new Properties();
    private static HashMap<String, String> hm;
    static{
        try {
            prop.load(new FileInputStream("config.properties"));
            prop.store(new FileOutputStream("config.properties"), "all keys and values must be exact");
        } catch(IOException e){ e.printStackTrace(); }
        hm = new HashMap<>();
    }

    /**
     * @param key the key to the new save in hashmap hm
     * @param value the value you want to be assigned to the specified key
     */
    protected static void put(String key, String value){ hm.put(key, value);}

    /**
     * this method writes an example into the constants file
     */
    public static void giveExample(){ prop.setProperty("ExampleKey","ExampleValue"); }

    /**
     * this method sets all everything to default in the constants file
     */
    public static void setDefaults(){
        for(String s : getKeys())
            prop.setProperty(s,hm.get(s));
    }

    /**
     * an number accessor for values in the config file
     * @param key
     * @return keys value
     */
    public static Double getDouble(String key) {
        Double num = null;

        if(!prop.containsKey(key)){
            if(hm.containsKey(key))
                num = Double.parseDouble(hm.get(key));
        }
        else
            num = Double.parseDouble(prop.getProperty(key));

        return num;
    }

    /**
     * an number accessor for values in the config file
     * @param key
     * @param def value
     * @return keys value
     */
    public static Double getDouble(String key, double def) {
        if(getDouble(key) != null)
            return getDouble(key);
        return def;
    }

    /**
     * an string accessor for values in the config file
     * @param key
     * @return keys value
     */
    public static String getString(String key) {
        String word = null;

        if (!prop.containsKey(key)) {
            if (hm.containsKey(key))
                word = hm.get(key);
        } else
            word = prop.getProperty(key);

        return word;
    }
    /**
     * a string accessor for values in the config file
     * @param key
     * @param def value
     * @return keys value
     */
    public static String getString(String key, String def) {
        if(getString(key) != null)
            return getString(key);
        return def;
    }

    public static ArrayList<String> getKeys() {
        ArrayList<String> keys = new ArrayList<>();
        for(String s : keys)
            keys.add(s);
        return keys;
    }
}
