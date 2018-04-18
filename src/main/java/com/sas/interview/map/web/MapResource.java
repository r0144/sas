package com.sas.interview.map;

import java.util.Map;

import org.springframework.hateoas.Resource;


public class MapResource extends Resource<Map> {

	public MapResource(Map map) {
		super(map);
	}

}