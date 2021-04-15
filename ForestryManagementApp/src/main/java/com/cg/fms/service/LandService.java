package com.cg.fms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.fms.dto.Land;
import com.cg.fms.exception.LandException;
@Service
public interface LandService {
	
	public Land getLand(String landId);

	public boolean addLand(Land land);

	public boolean updateLand(Land land);

	public boolean removeLandDetails(String landId);
	
	public List<Land> getAllLands();
	
	
}
