/**
 * 
 */
package com.cognizant.pmo.bo;

/**
 * @author 238209
 *
 */
public class AccountBo implements Comparable<AccountBo> {
	
	private Long accountId;
	
	private String accountName;

	/**
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AccountBo) {
			AccountBo that = (AccountBo) obj;
			if (this.getAccountId() != null && that.getAccountId() != null
					&& this.getAccountId().equals(that.getAccountId())) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int compareTo(AccountBo that) {
		if (this.getAccountId() != null && that.getAccountId() != null
				&& this.getAccountId().equals(that.getAccountId())) {
			return 0;
		}

		return 1;
	}
}
