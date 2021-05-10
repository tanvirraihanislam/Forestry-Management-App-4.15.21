package com.cg.fms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.service.SchedulerService;

import io.jsonwebtoken.SignatureException;

import com.cg.fms.dto.Scheduler;
import com.cg.fms.exception.UserNotFoundException;
import com.cg.fms.jwt.JwtTokenUtil;

@RestController
@RequestMapping("schedulerDetails")
@CrossOrigin(origins = "http://localhost:4200")
public class SchedulerController {

	@Autowired
	SchedulerService schedServ;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@GetMapping("{schedulerId}")
	public ResponseEntity<?> getScheduler(@PathVariable("schedulerId") String schedulerId,HttpServletRequest request){
		validateToken(request);
		Scheduler s = schedServ.getScheduler(schedulerId);
		if(s == null)
			return new ResponseEntity<String>("Scheduler with ID "+ schedulerId + " not found",HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Scheduler>(s, HttpStatus.OK);
	}
	
	@PostMapping()
	public String addScheduler(@Valid @RequestBody Scheduler s,HttpServletRequest request) {
		validateToken(request);
		
		if(schedServ.addScheduler(s)) {
			return "Scheduler added Successfully";
		}
		else {
			return "Scheduler exists already!";
		}
	}
	
	@PutMapping()
	public String updateScheduler(@Valid @RequestBody Scheduler s,HttpServletRequest request) {
		validateToken(request);
		if(schedServ.updateScheduler(s)) {
			return "Scheduler update Successfully";
		}
		else {
			return "Scheduler not found!";
		}
	}
	
	@PutMapping("{schedulerId}")
	public String updateScheduler(@PathVariable("schedulerId") String schedulerId,String schedulerName,
			String schedulerContact,String truckNumber,HttpServletRequest request) {
		validateToken(request);
		Scheduler s = new Scheduler();
		s.setSchedulerId(schedulerId);
		s.setSchedulerName(schedulerName);
		s.setSchedulerContact(schedulerContact);
		s.setTruckNumber(truckNumber);
		if(schedServ.updateScheduler(s)) {
			return "Scheduler update Successfully";
		}
		else {
			return "Scheduler not found!";
		}
	}
	
	@DeleteMapping("{schedulerId}")
	public String deleteScheduler(@PathVariable("schedulerId") String schedulerId,HttpServletRequest request) {
		validateToken(request);
		if(schedServ.deleteScheduler(schedulerId)) {
			return "Scheduler deleted Successfully!";
		}
		else {
			return "Scheduler Not Found!";
		}
		
	}
	
	@GetMapping
	public List<Scheduler> getAllScheduler(HttpServletRequest request){
		validateToken(request);
		return schedServ.getAllSchedulers();
	}
	
	public void validateToken(HttpServletRequest request) {
		final String tokenHeader = request.getHeader("Authorization");

		String jwtToken = null;

		if (tokenHeader == null)
			throw new UserNotFoundException("User Not Logged In or token not included");
		// JWT Token is in the form "Bearer token". Remove Bearer word
		if (!tokenHeader.startsWith("Bearer "))
			throw new UserNotFoundException("Invalid Token");

		jwtToken = tokenHeader.substring(7);
		try {
			if (!(jwtTokenUtil.validateToken(jwtToken)))
				throw new UserNotFoundException("Token Expired. Need Relogin");

		} catch (SignatureException ex) {
			throw new UserNotFoundException("Invalid Token");
		}
	}
	
}
