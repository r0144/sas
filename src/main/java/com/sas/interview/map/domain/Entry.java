package com.sas.interview.map;


public class Entry {

	private String key;
	private String val;


	public Entry() {
	}

	public Entry(String key, String val) {
		this.key = key;
		this.val = val;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

}