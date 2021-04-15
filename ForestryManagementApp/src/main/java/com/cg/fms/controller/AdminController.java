package com.cg.fms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.service.AdminService;
import com.cg.fms.dto.Admin;
import com.cg.fms.dto.Contract;
import com.cg.fms.dto.Land;

@RestController
@RequestMapping("adminDetails")
public class AdminController {

	@Autowired
	AdminService adserv;
	

	@GetMapping("getById/{adminId}")
	public ResponseEntity<?> getAdmin(@PathVariable("adminId") String adminId) {
		Admin a = adserv.getAdmin(adminId);
		if (a == null)
			return new ResponseEntity<String>("Admin with ID " + adminId + "not found", HttpStatus.NOT_FOUND);

		else
			return new ResponseEntity<Admin>(a, HttpStatus.FOUND);

	}
	
	@GetMapping("{adminName}")
	public List<Admin> getAdminsByName(@RequestParam (value = "adminName") String adminName){
		return adserv.getAdminByName(adminName);
	}

	@GetMapping
	public List<Admin> getAllAdmins() {
		return adserv.getAllAdmins();
	}

	@PostMapping("addNewLand/{adminId}/{adminName}/{adminPassword}")
	public String addAdmin(@PathVariable("adminId") String adminId, @PathVariable("adminName") String adminName, @PathVariable("adminPassword") String adminPassword) {

		Admin admin = new Admin();
		
		admin.setAdminId(adminId);
		admin.setAdminName(adminName);
		admin.setAdminPassword(adminPassword);
		if(adserv.addAdmin(admin)) {
			return "Admin added successfully";
		}
		else {
			return "Admin already there";
		}
	}
	
	@PutMapping("{adminId}")
	public String updateAdmin(@PathVariable("adminId") String adminId, String adminName, String adminPassword) {
		
		Admin admin = new Admin();
		
		admin.setAdminId(adminId);
		admin.setAdminName(adminName);
		admin.setAdminPassword(adminPassword);
		
		if(adserv.updateAdmin(admin))
			return "Admin has been updated";
		else
			return "Admin not found";
	}
	
	@DeleteMapping("{adminId}")
	public String deleteAdmin(@PathVariable("adminId") String adminId) {
		if(adserv.deleteAdmin(adminId)) {
			return "Admin has been removed";
		}
		else {
			return "Admin not found";
		}
	}
	
	@PutMapping
	public String approveContract(@RequestBody Contract contract) {
		if(adserv.approveContract(contract)) {
			//contract.setContractStatus("success");
			//System.out.println(contract.getContractStatus());
			
			return "contract approved success";
		}
		else 
			//contract.setContractStatus("failed");
		//System.out.println(contract.getContractStatus());
		return "contract is rejected";

			
	}
	
	
	

}
