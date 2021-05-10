package com.cg.fms.exception.handler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.fms.exception.ProductException;

@ControllerAdvice
public class ProductHandler {
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<?> handleDuplicateEntity(ProductException ex){
		Map<String,Object> errorBody=new LinkedHashMap<>();
	errorBody.put("error", "Creation failed");
		errorBody.put("timestap", LocalDateTime.now());
		errorBody.put("details", ex.getMessage());
return new ResponseEntity<>(errorBody,HttpStatus.BAD_REQUEST);
	}
}
