package com.cg.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cg.fms.dto.Land;

@Component
@Repository
public interface LandDao extends JpaRepository<Land, String> {

}
