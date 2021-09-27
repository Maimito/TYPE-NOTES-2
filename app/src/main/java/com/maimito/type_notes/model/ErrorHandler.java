package com.maimito.type_notes.model;

import com.google.gson.annotations.SerializedName;

public class ErrorHandler{
	@SerializedName("error_message")
	private String errorMessage;
	@SerializedName("error_state")
	private boolean errorState;
	@SerializedName("error_code")
	private String errorCode;

	public void setErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage(){
		return errorMessage;
	}

	public void setErrorState(boolean errorState){
		this.errorState = errorState;
	}

	public boolean isErrorState(){
		return errorState;
	}

	public void setErrorCode(String errorCode){
		this.errorCode = errorCode;
	}

	public String getErrorCode(){
		return errorCode;
	}
}
