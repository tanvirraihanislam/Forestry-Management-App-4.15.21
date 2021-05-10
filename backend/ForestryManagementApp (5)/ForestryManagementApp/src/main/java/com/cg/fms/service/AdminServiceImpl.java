package com.cg.fms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.AdminDao;
import com.cg.fms.dao.ContractDao;
import com.cg.fms.dto.Admin;
import com.cg.fms.dto.Contract;
import com.cg.fms.exception.AdminException;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao ado;
	
	@Autowired
	ContractDao conRepo;
	
	@Override
	public Admin getAdmin(String adminId) {
		// TODO Auto-generated method stub
		Optional<Admin> opt=ado.findById(adminId);
		if(opt.isPresent())
			return opt.get();
		return null;
		
	}

	@Override
	public boolean addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		if(ado.existsById(admin.getAdminId())) {
			throw new AdminException("Admin with id "+admin.getAdminId()+" already exists");
		}else {
			ado.save(admin);
			return true;
		}
		
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		if(ado.existsById(admin.getAdminId())) {
			ado.save(admin);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteAdmin(String adminId) {
		// TODO Auto-generated method stub
		if(ado.existsById(adminId)) {
			ado.deleteById(adminId);
			return true;
		}
		return false;
	}

	@Override
	public List<Admin> getAllAdmins() {
		// TODO Auto-generated method stub
		return (List<Admin>)ado.findAll();
	}

	@Override
	public List<Admin> getAdminByName(String adminName) {
		// TODO Auto-generated method stub
		return (List<Admin>)ado.findByName(adminName);
	}

	@Override
	public boolean approveContract(Contract contract) {
		
		if(contract.getQuotation()<500000) {
			contract.setContractStatus("failed");
			conRepo.save(contract);
			return false;
		}
		else {
		contract.setContractStatus("success");
		conRepo.save(contract);

		return true;
		}
	}

	
	

}
