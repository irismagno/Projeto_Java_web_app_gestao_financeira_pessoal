package br.com.fiap.fintech.exception;

import java.sql.SQLException;

public class BDException extends SQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BDException() {
		// TODO Auto-generated constructor stub
	}

	public BDException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BDException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public BDException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BDException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super();
		// TODO Auto-generated constructor stub
	}

}
