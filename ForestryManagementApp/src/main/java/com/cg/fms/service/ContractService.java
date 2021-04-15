package com.cg.fms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.fms.dto.Contract;
@Service
public interface ContractService {

	public Contract getContract(String contractNumber);
	public Contract addContract(Contract contract);
	public boolean updateContract(Contract contract);
	public boolean deleteContract(String contractNumber);
	public List<Contract> getAllContracts();
}
