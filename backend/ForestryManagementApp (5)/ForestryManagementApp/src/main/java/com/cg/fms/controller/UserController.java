package com.cg.fms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.dto.Order;
import com.cg.fms.dto.User;
import com.cg.fms.jwt.JwtTokenUtil;
import com.cg.fms.service.SchedulerService;
import com.cg.fms.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	
	@Autowired
	UserService ser;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@GetMapping("/login/{userName}/{password}")
	public String userLogin(@PathVariable("userName") String username, @PathVariable("password") String password) {
		if (ser.login(username, password))
			return "Login Successful";
		else
			return "Login Failed";
	}
	
	@GetMapping
	public List<User> getAllUser() {
		return ser.getAllUsers();
	}

	@PutMapping("/logout/{userName}")
	public String userLogout(@PathVariable("userName") String userName) {

		User user = new User();
		user.setUserName(userName);
		return ser.logout(user);
	}

	@PostMapping("/addUser/{userName}/{password}/{role}")
	public String addUser(@PathVariable("userName") String userName, @PathVariable("password") String password,
			@PathVariable("role") String role) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setRole(role);

		if (ser.addUser(user))
			return "User added successfully";
		else
			return "User exists";
	}

	@DeleteMapping("/removeUser/{userName}")
	public String removeUser(@PathVariable("userName") String userName) {
		if (ser.removeUser(userName))
			return "User has been removed";
		else
			return "User not found";
	}
}