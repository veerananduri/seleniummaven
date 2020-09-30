package com.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReaderUtil {
	
	String propValue = null;
	public String getProperty(String prop) {
		
		Properties property = new Properties();
		
        try (InputStream input = PropertyReaderUtil.class
        		.getClassLoader().getResourceAsStream("config.properties")) {
    		
        	if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return "";
            }
    		
        	// load a properties file
        	property.load(input);    
        	
        	propValue = property.getProperty(prop);
        	
        } catch (FileNotFoundException e) {
			System.out.println("File Not Found Exception" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO Exception" + e.getMessage());
		}
		return propValue; 

	}
}
