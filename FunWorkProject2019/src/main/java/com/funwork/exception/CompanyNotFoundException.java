package com.funwork.exception;

public class CompanyNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	Integer companyId;

	public CompanyNotFoundException() {
	}

	public CompanyNotFoundException(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public CompanyNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CompanyNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompanyNotFoundException(String message) {
		super(message);
	}

	public CompanyNotFoundException(Throwable cause) {
		super(cause);
	}
}
