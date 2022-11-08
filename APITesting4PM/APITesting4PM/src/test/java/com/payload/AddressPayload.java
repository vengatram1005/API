package com.payload;

import com.pojo.address.AddUserAddress_Input_Pojo;
import com.pojo.address.DeleteUserAddress_Input_Pojo;
import com.pojo.address.GetCityCode_Input_pojo;
import com.pojo.address.UpdateUserAddress_Input_Pojo;

public class AddressPayload {

	public AddUserAddress_Input_Pojo addUserAddress(String first_name, String last_name, String mobile,
			String apartment, int state, int city, int country, String zipcode, String address, String address_type) {
		AddUserAddress_Input_Pojo address_Input_Pojo = new AddUserAddress_Input_Pojo(first_name, last_name, mobile,
				apartment, state, city, country, zipcode, address, address_type);
		return address_Input_Pojo;

	}

	public UpdateUserAddress_Input_Pojo updateUserAddress(String address_id, String first_name, String last_name,
			String mobile, String apartment, int state, int city, int country, String zipcode, String address,
			String address_type) {
		UpdateUserAddress_Input_Pojo updateUserAddress_Input_Pojo = new UpdateUserAddress_Input_Pojo(address_id,
				first_name, last_name, mobile, apartment, state, city, country, zipcode, address, address_type);
		return updateUserAddress_Input_Pojo;
	}

	public DeleteUserAddress_Input_Pojo deleteUserAddress(String address_id) {
		DeleteUserAddress_Input_Pojo deleteUserAddress_Input_Pojo = new DeleteUserAddress_Input_Pojo(address_id);
		return deleteUserAddress_Input_Pojo;

	}

	public GetCityCode_Input_pojo getCityCode(int stateId) {
		GetCityCode_Input_pojo getCityCode_Input_pojo = new GetCityCode_Input_pojo(stateId);
		return getCityCode_Input_pojo;
	}

}
