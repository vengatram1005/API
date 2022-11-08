package com.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.payload.ProductPayload;
import com.pojo.address.SearchProduct_Input_Pojo;
import com.pojo.address.SearchProduct_Output_Pojo;

import io.cucumber.java.en.*;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC4_SearchProductStep extends BaseClass {
	static ProductPayload productPayload = new ProductPayload();
	Response response;

	@Given("User add Headers for Search products")
	public void userAddHeadersForSearchProducts() {
		List<Header> h = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h3 = new Header("Content-Type", "application/json");
		h.add(h1);
		h.add(h3);

		Headers headers = new Headers(h);

		addHeaders(headers);
	}

	@When("User add request body for Search product {string}")
	public void userAddRequestBodyForSearchProduct(String productName) {
		SearchProduct_Input_Pojo searchProduct_Input_Pojo = productPayload.searchProduct(productName);
		addBody(searchProduct_Input_Pojo);
	}

	@When("User Send {string} request for Search product endpoint")
	public void userSendRequestForSearchProductEndpoint(String type) {
		response = requestType(type, Endpoints.SEARCHPRODUCT);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(statusCode);

	}

	@Then("User verify the search product response message matches {string}")
	public void userVerifyTheSearchProductResponseMessageMatches(String exp) {
		SearchProduct_Output_Pojo searchProduct_Output_Pojo = response.as(SearchProduct_Output_Pojo.class);
		String message = searchProduct_Output_Pojo.getMessage();
		Assert.assertEquals("Verify search  product", exp, message);

	}

}
