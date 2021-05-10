package com.cg.fms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.SchedulerDao;
import com.cg.fms.dao.UserDao;
import com.cg.fms.dto.Order;
import com.cg.fms.dto.User;
import com.cg.fms.exception.UserException;
import com.cg.fms.exception.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	 @Autowired
		UserDao ado;
	 Logger log = LoggerFactory.getLogger(UserService.class);
	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		log.info("logging in...");
		User user = ado.findByUserNameAndPassword(username, password);
		if(user==null) {
			log.info("User "+username+" invalid user ");
			return false;
		}
		else {
			log.info("User "+username+" has logged in successfully ");
			return true;
		}
		
	}
	
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return (List<User>)ado.findAll();
	}

	@Override
	public String logout(User user) {
		// TODO Auto-generated method stub
		if(ado.existsById(user.getUserName())) {
		log.info("logging out...");
		log.info("User "+user.getUserName()+" has been logged out");
		return "User "+user.getUserName()+" has been logged out";
		}else {
			return "User not present";
		}
		
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		if(ado.existsById(user.getUserName())) {
			log.info("user already exists");
			throw new UserException("user already exists");
		}
		ado.save(user);
		log.info("user added");
		return true;
	}

	@Override
	public boolean removeUser(String userName) {
		// TODO Auto-generated method stub
		if(ado.existsById(userName)) {
			ado.deleteById(userName);
			log.info("user removed");

			return true;
		}
		log.info("user not exits");

		return false;
	}
	
}