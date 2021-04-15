package com.cg.fms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.SchedulerDao;
import com.cg.fms.dto.Scheduler;
import com.cg.fms.exception.SchedulerException;

@Service
public class SchedulerServiceImpl implements SchedulerService {

	 @Autowired
		SchedulerDao ado;
	@Override
	public Scheduler getScheduler(String schedulerId) {
		// TODO Auto-generated method stub
		Optional<Scheduler> opt=ado.findById(schedulerId);
		if(opt.isPresent())
			return opt.get();
		return null;
		}

	@Override
	public boolean addScheduler(Scheduler scheduler) {
		// TODO Auto-generated method stub
		if(ado.existsById(scheduler.getSchedulerId())) {
			throw new SchedulerException("scheduler with number "+scheduler.getSchedulerId()+" already exists");
		}else {
			ado.save(scheduler);
			return true;
		}
	}

	@Override
	public boolean updateScheduler(Scheduler scheduler) {
		// TODO Auto-generated method stub
		if(ado.existsById(scheduler.getSchedulerId())) {
			ado.save(scheduler);
			return true;
		}
		return false;	
	}

	@Override
	public boolean deleteScheduler(String schedulerId) {
		// TODO Auto-generated method stub
		if(ado.existsById(schedulerId)) {
			ado.deleteById(schedulerId);
			return true;
		}
		return false;	}

	@Override
	public List<Scheduler> getAllSchedulers() {
		// TODO Auto-generated method stub
		return (List<Scheduler>)ado.findAll();
	}

}
