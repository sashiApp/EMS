package com.sashi.ems.model;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	// Static list to hold companies  with access tokens.
	public static List<String> COMPANIES = new ArrayList<String>();
	static {
		for(int i =0; i<100;i++) {
			COMPANIES.add(String.valueOf((i+1)+Math.random()));
		}
	}
	
	/*
	* Get company ID from user ID.
	* User ID is AdminX where X is the company ID 
	* @param String 
	* @return int company ID.
	*/
	public static int getCompanyID(String userID){
		return Integer.valueOf(userID.substring("admin".length(), userID.length()));
	}
	
	/*
	* Get access token from company map for a given company ID. 
	* X.RandomNumber is the format where X is the company ID.
	* @param int companyID 
	* @return String access token.
	*/
	public static String getAccessToken(int companyID) {
		return COMPANIES.get(companyID);
	}
	
	/*
	* Check against company ID and access token to verify the authenticity. 
	* @param int companyID , String access toekn
	* @return boolean validity.
	*/
	public static boolean isValidToken(int companyID, String token) {
		return getAccessToken(companyID-1).equals(token);
	}
}
