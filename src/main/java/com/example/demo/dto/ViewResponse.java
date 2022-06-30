package com.example.demo.dto;

public class ViewResponse {

	private String name;
	private String title;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ViewResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ViewResponse(String name, String title) {
		super();
		this.name = name;
		this.title = title;
	}
	@Override
	public String toString() {
		return "ViewResponse [name=" + name + ", title=" + title + "]";
	}
	
	
}
