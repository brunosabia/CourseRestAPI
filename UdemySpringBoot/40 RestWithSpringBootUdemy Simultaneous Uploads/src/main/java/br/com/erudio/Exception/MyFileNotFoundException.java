package br.com.erudio.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MyFileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MyFileNotFoundException(String exception) {
			super(exception);
	}
	
	public MyFileNotFoundException(String exception, Throwable cause) {
		super(exception,cause);
}
}
