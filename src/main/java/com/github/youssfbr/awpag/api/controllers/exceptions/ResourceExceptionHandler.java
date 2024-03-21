package com.github.youssfbr.awpag.api.controllers.exceptions;

import com.github.youssfbr.awpag.domain.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
	private static final  HttpStatus notFound = HttpStatus.NOT_FOUND;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {

		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(notFound.value());
		err.setError("Recurso não existe");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(notFound).body(err);
	}

}
