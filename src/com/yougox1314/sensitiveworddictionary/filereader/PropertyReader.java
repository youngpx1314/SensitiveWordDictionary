package com.yougox1314.sensitiveworddictionary.filereader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.yougox1314.sensitiveworddictionary.Constant;

/**
 * @author youngpx1314
 *
 */
public class PropertyReader {
	public static String getProperty() throws IOException{
		Properties prop = new Properties();
		InputStream in = PropertyReader.class.getClassLoader().getResourceAsStream(Constant.PROPERTYPATH);
		BufferedReader bf = new BufferedReader(new InputStreamReader(in)); 
		prop.load(bf);
		return prop.getProperty(Constant.SENSTIVEWORDS);
	}
}
