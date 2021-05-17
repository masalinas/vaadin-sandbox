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
		
		departments.add(new Department("Marketing", "El mejor departamento de marketing", 10));
		departments.add(new Department("Software", "Los mejores desarrolles del mundo", 20));
		departments.add(new Department("Administration", "Los que me pagan la nómina", 5));
	}
	
	public List<Department> findAll() {
		return departments;
	}
}
