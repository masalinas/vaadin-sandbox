package com.example.application.services;

import org.springframework.stereotype.Service;

import com.example.application.data.User;

@Service
public class UserService {
	public User getMockUser() {
		return new User("Sir", "Miguel", "Salinas Gancedo");
	}
}
