package com.test.repaymentplan.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;

import com.test.repaymentplan.controller.RepaymentPlanController;
import com.test.repaymentplan.exceptionhandler.ProcessingException;
import com.test.repaymentplan.model.LoanDetails;
import com.test.repaymentplan.model.RepaymentDetails;
import com.test.repaymentplan.service.RepaymentPlanService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RepaymentPlanControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@InjectMocks
	private RepaymentPlanController repaymentPlanController;

	@Mock
	private RepaymentPlanService repaymentPlanService;

	@Test
	void generateRepaymentPlan_Success() throws Exception {

		String username = "user";
		String password = "password";

		String url = "http://localhost:" + port + " + \"generatePlan";

		String authorizationHeader = "Basic "
				+ DatatypeConverter.printBase64Binary((username + ":" + password).getBytes());

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		requestHeaders.add("Authorization", authorizationHeader);

		HttpEntity<LoanDetails> requestEntity = new HttpEntity<>(getLoanDetails(), requestHeaders);

		ResponseEntity<RepaymentDetails> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
				RepaymentDetails.class);

		assertThat(responseEntity.getStatusCode().is2xxSuccessful());

	}

	@Test
	void generateRepaymentPlan_RequestNull() throws Exception {
		String url = "http://localhost:" + port + " + \"generatePlan";

		try {
			restTemplate.exchange(url, HttpMethod.POST, null, RepaymentDetails.class);
		} catch (Exception e) {
			assertEquals(e.getClass().getName(), ResourceAccessException.class.getName());
		}
	}

	@Test
	void testRepaymentPlanSuccess() {
		RepaymentDetails repaymentDetails = new RepaymentDetails();
		Mockito.when(repaymentPlanService.getRepaymentPlan(Mockito.any(LoanDetails.class)))
				.thenReturn(repaymentDetails);
		RepaymentDetails repaymentPlan = repaymentPlanController.generateRepaymentPlan(getLoanDetails());
		assertNotNull(repaymentPlan);
	}

	@Test
	void testRepaymentPlan_Exception() {
		Mockito.when(repaymentPlanService.getRepaymentPlan(Mockito.any(LoanDetails.class)))
				.thenThrow(ProcessingException.class);
		assertThrows(ProcessingException.class, () -> {
			repaymentPlanController.generateRepaymentPlan(getLoanDetails());
		});

	}

	@Test
	void testRepaymentPlan_ArithmeticException() {
		Mockito.when(repaymentPlanService.getRepaymentPlan(Mockito.any(LoanDetails.class)))
				.thenThrow(ArithmeticException.class);
		assertThrows(ArithmeticException.class, () -> {
			repaymentPlanController.generateRepaymentPlan(getLoanDetails());
		});

	}

	private LoanDetails getLoanDetails() {
		return new LoanDetails(5000, 5, 24, LocalDate.now());
	}

}
