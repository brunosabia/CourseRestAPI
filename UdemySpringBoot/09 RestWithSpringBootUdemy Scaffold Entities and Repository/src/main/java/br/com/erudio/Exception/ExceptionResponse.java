package br.com.erudio.Exception;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {

	//attributes
	private static final long serialVerionUID = 1L;
	private Date timestamp;
	private String message;
	private String details;

	//constructor
	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
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

	
}
