package com.test.repaymentplan.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RepaymentPlanControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	public final ResponseEntity<ResponseMessage> inputValidationException(ValidationException ex, WebRequest request) {
		ResponseMessage error = new ResponseMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ProcessingException.class)
	public final ResponseEntity<ResponseMessage> handleProcessingException(ProcessingException ex,
			WebRequest request) {
		ResponseMessage error = new ResponseMessage("Error while calculating Repayments " + ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ArithmeticException.class)
	public final ResponseEntity<ResponseMessage> handleArithmeticException(Exception ex, WebRequest request) {
		ResponseMessage error = new ResponseMessage("Arithematic exception " + ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler
	public final ResponseEntity<ResponseMessage> handleAllExceptions(Exception ex, WebRequest request) {
		ResponseMessage error = new ResponseMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
