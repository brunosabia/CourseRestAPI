package br.com.erudio.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileStorageException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileStorageException(String exception) {
			super(exception);
	}
	
	public FileStorageException(String exception, Throwable cause) {
		super(exception,cause);
}
}
