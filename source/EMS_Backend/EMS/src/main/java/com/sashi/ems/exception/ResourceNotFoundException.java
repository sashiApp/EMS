package com.sashi.ems.exception;

public class ResourceNotFoundException extends Exception{
	
	@Override
	public String getMessage() {
		return "Requested resource not found.";
		
	}

}
