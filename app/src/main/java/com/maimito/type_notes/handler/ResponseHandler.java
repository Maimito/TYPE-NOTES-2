package com.maimito.type_notes.handler;

import com.google.gson.annotations.SerializedName;

public class ResponseHandler {
	@SerializedName("error_state")
	private Boolean errorState;
	@SerializedName("id")
	private String id;
	@SerializedName("fullname")
	private String fullname;
	@SerializedName("message")
	private String message;
	@SerializedName("username")
	private String username;

	public void setErrorState(Boolean errorState){
		this.errorState = errorState;
	}

	public boolean isErrorState(){
		return errorState;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setFullname(String fullname){
		this.fullname = fullname;
	}

	public String getFullname(){
		return fullname;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}
