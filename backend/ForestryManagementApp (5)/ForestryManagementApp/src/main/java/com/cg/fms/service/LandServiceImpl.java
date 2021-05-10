package com.cg.fms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.LandDao;
import com.cg.fms.dto.Land;
import com.cg.fms.exception.LandException;

@Service
public class LandServiceImpl implements LandService{
	
	@Autowired
	LandDao lado;

	@Override
	public Land getLand(String landId) {
		Optional<Land> opt = lado.findById(landId);
		if(opt.isPresent())
			return opt.get();
		return null;
	}

	@Override
	public boolean addLand(Land land) {
		if(lado.existsById(land.getLandId())) {
			throw new LandException("Land with Id" + land.getLandId() + " is already present");	
		}else {
		lado.save(land);
		return true;
		}
	}

	@Override
	public boolean updateLand(Land land) {
		if(lado.existsById(land.getLandId())) {
			lado.save(land);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeLandDetails(String landId) {
		if(lado.existsById(landId)) {
			lado.deleteById(landId);
			return true;
		}
		return false;
	}

	@Override
	public List<Land> getAllLands() {
		
		return (List<Land>)lado.findAll();
	}

}
