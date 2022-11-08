package com.payload;

import com.pojo.address.SearchProduct_Input_Pojo;

public class ProductPayload {

	public SearchProduct_Input_Pojo searchProduct(String productName) {
		SearchProduct_Input_Pojo searchProduct_Input_Pojo = new SearchProduct_Input_Pojo(productName);
		return searchProduct_Input_Pojo;
	}

}
