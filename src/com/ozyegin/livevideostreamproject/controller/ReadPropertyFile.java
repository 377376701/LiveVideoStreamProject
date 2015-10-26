package com.ozyegin.livevideostreamproject.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {

	public String ReadProperty(String key,String fileName) {

		File file = new File(fileName);
		Properties prop = new Properties();
		FileInputStream fileInput = null;

		try {
			fileInput = new FileInputStream(file);
			prop.load(fileInput);
			
			return prop.getProperty(key);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (fileInput != null) {
				try {
					fileInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
