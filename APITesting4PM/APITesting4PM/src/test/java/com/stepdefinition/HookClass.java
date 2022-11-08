package com.stepdefinition;

import com.base.BaseClass;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class HookClass extends BaseClass {
	static Scenario sc;

	@Before
	public void beforeScenario(Scenario scenario) {
		sc = scenario;

	}

	@After
	public void afterScenario(Scenario scenario) {
		String resBodyAsPrettyString = getResBodyAsPrettyString(response);
		scenario.log(resBodyAsPrettyString);

	}

}
