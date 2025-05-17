package com.capgemini.flightbookingsystem.dto;

public class ResponseToken {

	private String token;

	public ResponseToken() {

	}

	public ResponseToken(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
