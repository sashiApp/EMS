package com.sashi.ems.model;

import java.util.List;

/**
 * Status class to hold the API call status including errors and warnings.
 * @author sashi
 *
 */
public class EmsStatus {
	
	private String status;
	private int code;
	private String message;
	private List<String> details;
	
	public EmsStatus() {
		super();
	}
	public EmsStatus(String status, int code, String message, List<String> details) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
		this.details = details;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getDetails() {
		return details;
	}
	public void setDetails(List<String> details) {
		this.details = details;
	}
	
	public static EmsStatus ok() {
		return new EmsStatus("OK",200,null,null);
	}
	public static EmsStatus forbidden() {
		return new EmsStatus("FORBIDDEN",403,null,null);
	}
	public static EmsStatus unauthorized() {
		return new EmsStatus("USER_UNAUTHORIZED",401,null,null);
	}
	public static EmsStatus notFound() {
		return new EmsStatus("RESOURCE_NOT_FOUND",404,null,null);
	}
	public static EmsStatus exception(Exception e) {
		return new EmsStatus("INTERNAL_SERVER",500,e.getMessage(),null);
	}

}
