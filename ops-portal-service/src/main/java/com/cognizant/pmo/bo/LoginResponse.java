/**
 * 
 */
package com.cognizant.pmo.bo;

import java.io.Serializable;

/**
 * @author 238209
 *
 */
public class LoginResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3315562594088034457L;

	private boolean isSuccess;
	
	private String errorCode;
	
	private String errorDescription;

	/**
	 * @return the isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorDescription
	 */
	public String getErrorDescription() {
		return errorDescription;
	}

	/**
	 * @param errorDescription the errorDescription to set
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
}
