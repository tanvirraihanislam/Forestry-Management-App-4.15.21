package com.cg.fms.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cg.fms.dto.Admin;
@Component
@Repository
public interface AdminDao extends JpaRepository<Admin, String>{
	@Query(value="SELECT a FROM Admin a WHERE a.adminName = :adminName")
	List<Admin> findByName(@Param ("adminName") String adminName);

	Optional<Admin> findByadminName(String adminName);

}
