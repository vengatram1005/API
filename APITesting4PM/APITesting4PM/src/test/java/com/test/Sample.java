package com.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pojo.address.AddUserAddress_Input_Pojo;
import com.pojo.address.AddUserAddress_output_Pojo;
import com.pojo.address.ChangeProfilePic_Output_Pojo;
import com.pojo.address.DeleteUserAddress_Input_Pojo;
import com.pojo.address.DeleteUserAddress_Output_Pojo;
import com.pojo.address.GetUserAddresses_Output_Pojo;
import com.pojo.address.Login_Output_Pojo;
import com.pojo.address.SearchProduct_Input_Pojo;
import com.pojo.address.SearchProduct_Output_Pojo;
import com.pojo.address.UpdateUserAddress_Input_Pojo;
import com.pojo.address.UpdateUserAddress_Output_pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Sample extends BaseClass {
	String logtoken;
	String address_id;

	@Test(priority = 7)
	public void searchProduct() {
		// 1. Headers
		List<Header> h = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h3 = new Header("Content-Type", "application/json");
		h.add(h1);
		h.add(h3);

		Headers headers = new Headers(h);

		addHeaders(headers);

		// 2. payload
		SearchProduct_Input_Pojo searchProduct_Input_Pojo = new SearchProduct_Input_Pojo("nuts");
		addBody(searchProduct_Input_Pojo);

		// 3. req type
		Response response = requestType("POST", "https://omrbranch.com/api/searchProduct");

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "Verify status Code");

		SearchProduct_Output_Pojo searchProduct_Output_Pojo = response.as(SearchProduct_Output_Pojo.class);
		String message = searchProduct_Output_Pojo.getMessage();

		Assert.assertEquals(message, "OK", "Verify OK");

	}

	@Test(priority = 6)
	public void changeProfilePic() {
		// 1. Headers
		List<Header> h = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "multipart/form-data");
		h.add(h1);
		h.add(h2);
		h.add(h3);

		Headers headers = new Headers(h);

		addHeaders(headers);

		// 2. Payload -->form Data
		formData("profile_picture", new File(System.getProperty("user.dir") + "\\images\\pic.jpg"));

		// 3. req type
		Response response = requestType("POST", "https://omrbranch.com/api/changeProfilePic");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "Verify status Code");

		ChangeProfilePic_Output_Pojo changeProfilePic_Output_Pojo = response.as(ChangeProfilePic_Output_Pojo.class);
		String message = changeProfilePic_Output_Pojo.getMessage();
		Assert.assertEquals(message, "Profile updated Successfully", "Verify Profile updated Successfully");
	}

	@Test(priority = 5)
	public void getAddresses() {
		// 1. Headers

		List<Header> h = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		h.add(h1);
		h.add(h2);

		Headers headers = new Headers(h);

		addHeaders(headers);

		// 2. Method type
		Response response = requestType("GET", "https://omrbranch.com/api/getUserAddress");

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "Verify status Code");

		String resBodyAsPrettyString = getResBodyAsPrettyString(response);
		System.out.println(resBodyAsPrettyString);

		GetUserAddresses_Output_Pojo getUserAddresses_Output_Pojo = response.as(GetUserAddresses_Output_Pojo.class);

		String message = getUserAddresses_Output_Pojo.getMessage();

		Assert.assertEquals(message, "OK", "Verify OK");

	}

	@Test(priority = 4)
	public void deleteUserAddress() {
		// 1. Headers

		List<Header> h = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		h.add(h1);
		h.add(h2);
		h.add(h3);

		Headers headers = new Headers(h);

		addHeaders(headers);

		// 2. payload

		DeleteUserAddress_Input_Pojo deleteUserAddress_Input_Pojo = new DeleteUserAddress_Input_Pojo(address_id);
		addBody(deleteUserAddress_Input_Pojo);

		// 3. method type
		Response response = requestType("DELETE", "https://omrbranch.com/api/deleteAddress");

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);

		Assert.assertEquals(statusCode, 200, "Verify status Code");

		DeleteUserAddress_Output_Pojo deleteUserAddress_Output_Pojo = response.as(DeleteUserAddress_Output_Pojo.class);

		String message = deleteUserAddress_Output_Pojo.getMessage();

		Assert.assertEquals(message, "Address deleted successfully", "Verify Address deleted successfully");

	}

	@Test(priority = 3)
	public void updateUserAddress() {
		// 1. Headers

		List<Header> h = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		h.add(h1);
		h.add(h2);
		h.add(h3);

		Headers headers = new Headers(h);

		addHeaders(headers);

		// 2. Payload
		UpdateUserAddress_Input_Pojo updateUserAddress_Input_Pojo = new UpdateUserAddress_Input_Pojo(address_id, "Ram",
				"J", "9876543212", "AK", 10, 3, 103, "600097", "Thoraipakkam", "Home");
		addBody(updateUserAddress_Input_Pojo);

		// 3. req type
		Response response = requestType("PUT", "https://omrbranch.com/api/updateUserAddress");

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);

		Assert.assertEquals(statusCode, 200, "Verify status Code");

		UpdateUserAddress_Output_pojo updateUserAddress_Output_pojo = response.as(UpdateUserAddress_Output_pojo.class);

		String message = updateUserAddress_Output_pojo.getMessage();

		Assert.assertEquals(message, "Address updated successfully", "Verify Address updated successfully");
	}

	@Test(priority = 2)
	public void addUserAddress() {
//1. Headers

		List<Header> h = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		h.add(h1);
		h.add(h2);
		h.add(h3);

		Headers headers = new Headers(h);

		addHeaders(headers);

		// 2. Add payload
		AddUserAddress_Input_Pojo address_Input_Pojo = new AddUserAddress_Input_Pojo("Ram", "J", "9876543212", "AK", 10,
				3, 103, "600097", "Thoraipakkam", "Home");

		addBody(address_Input_Pojo);

		// 3. req type
		Response response = requestType("POST", "https://omrbranch.com/api/addUserAddress");

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);

		Assert.assertEquals(statusCode, 200, "Verify status Code");

		AddUserAddress_output_Pojo addUserAddress_output_Pojo = response.as(AddUserAddress_output_Pojo.class);
		String message = addUserAddress_output_Pojo.getMessage();

		Assert.assertEquals(message, "Address added successfully", "Verify Address added successfully");

		int id = addUserAddress_output_Pojo.getAddress_id();
		address_id = String.valueOf(id);

	}

	@Test(priority = 1)
	public void login() throws ParseException {
		// 1. add Header
		addHeader("accept", "application/json");

		// 2. basic auth
		basicAuth("greenstechchennaiomr@gmail.com", "Api2learn@");

		// 3. method type
		Response response = requestType("POST", "https://omrbranch.com/api/postmanBasicAuthLogin");

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "Verify status Code");

		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);

		String first_name = login_Output_Pojo.getData().getFirst_name();
		Assert.assertEquals(first_name, "Greens", "Verify first name");

		logtoken = login_Output_Pojo.getData().getLogtoken();

	}

}
