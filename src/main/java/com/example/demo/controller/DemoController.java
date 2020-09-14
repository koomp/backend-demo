package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.dto.UserDTO;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin
public class DemoController {

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public boolean login() {
		System.out.println("Login request");
		return true;
		// return user.getUsername().equals("user") &&
		// user.getPassword().equals("password");
	}

	@RequestMapping("/resource")
	public Map<String, Object> home() {
		System.out.println("Niekto ma zavolal");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Ladislav");

		return model;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/register")
	public ResponseEntity<Map<String, String>> register(@RequestBody UserDTO user) {

		userService.saveNewUser(user);

		Map<String, String> model = new HashMap<String, String>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Public api response");

		return new ResponseEntity<>(model, HttpStatus.OK);
	}

	@RequestMapping("/public")
	public Map<String, Object> publicAPI() {
		System.out.println("Public api request");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Public api response");

		return model;
	}
}
