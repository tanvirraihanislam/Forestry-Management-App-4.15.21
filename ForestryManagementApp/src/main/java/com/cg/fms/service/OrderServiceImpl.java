package com.cg.fms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.OrderDao;
import com.cg.fms.dto.Order;
import com.cg.fms.exception.OrderException;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao ado;
	@Override
	public Order getOrder(String orderNumber) {
		// TODO Auto-generated method stub
		Optional<Order> opt=ado.findById(orderNumber);
		if(opt.isPresent())
			return opt.get();
		return null;	}

	@Override
	public boolean addOrder(Order order) {
		// TODO Auto-generated method stub
		if(ado.existsById(order.getOrderNumber())) {
			throw new OrderException("order with number "+order.getOrderNumber()+" already exists");
		}else {
			ado.save(order);
			return true;
		}
	}

	@Override
	public boolean updateOrder(Order order) {
		// TODO Auto-generated method stub
		if(ado.existsById(order.getOrderNumber())) {
			ado.save(order);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteOrder(String orderNumber) {
		// TODO Auto-generated method stub
		if(ado.existsById(orderNumber)) {
			ado.deleteById(orderNumber);
			return true;
		}
		return false;	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return (List<Order>)ado.findAll();
	}

}
