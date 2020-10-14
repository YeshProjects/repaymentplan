package com.test.repaymentplan.model;

import java.io.Serializable;
import java.util.List;

public class RepaymentDetails implements Serializable{

	/**
	 * SeriaI UID
	 */
	private static final long serialVersionUID = 7015802360335104784L;
	
	private List<RepaymentPlan> borrowerPayments;

	public List<RepaymentPlan> getBorrowerPayments() {
		return borrowerPayments;
	}

	public void setBorrowerPayments(List<RepaymentPlan> borrowerPayments) {
		this.borrowerPayments = borrowerPayments;
	}
	
}
