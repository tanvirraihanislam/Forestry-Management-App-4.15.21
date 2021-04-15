package com.cg.fms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.fms.dto.Admin;
import com.cg.fms.dto.Contract;
import com.cg.fms.exception.AdminException;
@Service
public interface AdminService {
	
	public Admin getAdmin(String adminId);

	public boolean addAdmin(Admin admin);
	
	public boolean updateAdmin(Admin admin);

	public boolean deleteAdmin(String adminId);

	public List<Admin> getAllAdmins();
	
	public List<Admin> getAdminByName(String adminName);
	public boolean approveContract(Contract contract);
}
