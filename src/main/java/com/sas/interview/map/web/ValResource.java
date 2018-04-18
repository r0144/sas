package com.sas.interview.map;

import org.springframework.hateoas.Resource;


public class ValResource extends Resource<String> {

	public ValResource(String val) {
		super(val == null ? "" : val);
	}

}