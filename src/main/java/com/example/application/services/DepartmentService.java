package com.example.application.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.application.data.Department;

@Service
public class DepartmentService {
	List<Department> departments;
	
	public DepartmentService() {
		departments = new ArrayList<Department>();
		
		departments.add(new Department("Marketing", 10));
		departments.add(new Department("Software", 20));
		departments.add(new Department("Administration", 5));
	}
	
	public List<Department> findAll() {
		return departments;
	}
}
