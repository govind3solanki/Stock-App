package com.zensar.stockapplication.endpoints;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Endpoint(id ="custom")
@Component
public class CustomEndPoints {
	@ReadOperation
	public String myOwnCustomEndpoint() {
		return "my own endpoint";
	}
}
