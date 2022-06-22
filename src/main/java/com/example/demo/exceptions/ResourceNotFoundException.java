package com.example.demo.exceptions;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException{

	
	String resourceName;
	String fieldName;
	long fieldvalue;
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldvalue) {
		super();
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldvalue = fieldvalue;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public long getFieldvalue() {
		return fieldvalue;
	}
	public void setFieldvalue(long fieldvalue) {
		this.fieldvalue = fieldvalue;
	}
	
	
}
