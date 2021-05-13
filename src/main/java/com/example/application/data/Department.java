package com.example.application.data;

public class Department {
	private String name;
	private Integer size;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	
	public Department(String name, Integer size) {
		super();
		this.name = name;
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
