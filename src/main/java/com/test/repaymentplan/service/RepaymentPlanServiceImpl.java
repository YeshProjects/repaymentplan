package com.test.repaymentplan.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.test.repaymentplan.exceptionhandler.ValidationException;
import com.test.repaymentplan.model.LoanDetails;
import com.test.repaymentplan.model.RepaymentDetails;
import com.test.repaymentplan.model.RepaymentPlan;

@Service("repaymentPlanService")
public class RepaymentPlanServiceImpl implements RepaymentPlanService {

	Logger logger = LoggerFactory.getLogger(RepaymentPlanServiceImpl.class);

	@Override
	public RepaymentDetails getRepaymentPlan(LoanDetails loanDetails) {
		logger.debug("ENTRY : getRepaymentPlan {} - {} -  {} - {}", loanDetails.getLoanAmmount(),
				loanDetails.getNominalRate(), loanDetails.getDuration(), loanDetails.getStartDate());

		validateInput(loanDetails);

		RepaymentDetails repaymentDetails = new RepaymentDetails();

		List<RepaymentPlan> repaymentPlans = new ArrayList<>();

		try {

			float monthlyInterest = ((loanDetails.getNominalRate() / 100) / 12);
			float annuity = getAnnuity(loanDetails, monthlyInterest);
			for (int i = 1; i <= loanDetails.getDuration(); i++) {

				RepaymentPlan repaymentPlan = new RepaymentPlan();

				repaymentPlan.setDate(i == 1 ? loanDetails.getStartDate() : getRepaymentDate(loanDetails));
				repaymentPlan.setBorrowerPaymentAmount(getRoundOfValue(annuity));
				repaymentPlan.setInitialOutstandingPrincipal(getRoundOfValue(loanDetails.getLoanAmmount()));

				float interestAmmount = getInterest(monthlyInterest, loanDetails);
				repaymentPlan.setInterest(getRoundOfValue(interestAmmount));

				repaymentPlan.setPrincipal(getRoundOfValue(annuity - interestAmmount));

				float remainingPrinciple = loanDetails.getLoanAmmount() - repaymentPlan.getPrincipal();
				float remainingAmmount = getRoundOfValue(remainingPrinciple);
				repaymentPlan.setRemainingOutstandingPrincipal(remainingAmmount > 1 ? remainingAmmount : 0);

				loanDetails.setLoanAmmount(remainingPrinciple);

				repaymentPlans.add(repaymentPlan);
			}

			repaymentDetails.setBorrowerPayments(repaymentPlans);
		} catch (RuntimeException e) {
			logger.error("Error while calculating repayment plan {}: ", e.getMessage());
			throw e;
		}
		return repaymentDetails;
	}

	private float getInterest(float monthlyInterest, LoanDetails loanDetails) {
		return (monthlyInterest * loanDetails.getLoanAmmount());
	}

	private float getAnnuity(LoanDetails loanDetails, float monthlyInterest) {
		float power = -loanDetails.getDuration();
		return (float) ((monthlyInterest * loanDetails.getLoanAmmount())
				/ (1 - (Math.pow((1 + monthlyInterest), power))));

	}

	private LocalDate getRepaymentDate(LoanDetails loanDetails) {
		LocalDate date = loanDetails.getStartDate().plusMonths(1);
		loanDetails.setStartDate(date);
		return date;
	}

	private void validateInput(LoanDetails loanDetails) {
		if (loanDetails == null || loanDetails.getLoanAmmount() == 0 || loanDetails.getNominalRate() == 0
				|| loanDetails.getDuration() == 0) {
			throw new ValidationException("Invalid");
		}
	}

	private float getRoundOfValue(float value) {
		return (float) (Math.round(value * 100.0) / 100.0);
	}

}
