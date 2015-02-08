/**
 * 
 */
package com.yougox1314.sensitiveworddictionary.main;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.yougox1314.sensitiveworddictionary.dictionary.Dictionary;
import com.yougox1314.sensitiveworddictionary.filereader.PropertyReader;


/**
 * @author youngpx1314
 *
 */
public class BootStrap {
	private static final Logger log = Logger.getLogger(BootStrap.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dictionary dictionray = Dictionary.getInstance();
		if(args!=null&&args.length>0){
			dictionray.init(args);
			return;
		}
		try {
			String senstiveWords = PropertyReader.getProperty();
			if(senstiveWords==null){
				senstiveWords = "";
			}
			dictionray.init(senstiveWords.split(","));
			log.debug("敏感词字典创建完成");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
