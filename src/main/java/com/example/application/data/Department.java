package com.example.application.data;

public class Department {
	private String name;
	private String description;
	private Integer size;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	
	public Department(String name, String description, Integer size) {
		super();
		this.name = name;
		this.description = description;
		this.size = size;
	}

	public String getSizeToString() {
		return "Department Size: " + size.toString();
	}
	
	@Override
	public String toString() {
		return "Department [name=" + name + ", size=" + size + "]";
	}
}
