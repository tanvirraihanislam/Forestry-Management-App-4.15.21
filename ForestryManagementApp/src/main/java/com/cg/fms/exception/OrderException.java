package com.cg.fms.exception;

@SuppressWarnings("serial")
public class OrderException extends RuntimeException {
	
	public OrderException(String msg) {
		super(msg);
	}
}
