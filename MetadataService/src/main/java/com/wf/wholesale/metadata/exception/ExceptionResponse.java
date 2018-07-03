package com.wf.wholesale.metadata.exception;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExceptionResponse {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;
	private String message;
	private String details;
	private List<String> errors;
	
	public ExceptionResponse(Date timestamp, String message, String details, List<String> errors) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.errors = errors;
	}
	
	public ExceptionResponse(Date timestamp, String message, String details, String error) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		errors = Arrays.asList(error);
	}
	
	
	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public ExceptionResponse(Date timestamp, String message) {
		super();
		this.timestamp = timestamp;
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}

	public List<String> getErrors() {
		return errors;
	}
	

}
