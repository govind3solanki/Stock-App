package com.zensar.stockapplication.exception;

import lombok.Data;

@Data
public class CustomErrorResponse {

	private String timestamp;
	private int status;
	private String error;
}
