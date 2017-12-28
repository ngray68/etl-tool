package com.ngray.etl;

@SuppressWarnings("serial")
public class EtlException extends Exception {

	public EtlException() {
	}

	public EtlException(String message) {
		super(message);
	}

	public EtlException(Throwable cause) {
		super(cause);
	}

	public EtlException(String message, Throwable cause) {
		super(message, cause);
	}

	public EtlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
