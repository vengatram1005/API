package com.reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.base.BaseClass;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class Reporting extends BaseClass {

	public static void generatesJvmReport(String jsonFile) throws FileNotFoundException, IOException {
		// 1. Mention the path of jvm report where to store?
		File file = new File(System.getProperty("user.dir") + getPropertyFileValue("jvmReportPath"));

		// 2. Create the Object for Configuration Class
		Configuration configuration = new Configuration(file, "OMR Branch API Automation");

		// 3. Mention the browser,version,os
		configuration.addClassifications("Env", "QA");
		configuration.addClassifications("Testing type", "Reg");
		configuration.addClassifications("Sprint", "34");

		// 4. Create the Object for Report Builder Class--->pass the json file to fetch
		// results
		List<String> jsonFiles = new ArrayList<String>();
		jsonFiles.add(jsonFile);

		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);

		// 5. Generates JVM Report
		reportBuilder.generateReports();

	}

}
