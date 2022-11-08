package com.runner;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.base.BaseClass;
import com.reports.Reporting;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(tags = "", dryRun = false, publish = true, snippets = SnippetType.CAMELCASE, stepNotifications = true, monochrome = true, plugin = {
		"pretty", "json:target/output.json" }, glue = "com.stepdefinition", features = "src\\test\\resources")

public class TestRunnerClass extends BaseClass {
	@AfterClass
	public static void afterClass() throws FileNotFoundException, IOException {
		Reporting.generatesJvmReport(System.getProperty("user.dir") + getPropertyFileValue("jsonPath"));

	}

}
