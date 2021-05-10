package com.cg.fms.controller;

import java.time.LocalDate;
import java.util.Date;
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

import com.cg.fms.dto.Admin;
import com.cg.fms.dto.Contract;
import com.cg.fms.dto.Customer;
import com.cg.fms.dto.Land;
import com.cg.fms.exception.UserNotFoundException;
import com.cg.fms.jwt.JwtTokenUtil;
import com.cg.fms.service.AdminService;
import com.cg.fms.service.ContractService;
import com.cg.fms.service.CustomerService;
import com.cg.fms.service.LandService;

import io.jsonwebtoken.SignatureException;

@RestController
@RequestMapping("contractDetails")
@CrossOrigin(origins = "http://localhost:4200")
public class ContractController {

	@Autowired
	ContractService contractservice;
	
	@Autowired
	AdminService adserv;
	
	@Autowired
	CustomerService custServ;
	
	@Autowired
	LandService landServ;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@GetMapping("{getContractDetils}")
	public ResponseEntity<?> getContract(@PathVariable("getContractDetils") String contractNumber,HttpServletRequest request) {
		validateToken(request);
		Contract c = contractservice.getContract(contractNumber);
		if (c != null) {
			return new ResponseEntity<Contract>(c, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Contract details of " + contractNumber + " not found",
					HttpStatus.NOT_FOUND);
		}
	}
	
//	@PostMapping()
//	public String addContract(@Valid @RequestBody Contract contract) {
//
//		if (contractservice.addContract(contract) != null) {
//			return "Contract added successfully";
//		} else {
//			return "no contract found";
//		}
//
//	}

	@PostMapping("{contractNumber}/{quotation}/{startDate}/{endDate}/{contractStatus}/{customerId}/{adminId}/{landId}")
	public String addContract(@PathVariable("contractNumber") String contractNumber,
			@PathVariable("quotation") Long quotation, @PathVariable("startDate") Date startDate,
			@PathVariable("endDate") Date endDate, @PathVariable("contractStatus") String contractStatus,
			@PathVariable("customerId") String customerId, @PathVariable("adminId") String adminId,
			@PathVariable("landId") String landId,HttpServletRequest request) {
		//validateToken(request);
		Contract contract = new Contract();

		contract.setContractNumber(contractNumber);
		contract.setQuotation(quotation);
		contract.setStartDate(startDate);
		contract.setEndDate(endDate);
		contract.setContractStatus(contractStatus);

		Admin admin = new Admin();

		admin = adserv.getAdmin(adminId);
		

		Customer customer = new Customer();

		customer = custServ.getCustomer(customerId);

		Land land = new Land();

		land = landServ.getLand(landId);
		
		contract.setAdmin(admin);
		contract.setCustomer(customer);
		contract.setLand(land);

		if (contractservice.addContract(contract) != null) {
			return "Contract added successfully";
		} else {
			return "no contract found";
		}

	}
	
	
	@PutMapping()
	public String updateContract(@Valid @RequestBody Contract contract,HttpServletRequest request) {
		validateToken(request);
		if (contractservice.updateContract(contract))
			return "Contract has been updated";
		else
			return "Contract not found";
	}

	@PutMapping("{contractNumber}/{quotation}/{startDate}/{endDate}/{contractStatus}/{customerId}/{adminId}/{landId}")
	public String updateContract(@PathVariable("contractNumber") String contractNumber, Long quotation, Date startDate,
			Date endDate, String contractStatus, String customerId, String adminId,
			String landId,HttpServletRequest request) {
		validateToken(request);
		Contract contract = new Contract();

		contract.setContractNumber(contractNumber);
		contract.setQuotation(quotation);
		contract.setStartDate(startDate);
		contract.setEndDate(endDate);
		contract.setContractStatus(contractStatus);

		Admin admin = new Admin();

		admin = adserv.getAdmin(adminId);
		

		Customer customer = new Customer();

		customer = custServ.getCustomer(customerId);

		Land land = new Land();

		land = landServ.getLand(landId);
		
		contract.setAdmin(admin);
		contract.setCustomer(customer);
		contract.setLand(land);

		if (contractservice.updateContract(contract))
			return "Contract has been updated";
		else
			return "Contract not found";
	}
	
	
	

	@GetMapping
	public List<Contract> getAllContract(HttpServletRequest request) {
		
		return contractservice.getAllContracts();
	}

	@DeleteMapping("{contractNumber}")
	public String deleteContract(@PathVariable("contractNumber") String contractNumber,HttpServletRequest request) {
		validateToken(request);
		if (contractservice.deleteContract(contractNumber)) {
			return "Contract data is successfully deleted";
		} else
			return "Contract with " + contractNumber + " contract number is not found";
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
