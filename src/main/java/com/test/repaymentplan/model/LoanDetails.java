package com.test.repaymentplan.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Srikanth
 *
 */
public class LoanDetails implements Serializable {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 5437695427199545192L;

	private float loanAmmount;
	private float nominalRate;
	private float duration;
	@JsonFormat(pattern = "dd.MM.yyyy")
	private LocalDate startDate;

	public LoanDetails() {
		super();
	}

	public LoanDetails(float loanAmmount, float nominalRate, float duration, LocalDate startDate) {
		super();
		this.loanAmmount = loanAmmount;
		this.nominalRate = nominalRate;
		this.duration = duration;
		this.startDate = startDate;
	}

	public float getLoanAmmount() {
		return loanAmmount;
	}

	public void setLoanAmmount(float loanAmmount) {
		this.loanAmmount = loanAmmount;
	}

	public float getNominalRate() {
		return nominalRate;
	}

	public void setNominalRate(float nominalRate) {
		this.nominalRate = nominalRate;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

}
