package com.cg.fms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.service.AdminService;
import com.cg.fms.service.ContractService;

import io.jsonwebtoken.SignatureException;

import com.cg.fms.dto.Admin;
import com.cg.fms.dto.Contract;
import com.cg.fms.dto.Land;
import com.cg.fms.dto.StringResponse;
import com.cg.fms.exception.UserNotFoundException;
import com.cg.fms.jwt.JwtTokenUtil;

@RestController
@RequestMapping("/adminDetails")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	@Autowired
	AdminService adserv;

	@Autowired
	ContractService conServ;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	Logger logger=LoggerFactory.getLogger(AdminController.class);

//	@GetMapping("getById/{adminId}")
//	public ResponseEntity<?> getAdmin(@PathVariable("adminId") String adminId) {
//		Admin a = adserv.getAdmin(adminId);
//		if (a == null)
//			return new ResponseEntity<String>("Admin with ID " + adminId + "not found", HttpStatus.NOT_FOUND);
//
//		else
//			return new ResponseEntity<Admin>(a, HttpStatus.OK);
//
//	}
	
	@GetMapping("getById/{adminId}")
	//public ResponseEntity<?> getAdmin(@PathVariable("adminId") String adminId) {
	public Admin getAdmin(@PathVariable("adminId") String adminId) {
		
		Admin a = adserv.getAdmin(adminId);
//		if (a == null)
//			return new ResponseEntity<String>("Admin with ID " + adminId + "not found", HttpStatus.NOT_FOUND);
//
//		else
//			return new ResponseEntity<Admin>(a, HttpStatus.FOUND);
		return adserv.getAdmin(adminId);

	}
	
	

	@GetMapping("{adminName}")
	public List<Admin> getAdminsByName(@RequestParam(value = "adminName") String adminName,HttpServletRequest request) {
		
		return adserv.getAdminByName(adminName);
	}

	@GetMapping
	public List<Admin> getAllAdmins(HttpServletRequest request) {
		
		return adserv.getAllAdmins();
	}

	@PostMapping()
	public String addAdmin(@Valid @RequestBody Admin admin,HttpServletRequest request) {
	//	validateToken(request);
		
		if (adserv.addAdmin(admin)) {
			return "Admin added successfully";
		} else {
			return "Admin already there";
		}
	}
	
	@PutMapping("{adminId}")
	public String updateAdmin(@Valid @RequestBody Admin admin,HttpServletRequest request) {
		validateToken(request);
		if (adserv.updateAdmin(admin))
			return "Admin has been updated";
		else
			return "Admin not found";
	}

//	@PutMapping("{adminId}")
//	public String updateAdmin(@PathVariable("adminId") String adminId, String adminName, String adminPassword) {
//
//		Admin admin = new Admin();
//
//		admin.setAdminId(adminId);
//		admin.setAdminName(adminName);
//		admin.setAdminPassword(adminPassword);
//
//		if (adserv.updateAdmin(admin))
//			return "Admin has been updated";
//		else
//			return "Admin not found";
//	}

	@DeleteMapping("{adminId}")
	public String deleteAdmin(@PathVariable("adminId") String adminId,HttpServletRequest request) {
		validateToken(request);
		if (adserv.deleteAdmin(adminId)) {
			return "Admin has been removed";
		} else {
			return "Admin not found";
		}
	}

	@GetMapping(value="verify/{contractNumber}")
	public String approveContract(@PathVariable String contractNumber,HttpServletRequest request) {
		
		validateToken(request);
		
		Contract contract = new Contract();
		contract = conServ.getContract(contractNumber);
 
		if (adserv.approveContract(contract)) {
			// contract.setContractStatus("success");
			// System.out.println(contract.getContractStatus())
		return  "Contract Sucessful";
		} else {
			return "Contract Failed";
		}

		// contract.setContractStatus("failed");
		// System.out.println(contract.getContractStatus());

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
