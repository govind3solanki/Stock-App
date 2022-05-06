package com.zensar.stockapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvailidStockIdException extends Exception{

	private String massage;

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}

	public InvailidStockIdException(String massage) {
		super();
		this.massage = massage;
	}
	
}
