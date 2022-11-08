package com.stepdefinition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.address.ChangeProfilePic_Output_Pojo;

import io.cucumber.java.en.*;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC5_ChangeProfilePicStep extends BaseClass {
	Response response;

	@Given("User add Headers and Bearer authorization for form data")
	public void userAddHeadersAndBearerAuthorizationForFormData() {
		List<Header> h = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globalDatas.getLogtoken());
		Header h3 = new Header("Content-Type", "multipart/form-data");
		h.add(h1);
		h.add(h2);
		h.add(h3);

		Headers headers = new Headers(h);

		addHeaders(headers);

	}

	@When("User add request body for Change Profile Pic")
	public void userAddRequestBodyForChangeProfilePic() throws FileNotFoundException, IOException {
		formData("profile_picture", new File(System.getProperty("user.dir") + getPropertyFileValue("imagePath")));
	}

	@When("User Send {string} request for ChangeprofilePic endpoint")
	public void userSendRequestForChangeprofilePicEndpoint(String type) {
		response = requestType(type, Endpoints.CHANGEPROFILEPIC);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(statusCode);

	}

	@Then("User verify the Change Profile pic response message matches {string}")
	public void userVerifyTheChangeProfilePicResponseMessageMatches(String expMsg) {
		ChangeProfilePic_Output_Pojo changeProfilePic_Output_Pojo = response.as(ChangeProfilePic_Output_Pojo.class);
		String message = changeProfilePic_Output_Pojo.getMessage();

		Assert.assertEquals("Verify Change profile Pic", expMsg, message);

	}

}
