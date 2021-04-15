package com.cg.fms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.fms.dao.ContractDao;
import com.cg.fms.dto.Contract;
import com.cg.fms.exception.ContractException;
import static org.junit.jupiter.api.Assertions.assertThrows;



@SpringBootTest
public class ContractServiceTest {

	@Mock
	private ContractDao contractrepo;
	
	@InjectMocks
	private ContractServiceImpl contractservice;
	private Contract contract;
	private List<Contract> contractList;
	private Optional optional;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Contract contract = new Contract("122222",500000);
		optional = Optional.of(contract);
		
		
		String startDate = "03/05/2018";
		String endDate = "10/11/2020";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date strdate = null;
		Date enddate = null;
		try {
			strdate = df.parse(startDate);
			enddate = df.parse(endDate);
		} catch (ParseException e1) {
			System.out.println("wrong date formate");
			e1.printStackTrace();
		}
		
		
		Contract contract2 = new Contract(null,  0,  null,  null);
		contract2.setContractNumber("102");
		contract2.setQuotation(30000);
		contract2.setStartDate(strdate);
		contract2.setEndDate(enddate);
		
		
	}
	
	@AfterEach
	public void tearDown() {
//		contract = null;
	}
	
	@Test
	void givenContractTosavethenShouldreturnSavedContract() throws ContractException{
		Contract contract = new Contract("201",500000);
		when(contractrepo.save(any())).thenReturn(contract);
		
//		System.out.println(contractservice.addContract(contract));
		
		assertEquals(contract, contractservice.addContract(contract));
		verify(contractrepo, times(1)).save(any());
	}
	
	@Test
	public void givenAllBlogsThenShouldreturnListOfAllBlogs() throws ContractException{
		
		contractrepo.save(contract);
		
		when(contractrepo.findAll()).thenReturn(contractList);
		
		List<Contract> contractList1 = contractservice.getAllContracts();
		
		assertEquals(contractList, contractList1);
		
		verify(contractrepo, times(1)).save(contract);
		
		verify(contractrepo, times(1)).findAll();
	}
	
	@Test
	public void givenContractTosaveThenShouldNotReturnSavedContract() {
		Contract contract = new Contract("201",500000);
		
		when(contractrepo.save(contract)).thenThrow(new RuntimeException());
		
		// It throws an error thats why assertThrows() is passed.
		assertThrows(RuntimeException.class, () -> {contractservice.addContract(contract);});
		
		verify(contractrepo, times(1)).save(contract);
	}
	
	@Test
	void givenContractNumberThenShouldreturnRespectiveContract() throws ContractException{
		Contract contract = new Contract("201",500000);
		
		when(contractrepo.findById("101")).thenReturn(Optional.of(contract));
		
		Contract receivedContract = contractservice.getContract(contract.getContractNumber());
		
		verify(contractrepo, times(1)).findById("201");
	}
	
	@Test
	void givenContractNumberThenShouldDeleteRespectiveContract()throws ContractException{
		
		Contract contract = new Contract("201",500000);
		
		when(contractrepo.findById(contract.getContractNumber())).thenReturn(optional);
		 
		boolean deletedContract = contractservice.deleteContract("201");
		
		System.out.println(deletedContract);
		
		assertEquals(false, deletedContract);
		
//		verify(contractrepo, times(2)).findById(contract.getContractNumber());
		
//		verify(contractrepo, times(1)).deleteById(contract.getContractNumber());
	}
	
	@Test
	public void givenContractToUpdateThenShouldReturnUpdatedContract() throws ContractException {
		
		Contract contract = new Contract("201",500000);
		
		when(contractrepo.existsById(contract.getContractNumber())).thenReturn(true);
		
		when(contractrepo.findById(contract.getContractNumber())).thenReturn(Optional.ofNullable(contract));
		
		when(contractrepo.save(contract)).thenReturn(contract);
		
		contract.setQuotation(400000);
		
		boolean flag = contractservice.updateContract(contract);
		
		assertEquals(true, flag);
	}
	
	
	
	
	
}
