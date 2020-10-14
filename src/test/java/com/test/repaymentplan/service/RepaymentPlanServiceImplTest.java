package com.test.repaymentplan.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.repaymentplan.exceptionhandler.ValidationException;
import com.test.repaymentplan.model.LoanDetails;
import com.test.repaymentplan.model.RepaymentDetails;
import com.test.repaymentplan.service.RepaymentPlanServiceImpl;

@SpringBootTest
class RepaymentPlanServiceImplTest {

	@InjectMocks
	private RepaymentPlanServiceImpl repaymentPlanService;

	private LoanDetails loanDetails;

	@BeforeEach
	public void setup() {
		loanDetails = new LoanDetails(5000, 5, 24, LocalDate.now());
	}

	@Test
	void testRepaymentPlanSuccess() {
		RepaymentDetails repaymentPlan = repaymentPlanService.getRepaymentPlan(loanDetails);
		assertNotNull(repaymentPlan);
		assertEquals(219.35,
				(Math.round(repaymentPlan.getBorrowerPayments().get(0).getBorrowerPaymentAmount() * 100.0) / 100.0));
		assertEquals(LocalDate.now(), repaymentPlan.getBorrowerPayments().get(0).getDate());
		assertEquals(5000, repaymentPlan.getBorrowerPayments().get(0).getInitialOutstandingPrincipal());
		assertTrue(repaymentPlan.getBorrowerPayments().get(0).getRemainingOutstandingPrincipal() > 0);
		assertTrue(repaymentPlan.getBorrowerPayments().get(0).getInterest() > 0);
	}

	@Test
	void testRepaymentPlan_InputValidation() {
		LoanDetails loanDetails = new LoanDetails();
		loanDetails.setNominalRate(5);
		loanDetails.setDuration(24);
		loanDetails.setStartDate(LocalDate.now());
		assertThrows(ValidationException.class, () -> {
			repaymentPlanService.getRepaymentPlan(loanDetails);
		});
	}

}