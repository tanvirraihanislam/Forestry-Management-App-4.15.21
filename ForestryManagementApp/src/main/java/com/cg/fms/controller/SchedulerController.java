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
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.service.SchedulerService;
import com.cg.fms.dto.Scheduler;

@RestController
@RequestMapping("schedulerDetails")
public class SchedulerController {

	@Autowired
	SchedulerService schedServ;
	
	@GetMapping("{schedulerId}")
	public ResponseEntity<?> getScheduler(@PathVariable("schedulerId") String schedulerId){
		Scheduler s = schedServ.getScheduler(schedulerId);
		if(s == null)
			return new ResponseEntity<String>("Scheduler with ID "+ schedulerId + " not found",HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Scheduler>(s, HttpStatus.FOUND);
	}
	
	@PostMapping("{schedulerId}/{schedulerName}/{schedulerContact}/{truckNumber}")
	public String addScheduler(@PathVariable("schedulerId") String schedulerId,@PathVariable("schedulerName") String schedulerName,
			@PathVariable("schedulerContact") String schedulerContact,@PathVariable("truckNumber") String truckNumber) {
		Scheduler s = new Scheduler();
		s.setSchedulerId(schedulerId);
		s.setSchedulerName(schedulerName);
		s.setSchedulerContact(schedulerContact);
		s.setTruckNumber(truckNumber);
		if(schedServ.addScheduler(s)) {
			return "Scheduler added Successfully";
		}
		else {
			return "Scheduler exists already!";
		}
	}
	
	@PutMapping("{schedulerId}")
	public String updateScheduler(@PathVariable("schedulerId") String schedulerId,String schedulerName,
			String schedulerContact,String truckNumber) {
		Scheduler s = new Scheduler();
		s.setSchedulerId(schedulerId);
		s.setSchedulerName(schedulerName);
		s.setSchedulerContact(schedulerContact);
		s.setTruckNumber(truckNumber);
		if(schedServ.updateScheduler(s)) {
			return "Scheduler update Successfully";
		}
		else {
			return "Scheduler not found!";
		}
	}
	
	@DeleteMapping("{schedulerId}")
	public String deleteScheduler(@PathVariable("schedulerId") String schedulerId) {
		if(schedServ.deleteScheduler(schedulerId)) {
			return "Scheduler deleted Successfully!";
		}
		else {
			return "Scheduler Not Found!";
		}
		
	}
	
	@GetMapping
	public List<Scheduler> getAllScheduler(){
		return schedServ.getAllSchedulers();
	}
	
}
