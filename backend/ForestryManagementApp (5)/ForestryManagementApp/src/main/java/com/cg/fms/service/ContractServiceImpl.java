package com.cg.fms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.ContractDao;
import com.cg.fms.dto.Contract;
import com.cg.fms.exception.ContractException;
@Service
public class ContractServiceImpl implements ContractService{


	@Autowired
	ContractDao ado;

	@Override
	public Contract getContract(String contractNumber) {
		// TODO Auto-generated method stub
		Optional<Contract> opt=ado.findById(contractNumber);
		if(opt.isPresent())
			return opt.get();
		return null;
		
		
	}

	@Override
	public Contract addContract(Contract contract) {
		// TODO Auto-generated method stub
		if(ado.existsById(contract.getContractNumber())) {
			throw new ContractException("Contract with number "+contract.getContractNumber()+" already exists");
		}
		else {
			Contract savedContract =  ado.save(contract);
			return savedContract;
		}
	}

	@Override
	public boolean updateContract(Contract contract) {
		// TODO Auto-generated method stub
		if(ado.existsById(contract.getContractNumber())) {
			ado.save(contract);
			return true;
		}
		return false;

	}

	@Override
	public boolean deleteContract(String contractNumber) {
		// TODO Auto-generated method stub
		if(ado.existsById(contractNumber)) {
			ado.deleteById(contractNumber);
			return true;
		}
		return false;
		}

	@Override
	public List<Contract> getAllContracts() {
		// TODO Auto-generated method stub
		return (List<Contract>)ado.findAll();
	}
	
	
}
