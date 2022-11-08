package com.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.payload.AddressPayload;
import com.pojo.address.AddUserAddress_Input_Pojo;
import com.pojo.address.AddUserAddress_output_Pojo;
import com.pojo.address.DeleteUserAddress_Input_Pojo;
import com.pojo.address.DeleteUserAddress_Output_Pojo;
import com.pojo.address.GetUserAddresses_Output_Pojo;
import com.pojo.address.UpdateUserAddress_Input_Pojo;
import com.pojo.address.UpdateUserAddress_Output_pojo;

import io.cucumber.java.en.*;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC3_AddressStep extends BaseClass {
	static String address_id;
	static String logtoken;
	Response response;
	static AddressPayload payload = new AddressPayload();

	@Given("User add header and bearer authorization for accessing address endpoints")
	public void user_add_header_and_bearer_authorization_for_accessing_address_endpoints() {
		logtoken = TC1_LoginStep.globalDatas.getLogtoken();

		List<Header> h = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		h.add(h1);
		h.add(h2);
		h.add(h3);

		Headers headers = new Headers(h);

		addHeaders(headers);

	}

	@When("User add request body for add new address {string},{string},{string},{string},{string},{string},{string},{string},{string},and {string}")
	public void user_add_request_body_for_add_new_address_and(String first_name, String last_name, String mobile,
			String apartment, String state, String city, String country, String zipcode, String address,
			String address_type) {
//		int stateNum = Integer.parseInt(state);
//		int cityNum = Integer.parseInt(city);
		int stateNum = TC1_LoginStep.globalDatas.getStateId();
		int cityNum = TC1_LoginStep.globalDatas.getCityId();

		int countryNum = Integer.parseInt(country);
		AddUserAddress_Input_Pojo addUserAddress = payload.addUserAddress(first_name, last_name, mobile, apartment,
				stateNum, cityNum, countryNum, zipcode, address, address_type);

		addBody(addUserAddress);

	}

	@When("User send {string} request for addUserAddress endpoint")
	public void user_send_request_for_add_user_address_endpoint(String type) {
		response = requestType(type, Endpoints.ADDUSERADDRESS);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(statusCode);
	}

	@Then("User verify the addUserAddress response message matches {string}")
	public void user_verify_the_add_user_address_response_message_matches(String expAddAddressMsg) {
		AddUserAddress_output_Pojo addUserAddress_output_Pojo = response.as(AddUserAddress_output_Pojo.class);
		String message = addUserAddress_output_Pojo.getMessage();
		int id = addUserAddress_output_Pojo.getAddress_id();
		address_id = String.valueOf(id);
		Assert.assertEquals("Verify add address", expAddAddressMsg, message);
	}

	@When("User add request body to update new address {string},{string},{string},{string},{string},{string},{string},{string},{string} and {string}")
	public void user_add_request_body_to_update_new_address_and(String first_name, String last_name, String mobile,
			String apartment, String state, String city, String country, String zipcode, String address,
			String address_type) {
//		int stateNum = Integer.parseInt(state);
//		int cityNum = Integer.parseInt(city);

		int stateNum = TC1_LoginStep.globalDatas.getStateId();
		int cityNum = TC1_LoginStep.globalDatas.getCityId();
		int countryNum = Integer.parseInt(country);

		UpdateUserAddress_Input_Pojo updateUserAddress = payload.updateUserAddress(address_id, first_name, last_name,
				mobile, apartment, stateNum, cityNum, countryNum, zipcode, address, address_type);
		addBody(updateUserAddress);

	}

	@When("User send {string} request for update addUserAddress endpoint")
	public void user_send_request_for_update_add_user_address_endpoint(String type) {
		response = requestType(type, Endpoints.UPDATEUSERADDRESS);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(statusCode);

	}

	@Then("User verify the update address response message matches {string}")
	public void user_verify_the_update_address_response_message_matches(String expUpdateAddressMsg) {
		UpdateUserAddress_Output_pojo updateUserAddress_Output_pojo = response.as(UpdateUserAddress_Output_pojo.class);
		String message = updateUserAddress_Output_pojo.getMessage();
		Assert.assertEquals("Verify update address", expUpdateAddressMsg, message);
	}

	@Given("User add Headers and Bearer authorization for accessing Get address endpoints")
	public void user_add_headers_and_bearer_authorization_for_accessing_get_address_endpoints() {
		List<Header> h = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		h.add(h1);
		h.add(h2);

		Headers headers = new Headers(h);

		addHeaders(headers);

	}

	@When("User Send {string} request for GetUserAddress endpoint")
	public void user_send_request_for_get_user_address_endpoint(String type) {
		response = requestType(type, Endpoints.GETUSERADDRESS);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(statusCode);

	}

	@Then("User verify the GetUserAddress response message matches {string}")
	public void user_verify_the_get_user_address_response_message_matches(String expGetAddressMsg) {

		GetUserAddresses_Output_Pojo getUserAddresses_Output_Pojo = response.as(GetUserAddresses_Output_Pojo.class);
		String message = getUserAddresses_Output_Pojo.getMessage();
		Assert.assertEquals("Verify get addresses", expGetAddressMsg, message);

	}

	@When("User add request body for address id")
	public void user_add_request_body_for_address_id() {
		DeleteUserAddress_Input_Pojo deleteUserAddress = payload.deleteUserAddress(address_id);
		addBody(deleteUserAddress);

	}

	@When("User Send {string} request for DeleteAddress endpoint")
	public void user_send_request_for_delete_address_endpoint(String type) {
		response = requestType(type, Endpoints.DELETEUSERADDRESS);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(statusCode);
	}

	@Then("User verify the DeleteAddress response message matches {string}")
	public void user_verify_the_delete_address_response_message_matches(String expDeleteAddressMsg) {
		DeleteUserAddress_Output_Pojo deleteUserAddress_Output_Pojo = response.as(DeleteUserAddress_Output_Pojo.class);
		String message = deleteUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals("Verify delete address", expDeleteAddressMsg, message);
	}

}
