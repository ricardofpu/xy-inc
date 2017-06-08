package br.com.xyinc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ErrorHandle {

	private static final Logger log = LoggerFactory.getLogger(ErrorHandle.class);
	
	public static ResponseEntity<String> createHandler(String message, HttpStatus status) {
		log.error(message);
		return new ResponseEntity<String>(new ErrorMessage(status.toString(), message).toString(), status);
	}
	
}
