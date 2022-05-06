package com.zensar.stockapplication.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class CustomInfoOverriden implements InfoContributor{

	@Override
	public void contribute(Builder builder) {
		List<String> props = new ArrayList<>();
		  props.add("namespace");
		  props.add("pod");
		  builder.withDetail("k8s", props);
	}

}
