package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReaderUtil {
	
	private Properties props = null;
	
	public PropertyReaderUtil() {
		InputStream input = PropertyReaderUtil.class
        		.getClassLoader().getResourceAsStream("config.properties");
        props = new Properties(System.getProperties());
        try {
			props.load(input);
		} catch (IOException e) {
			System.out.println("Exception found in loading the properties");
		}
	}
	
	public static PropertyReaderUtil Singelton() {
        PropertyReaderUtil theInstance = null;
		synchronized (PropertyReaderUtil.class) {
            if (theInstance == null) {
                theInstance = new PropertyReaderUtil();
            }
        }
        return theInstance;
    }
	
	public static String getProperty(String propertyName) {
        String value = Singelton().props.getProperty(propertyName);
        if (value == null) {
            System.out.println("propertyName (" + propertyName + ") not found in property file config.properties");
        }

        return value;
    }
	
	/*
	 * String propValue = null; public String getProperty(String prop) {
	 * 
	 * Properties property = new Properties();
	 * 
	 * try (InputStream input = PropertyReaderUtil.class
	 * .getClassLoader().getResourceAsStream("config.properties")) {
	 * 
	 * if (input == null) {
	 * System.out.println("Sorry, unable to find config.properties"); return ""; }
	 * 
	 * // load a properties file property.load(input);
	 * 
	 * propValue = property.getProperty(prop);
	 * 
	 * } catch (FileNotFoundException e) {
	 * System.out.println("File Not Found Exception" + e.getMessage()); } catch
	 * (IOException e) { System.out.println("IO Exception" + e.getMessage()); }
	 * return propValue;
	 * 
	 * }
	 */
}
