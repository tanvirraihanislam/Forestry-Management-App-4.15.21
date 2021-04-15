package com.cg.fms.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.fms.dao.ContractDao;
import com.cg.fms.dto.Contract;

import org.junit.jupiter.api.Test;

@SpringBootTest
public class ContractRepositoryTest {
	
	@Autowired
	private ContractDao contractrepo;
	private Contract contract;
	private Contract contract2;
	
	@BeforeEach
	void setUp() {
		
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
		
		Contract contract = new Contract();
		
//		Contract contract = new Contract(null,  0,  null,  null);
		contract.setContractNumber("101");
		contract.setQuotation(500000);
		contract.setStartDate(strdate);
		contract.setEndDate(enddate);
		contract.setAdmin(null);
		contract.setContractStatus("Approved");
		contract.setCustomer(null);
		contract.setLand(null);
		
		Contract contract2 = new Contract(null,  0,  null,  null);
		contract2.setContractNumber("102");
		contract2.setQuotation(30000);
		contract2.setStartDate(strdate);
		contract2.setEndDate(enddate);
		
	}
	
	@AfterEach
	void tearDown() {
		contractrepo.deleteAll();
		contract = null;
		contract2 = null;
	}
	
	@Test
	public void getById() {
//		System.out.println("Abir Das");
//		Contract contract = new Contract("122222",500000);
		System.out.println(contract);
		Contract contract1 = contractrepo.save(contract);
		
//		System.out.println(contractrepo.save(contract));
		
		Optional<Contract> optional = contractrepo.findById(contract1.getContractNumber());
		
		assertEquals(contract1.getContractNumber(), optional.get().getContractNumber());
		 
	}
	
	@Test
	public void save() {
		
		Contract contract = new Contract("201", 500000);
		contractrepo.save(contract);
		
		Contract fetchedContract = contractrepo.findById(contract.getContractNumber()).get();
		
		assertEquals("201", fetchedContract.getContractNumber());
		
	}
	
	@Test
	public void givenGetAllContractsThenShouldReturnListOfAllContract() {
		
		Contract contract = new Contract("201", 500000);
		Contract contrac2 = new Contract("202", 300000);
		
		contractrepo.save(contract);
		contractrepo.save(contrac2);
		
		List<Contract> contractList = contractrepo.findAll();
		
		System.out.println(contractList.get(0).getQuotation());
		
		assertEquals(500000, contractList.get(0).getQuotation());
	
	}
	
	@Test
	public void givenContractIdThenShouldReturnDeletedContract() {
		Contract contract2 = new Contract("201", 500000);
		
		contractrepo.save(contract2);
		contractrepo.deleteById(contract2.getContractNumber());
		
		Optional<Contract> optional = contractrepo.findById(contract2.getContractNumber());
		
		assertEquals(Optional.empty(), optional);
	}
	
	
	
}
