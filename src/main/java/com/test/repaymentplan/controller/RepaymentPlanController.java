package com.test.repaymentplan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.repaymentplan.model.LoanDetails;
import com.test.repaymentplan.model.RepaymentDetails;
import com.test.repaymentplan.service.RepaymentPlanService;

/**
 * 
 * @author Srikanth
 *
 */
@RestController
public class RepaymentPlanController {

	Logger logger = LoggerFactory.getLogger(RepaymentPlanController.class);

	@Autowired
	RepaymentPlanService repaymentPlanService;

	@PostMapping(value = "/generatePlan", consumes = "application/json", produces = "application/json; charset=UTF-8")
	public RepaymentDetails generateRepaymentPlan(@RequestBody LoanDetails loanDetails) {
		logger.debug("ENTRY : generateRepaymentPlan {} - {} -  {} - {}", loanDetails.getLoanAmmount(),
				loanDetails.getNominalRate(), loanDetails.getDuration(), loanDetails.getStartDate());
		return repaymentPlanService.getRepaymentPlan(loanDetails);

	}

}
