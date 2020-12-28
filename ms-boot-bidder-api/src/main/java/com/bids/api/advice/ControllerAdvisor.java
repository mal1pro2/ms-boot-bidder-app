package com.bids.api.advice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bids.api.exception.NotFoundException;
import com.bids.api.utils.Utils;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	 
	Logger logger = LoggerFactory.getLogger(ControllerAdvisor.class);

	@Autowired
	HttpServletRequest request;

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(NotFoundException exception) {

		Map<String, Object> body = errorResponse(exception.getMessage());
		body.put("status", HttpStatus.NOT_FOUND);
		logger.error("ErrorResponse : "+Utils.MapToJson(body));
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
 
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> conflict(HttpServletRequest req, DataIntegrityViolationException e) {

		Map<String, Object> body = errorResponse(e.getCause().getCause().getMessage());
		body.put("status", HttpStatus.CONFLICT);
		logger.error("ErrorResponse : "+Utils.MapToJson(body));
		return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	} 

	 
	@ExceptionHandler(Exception.class) 
	public ResponseEntity<Object> handleException(final Exception exception) {

		Map<String, Object> body = errorResponse(exception.getMessage()); 
		body.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		logger.error("ErrorResponse : "+Utils.MapToJson(body));
		
		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private Map<String, Object> errorResponse(Object obj) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("uri", request.getRequestURI());
		body.put("method", request.getMethod()); 
		body.put("errors", obj); 
		return body;

	}
}
