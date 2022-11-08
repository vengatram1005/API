package com.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.payload.AddressPayload;
import com.pojo.address.CityList;
import com.pojo.address.GetCityCode_Input_pojo;
import com.pojo.address.GetCityCode_Output_Pojo;
import com.pojo.address.GetStateList_Output_Pojo;
import com.pojo.address.StateList;

import io.cucumber.java.en.*;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC2_GetStateCityCodeStep extends BaseClass {
	Response response;
	static int StateId;
	static int cityId;
	static AddressPayload addressPayload = new AddressPayload();

	@Given("User add headers for to StateList")
	public void userAddHeadersForToStateList() {

		addHeader("accept", "application/json");

	}

	@When("User send {string} request for StateList endpoint")
	public void userSendRequestForStateListEndpoint(String type) {
		response = requestType(type, Endpoints.STATELIST);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(statusCode);
	}

	@Then("User verify the stateList response message matches {string} and saved state id")
	public void userVerifyTheStateListResponseMessageMatchesAndSavedStateId(String expStateName) {

		GetStateList_Output_Pojo getStateList_Output_Pojo = response.as(GetStateList_Output_Pojo.class);
		ArrayList<StateList> stateListData = getStateList_Output_Pojo.getData();
		for (StateList eachStateDetails : stateListData) {
			if (eachStateDetails.getName().equals(expStateName)) {
				StateId = eachStateDetails.getId();
				System.out.println(StateId);
				TC1_LoginStep.globalDatas.setStateId(StateId);
				Assert.assertEquals("Verify state name", expStateName, eachStateDetails.getName());
				break;
			}

		}

	}

	@Given("User add header  for to get CityList")
	public void userAddHeaderForToGetCityList() {
		List<Header> h = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h3 = new Header("Content-Type", "application/json");
		h.add(h1);
		h.add(h3);

		Headers headers = new Headers(h);

		addHeaders(headers);

	}

	@When("User add request body state id for  get city list")
	public void userAddRequestBodyStateIdForGetCityList() {
		GetCityCode_Input_pojo getCityCode_Input_pojo = addressPayload.getCityCode(StateId);
		addBody(getCityCode_Input_pojo);

	}

	@When("User send {string} request for CityList endpoint")
	public void userSendRequestForCityListEndpoint(String type) {
		response = requestType(type, Endpoints.CITYLIST);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(statusCode);

	}

	@Then("User verify the cityList response message matches {string} and saved city id")
	public void userVerifyTheCityListResponseMessageMatchesAndSavedCityId(String expCityName) {
		GetCityCode_Output_Pojo getCityCode_Output_Pojo = response.as(GetCityCode_Output_Pojo.class);
		ArrayList<CityList> data = getCityCode_Output_Pojo.getData();

		for (CityList cityList : data) {
			if (cityList.getName().equals(expCityName)) {
				cityId = cityList.getId();
				System.out.println(cityId);
				TC1_LoginStep.globalDatas.setCityId(cityId);

				Assert.assertEquals("Verify City name", expCityName, cityList.getName());
				break;
			}

		}

	}

}
