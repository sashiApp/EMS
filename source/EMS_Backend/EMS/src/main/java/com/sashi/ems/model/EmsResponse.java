package com.sashi.ems.model;

/**
 * REST API return type to return generic API response
 * Generic response contains business data and status of the call.
 * @author sashi
 *
 */
public class EmsResponse<T extends Object> {
	//Business data sent to the service receiver.
	private T data;
	
	//Generic status object to propagate API call status.
	private EmsStatus emsStatus;

	

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public EmsStatus getEmsStatus() {
		return emsStatus;
	}

	public void setEmsStatus(EmsStatus emsStatus) {
		this.emsStatus = emsStatus;
	}
	
	
	

}
