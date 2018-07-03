package com.wf.wholesale.metadata.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.NoResultException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class MetadataResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handles JPA NoResultExceptions thrown from web service controller methods.
	 * Creates a response with an empty body and HTTP status code 404, not found.
	 * 
	 * @param ex A NoResultException instance.
	 * @return A ResponseEntity with an empty response body and HTTP status code 404.
	 */
	@ExceptionHandler(value = { NoResultException.class, NoSuchElementException.class,
			EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleNoResultException(final RuntimeException ex, final WebRequest request) {
		logger.info("> handleNoResultException");
		logger.info("- NoResultException: ", ex);
		ExceptionResponse response = new ExceptionResponse(new Date(), "No Results Found.",
				request.getDescription(false), ex.getMessage());

		logger.info("< handleNoResultException");
		return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	/**
	 * Handles Custom MetadataNotFoundException thrown from web service controller methods.
	 * Creates a response with an empty body and HTTP status code 404, not found.
	 * 
	 * @param ex A MetadataNotFoundException instance.
	 * @return A ResponseEntity with an empty response body and HTTP status code 404.
	 */
	@ExceptionHandler(MetadataNotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundException(MetadataNotFoundException ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(),
				null != ex.getLocalizedMessage() ? ex.getLocalizedMessage() : "Resource Not Found.",
				request.getDescription(false), ex.getMessage());
		return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles Springs MethodArgumentNotValidException thrown from web service controller methods.
	 * Creates a response with an empty body and HTTP status code 400, not found.
	 * 
	 * @param ex A MethodArgumentNotValidException instance.
	 * @return A ResponseEntity with an empty response body and HTTP status code 400.
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		ExceptionResponse apiError = new ExceptionResponse(new Date(), "Validation failed.",
				request.getDescription(false), errors);
		return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
	}

	/**
	 * Handles Springs MissingServletRequestParameterException thrown from web service controller methods.
	 * Creates a response with an empty body and HTTP status code 400, not found.
	 * 
	 * @param ex A MissingServletRequestParameterException instance.
	 * @return A ResponseEntity with an empty response body and HTTP status code 400.
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " parameter is missing";

		ExceptionResponse apiError = new ExceptionResponse(new Date(), ex.getLocalizedMessage(),
				request.getDescription(false), error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles Springs MethodArgumentTypeMismatchException thrown from web service controller methods.
	 * Creates a response with an empty body and HTTP status code 400, not found.
	 * 
	 * @param ex A MethodArgumentTypeMismatchException instance.
	 * @return A ResponseEntity with an empty response body and HTTP status code 400.
	 */
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

		ExceptionResponse apiError = new ExceptionResponse(new Date(), ex.getLocalizedMessage(),
				request.getDescription(false), error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles JSON HttpMessageNotReadableException thrown from web service controller methods.
	 * Creates a response with an empty body and HTTP status code 400, not found.
	 * 
	 * @param ex A HttpMessageNotReadableException instance.
	 * @return A ResponseEntity with an empty response body and HTTP status code 400.
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
		ExceptionResponse apiError = new ExceptionResponse(new Date(), error, request.getDescription(false),
				ex.getLocalizedMessage());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles java.lang.Exception thrown from web service controller methods.
	 * Creates a response with an empty body and HTTP status code 500,  Internal Server Error.
	 * 
	 * @param ex A java.lang.Exception instance.
	 * @return A ResponseEntity with an empty response body and HTTP status code 500.
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), "Internal Server Error.",
				request.getDescription(false), ex.getMessage());

		return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
