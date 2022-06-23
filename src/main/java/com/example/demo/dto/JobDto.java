package com.example.demo.dto;



public class JobDto {

	private int j_id;
	
	private String title;
	
	private String location;
	private String apply;
	//private Date postTime;

	public int getJ_id() {
		return j_id;
	}

	public void setJ_id(int j_id) {
		this.j_id = j_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setApply(String apply) {
		this.apply = apply;
	}
	public String getApply() {
		return apply;
	}
	
	/*
	 * public void setPostTime(Date postTime) { this.postTime = postTime; } public
	 * Date getPostTime() { return postTime; }
	 */
}
