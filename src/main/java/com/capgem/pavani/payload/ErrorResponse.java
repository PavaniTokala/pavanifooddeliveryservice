package com.capgem.pavani.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
 
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
	private String message;
	private boolean success;
	public ErrorResponse(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	@Override
	public String toString() {
		return "ErrorResponse [message=" + message + ", success=" + success + "]";
	}
	
}
