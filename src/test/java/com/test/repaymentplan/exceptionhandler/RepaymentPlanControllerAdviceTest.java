package com.test.repaymentplan.exceptionhandler;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.test.repaymentplan.exceptionhandler.ProcessingException;
import com.test.repaymentplan.exceptionhandler.RepaymentPlanControllerAdvice;
import com.test.repaymentplan.exceptionhandler.ResponseMessage;
import com.test.repaymentplan.exceptionhandler.ValidationException;

@ExtendWith(MockitoExtension.class)
class RepaymentPlanControllerAdviceTest {

	@InjectMocks
	private RepaymentPlanControllerAdvice repaymentPlanControllerAdvice;

	@Mock
	WebRequest webrequest;

	@Test
	void testhandleProcessingException() {
		ProcessingException processingException = new ProcessingException();
		ResponseEntity<ResponseMessage> handleProcessingException = repaymentPlanControllerAdvice
				.handleProcessingException(processingException, webrequest);
		assertNotNull(handleProcessingException);
		assertNotNull(handleProcessingException.getBody().getMessage());
	}

	@Test
	void testInputValidationException() {
		ValidationException validationException = new ValidationException();
		ResponseEntity<ResponseMessage> handleValidationException = repaymentPlanControllerAdvice
				.inputValidationException(validationException, webrequest);
		assertNotNull(handleValidationException);
		assertNull(handleValidationException.getBody().getMessage());
	}

	@Test
	void testInputValidationExceptionMessage() {
		ValidationException validationException = new ValidationException("invalid input");
		ResponseEntity<ResponseMessage> handleValidationException = repaymentPlanControllerAdvice
				.inputValidationException(validationException, webrequest);
		assertNotNull(handleValidationException);
		assertNotNull(handleValidationException.getBody().getMessage());
	}

	@Test
	void testhandleArithmeticException() {
		ArithmeticException arithmeticException = new ArithmeticException();
		ResponseEntity<ResponseMessage> handleArithmeticException = repaymentPlanControllerAdvice
				.handleArithmeticException(arithmeticException, webrequest);
		assertNotNull(handleArithmeticException);
		assertNotNull(handleArithmeticException.getBody().getMessage());
	}

}
