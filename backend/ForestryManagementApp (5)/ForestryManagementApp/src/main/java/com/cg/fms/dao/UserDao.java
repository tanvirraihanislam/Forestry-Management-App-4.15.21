package com.cg.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cg.fms.dto.User;
@Component
@Repository
public interface UserDao extends JpaRepository<User, String> {
	@Query(value="SELECT c FROM User c WHERE c.userName = :username and c.password = :password")
	public User findByUserNameAndPassword(@Param("username")String userName,@Param("password") String password);
	

}