package com.framework;


public interface Constants {

	static final String FILE_NAME = "Extent_Report.html";
	static final String TESTRESOURCES = System.getProperty("user.dir")+"/src/test/resources/";
	static final String OUTPUT_FOLDER = TESTRESOURCES+"/Reports/";
	static final String IMAGES_FOLDER = OUTPUT_FOLDER+"/Images/";
	
	static final String IMG_NAME = IMAGES_FOLDER + "screenshot_%d.png";
	
}
