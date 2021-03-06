package com.test.repaymentplan.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Srikanth
 *
 */
public class RepaymentPlan implements Serializable{

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -7714124972049973338L;
	private float borrowerPaymentAmount;
	private LocalDate date;
	private float initialOutstandingPrincipal;
	private float interest;
	private float principal;
	private float remainingOutstandingPrincipal;
	
	public float getBorrowerPaymentAmount() {
		return borrowerPaymentAmount;
	}
	public void setBorrowerPaymentAmount(float borrowerPaymentAmount) {
		this.borrowerPaymentAmount = borrowerPaymentAmount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public float getInitialOutstandingPrincipal() {
		return initialOutstandingPrincipal;
	}
	public void setInitialOutstandingPrincipal(float initialOutstandingPrincipal) {
		this.initialOutstandingPrincipal = initialOutstandingPrincipal;
	}
	public float getInterest() {
		return interest;
	}
	public void setInterest(float interest) {
		this.interest = interest;
	}
	public float getPrincipal() {
		return principal;
	}
	public void setPrincipal(float principal) {
		this.principal = principal;
	}
	public float getRemainingOutstandingPrincipal() {
		return remainingOutstandingPrincipal;
	}
	public void setRemainingOutstandingPrincipal(float remainingOutstandingPrincipal) {
		this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
	}

}
