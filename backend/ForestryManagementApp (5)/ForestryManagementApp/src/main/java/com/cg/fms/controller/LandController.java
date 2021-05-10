package com.cg.fms.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.FormSubmitEvent.MethodType;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.dto.Land;
import com.cg.fms.dto.Product;
import com.cg.fms.exception.LandException;
import com.cg.fms.exception.UserNotFoundException;
import com.cg.fms.jwt.JwtTokenUtil;
import com.cg.fms.service.LandService;
import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.sun.org.slf4j.internal.Logger;

import io.jsonwebtoken.SignatureException;

@RestController
@RequestMapping("land")
@CrossOrigin(origins = "http://localhost:4200")
public class LandController {
	
	@Autowired
	LandService landservice;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	Logger logger=LoggerFactory.getLogger(LandController.class);
	@GetMapping("getById/{landId}")
	public ResponseEntity<?> getLand(@PathVariable("landId") String landId, HttpServletRequest request ){
		
		validateToken(request);		// this method is implemented at last
		logger.info(request.getHeader("Authorization"));
		Land land = landservice.getLand(landId);
		if(land != null) {
			return new ResponseEntity<Land>(land, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("land details with id  " + landId + " is not found", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping()
	public String addLand(@Valid @RequestBody Land land,HttpServletRequest request)  {
		validateToken(request);
		
		if(landservice.addLand(land))
			return "New Land added";
		else
			return "land already exixts";
	}
	
	
//	  @PutMapping("{landId}") 
//	  public String updateLand(@PathVariable("landId") String landId, String surveyNumber, String ownerName, String landArea) {
//	  
//	  Land land = new Land();
//	  
//	  land.setLandId(landId); 
//	  land.setSurveyNumber(surveyNumber);
//	  land.setOwnerName(ownerName); 
//	  land.setLandArea(landArea);
//	  
//	  if(landservice.updateLand(land)) 
//		  return "Land has been updated"; 
//	  else 
//		  return "Land Not found"; }
	 
	
	@PutMapping()
	public String updateLand(@Valid @RequestBody Land l,HttpServletRequest request) {
		validateToken(request);
		if(landservice.updateLand(l))
			return "Land has been updated";
		else
			return "Land Not found";
	}
	
	@DeleteMapping("/deleteLand/{landId}")
	public String deleteLand(@PathVariable("landId") String landId,HttpServletRequest request) {
		validateToken(request);
		if(landservice.removeLandDetails(landId))
			return "Land has been removed";
		else
			return "Land not found";
	}
	
	@GetMapping("getAllLand")
	public List<Land> getAllLand(HttpServletRequest request){
		validateToken(request);
		return landservice.getAllLands();
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
