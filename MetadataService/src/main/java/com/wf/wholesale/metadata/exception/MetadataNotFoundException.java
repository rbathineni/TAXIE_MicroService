package com.wf.wholesale.metadata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MetadataNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -4278103250214474935L;

	public  MetadataNotFoundException(String message) {
		super(message);
	}
}
