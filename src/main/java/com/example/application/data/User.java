package com.example.application.data;

public class User {	
	private String title;
	private String firstName;
	private String lastName;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public User() {
		
	}
	
	public User(String title, String firstName, String lastName) {
		super();
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
}
