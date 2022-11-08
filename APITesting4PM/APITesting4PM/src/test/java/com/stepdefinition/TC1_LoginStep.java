package com.stepdefinition;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.global.GlobalDatas;
import com.pojo.address.Login_Output_Pojo;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

public class TC1_LoginStep extends BaseClass {
	static Response response;
	static GlobalDatas globalDatas = new GlobalDatas();

	@Given("User add header")
	public void user_add_header() {
		addHeader("accept", "application/json");

	}

	@When("User add basic authentication for login")
	public void user_add_basic_authentication_for_login() throws FileNotFoundException, IOException {
		basicAuth(getPropertyFileValue("userName"), getPropertyFileValue("password"));

	}

	@When("User send {string} request for login endpoint")
	public void user_send_request_for_login_endpoint(String type) {
		response = requestType(type, Endpoints.POSTMANBASICAUTHLOGIN);

		int statusCode = getStatusCode(response);
		globalDatas.setStatusCode(statusCode);

	}

	@Then("User verify the login response body firstName present as {string} and get the logtoken saved")
	public void user_verify_the_login_response_body_first_name_present_as_and_get_the_logtoken_saved(
			String expFirstName) {
		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);

		String first_name = login_Output_Pojo.getData().getFirst_name();

		HookClass.sc.log(
				"Verify firstName in login endpoint " + "exp Value: " + expFirstName + " act value: " + first_name);

		Assert.assertEquals("Verify firstName in login endpoint", expFirstName, first_name);

		String logtoken = login_Output_Pojo.getData().getLogtoken();
		globalDatas.setLogtoken(logtoken);

	}

}
