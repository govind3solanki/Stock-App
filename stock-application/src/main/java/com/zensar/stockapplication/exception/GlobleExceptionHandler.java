package com.zensar.stockapplication.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobleExceptionHandler extends ResponseEntityExceptionHandler{

	
	  @ExceptionHandler(InvailidStockIdException.class) public
	  ResponseEntity<Object> handleStockError(RuntimeException re,WebRequest
	  request){ return handleExceptionInternal(re, re.toString(),new HttpHeaders(),
	  HttpStatus.BAD_REQUEST, request); }
	 
	
	/*
	 * @ExceptionHandler(InvailidStockIdException.class) public
	 * ResponseEntity<CustomErrorResponse> handleStockError(HttpServletResponse
	 * response){ CustomErrorResponse response2=new CustomErrorResponse();
	 * response2.setError("Invalid ID"); response2.setStatus(400);
	 * response2.setTimestamp("sljflsdj"); return new
	 * ResponseEntity<CustomErrorResponse>(response2,HttpStatus.BAD_REQUEST); }
	 */
}
