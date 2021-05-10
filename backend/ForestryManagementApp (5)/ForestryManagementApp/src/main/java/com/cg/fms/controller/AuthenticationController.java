package com.cg.fms.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.dao.AdminDao;
import com.cg.fms.dto.Admin;
import com.cg.fms.dto.User;
import com.cg.fms.exception.UserNotFoundException;
import com.cg.fms.jwt.JwtRequest;
import com.cg.fms.jwt.JwtResponse;
import com.cg.fms.jwt.JwtTokenUtil;




@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/authenticate")
public class AuthenticationController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	AdminDao admindao;

	Admin admin;

	@PostMapping
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest request) throws Exception {

		Optional<Admin> userData = admindao.findById(request.getUsername());

		if (userData.isPresent()) {
			admin = userData.get();
		} else {
			throw new UserNotFoundException("User not found with username: " + request.getUsername());
		}

		if (!(admin.getAdminPassword().equals(request.getPassword())))
			throw new UserNotFoundException("Invalid Password");

		String token = jwtTokenUtil.generateToken(admin);

		return ResponseEntity.ok(new JwtResponse(token,"admin"));
	}

}
