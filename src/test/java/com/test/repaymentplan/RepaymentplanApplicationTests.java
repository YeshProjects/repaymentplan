package com.test.repaymentplan;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.repaymentplan.controller.RepaymentPlanController;

@SpringBootTest
class RepaymentplanApplicationTests {

	@Autowired
	RepaymentPlanController repaymentPlanController;

	@Test
	void contextLoads() {
		assertThat(repaymentPlanController).isNotNull();
	}

}
