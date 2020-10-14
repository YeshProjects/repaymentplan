package com.test.repaymentplan.service;

import com.test.repaymentplan.model.LoanDetails;
import com.test.repaymentplan.model.RepaymentDetails;

public interface RepaymentPlanService {

	RepaymentDetails getRepaymentPlan(LoanDetails loanDetails);

}
