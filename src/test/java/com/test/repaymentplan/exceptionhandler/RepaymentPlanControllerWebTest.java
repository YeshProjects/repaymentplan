package com.test.repaymentplan.exceptionhandler;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.test.repaymentplan.controller.RepaymentPlanController;
import com.test.repaymentplan.model.LoanDetails;
import com.test.repaymentplan.service.RepaymentPlanServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RepaymentPlanController.class)
class RepaymentPlanControllerWebTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ApplicationContext applicationContext;

	@MockBean
	private RepaymentPlanServiceImpl repaymentPlanService;
	LoanDetails loanDetails;
	HttpHeaders requestHeaders;

	@BeforeEach
	void printApplicationContext() {

		loanDetails = new LoanDetails(5000, 5, 24, LocalDate.now());
		Arrays.stream(applicationContext.getBeanDefinitionNames())
				.map(name -> applicationContext.getBean(name).getClass().getName()).sorted()
				.forEach(System.out::println);

		String username = "user";
		String password = "password";

		String authorizationHeader = "Basic "
				+ DatatypeConverter.printBase64Binary((username + ":" + password).getBytes());

		requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		requestHeaders.add("Authorization", authorizationHeader);
	}

	@Test
	void testLendingPlan_OK() throws Exception {
		// when(repaymentPlanService.getRepaymentPlan(loanDetails)).thenThrow(PlanGenerationProcessingException.class);

		mockMvc.perform(MockMvcRequestBuilders.post("/generatePlan/").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content("{\r\n" + "    \"loanAmmount\" : 5000,\r\n" + "    \"nominalRate\" : 0,\r\n"
						+ "    \"duration\" : 24,\r\n" + "    \"startDate\": \"01.01.2018\"\r\n" + "}")
				.headers(requestHeaders)).andExpect(status().isOk()).andReturn();
	}

	@Test
	void testLendingPlan_CLIENT_ERROR() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/generatePlan/").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content("").headers(requestHeaders))
				.andExpect(status().is4xxClientError()).andReturn();
	}

}
