package com.github.youssfbr.awpag.api.controllers.exceptions;

import com.github.youssfbr.awpag.domain.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private final MessageSource messageSource;
	private static final HttpStatus CONFLICT = HttpStatus.CONFLICT;
	private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;
	private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;
	public static final String INSTANT = "instant";
	private static final String URL = "https://www.github.com.youssfbr";

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ProblemDetail> handleEntityNotFound(ResourceNotFoundException e) {

		final ProblemDetail problemDetail = ProblemDetail.forStatus(NOT_FOUND);
		problemDetail.setType(URI.create(URL));
		problemDetail.setTitle(e.getMessage());
		problemDetail.setProperty(INSTANT , Instant.now());
		return ResponseEntity.status(NOT_FOUND).body(problemDetail);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ProblemDetail> handleDataIntegrity(DataIntegrityViolationException e) {

		final ProblemDetail problemDetail = ProblemDetail.forStatus(CONFLICT);
		problemDetail.setType(URI.create(URL));
		problemDetail.setTitle("Recurso está em uso.");
		problemDetail.setProperty(INSTANT, Instant.now());
		return ResponseEntity.status(CONFLICT).body(problemDetail);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ProblemDetail> handleIllegalArgumentException(IllegalArgumentException e) {

		final ProblemDetail problemDetail = ProblemDetail.forStatus(BAD_REQUEST);
		problemDetail.setType(URI.create(URL));
		problemDetail.setTitle(e.getMessage());
		problemDetail.setProperty(INSTANT, Instant.now());
		return ResponseEntity.status(BAD_REQUEST).body(problemDetail);
	}

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex , HttpHeaders headers , HttpStatusCode status , WebRequest request) {

        final ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setType(URI.create(URL));
        problemDetail.setTitle("Um ou mais campos estão inválidos.");
		problemDetail.setProperty(INSTANT, Instant.now());

		final Map<String, String> fields = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.collect(Collectors.toMap(FieldError::getField ,
						error -> messageSource.getMessage(error , LocaleContextHolder.getLocale())));

		problemDetail.setProperty("errors", fields);

        return super.handleExceptionInternal(ex , problemDetail , headers , status , request);
    }

}
