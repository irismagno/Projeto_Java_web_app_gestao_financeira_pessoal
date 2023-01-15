package br.com.fiap.fintech.exception;

public class SaldoInsuficiente extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaldoInsuficiente() {
		
	}

	public SaldoInsuficiente(String message) {
		super(message);
		
	}

	public SaldoInsuficiente(Throwable cause) {
		super(cause);
		
	}

	public SaldoInsuficiente(String message, Throwable cause) {
		super(message, cause);
		
	}

	public SaldoInsuficiente(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
