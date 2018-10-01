/**
 * 
 */
package com.myapp.exception.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Application Exception Handler
 * 
 * @author Madhsuudan
 * @version : 1.0.0
 *
 */
@ControllerAdvice(basePackages = { "com.myapp.controller" })
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		final List<String> errors = new ArrayList<>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed", errors.toString());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method is used to throw any unexpected error within application
	 * 
	 * @param exception
	 *            the resorce exception object
	 * @param request
	 *            servlet request object
	 * @return the error detail
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleException(final Exception exception, final HttpServletRequest request) {
		return new ResponseEntity<>(
				new ErrorDetails(new Date(), exception.getCause().getMessage(), exception.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * This method is used to throw the generic exception when resource in not found
	 * 
	 * @param exception
	 *            resouces not found exception
	 * @param request
	 *            servlet request object
	 * @return the error detail
	 */
	@ExceptionHandler(ResourceException.class)
	public ResponseEntity<ErrorDetails> handleResourceException(final ResourceException exception,
			final HttpServletRequest request) {
		return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getErrorType(), exception.getMessage()),
				HttpStatus.NOT_FOUND);
	}

	/**
	 * This method is used to throw any unexpected error within application
	 * 
	 * @param exception
	 *            the aap exception object
	 * @param request
	 *            servlet request object
	 * @return the error detail
	 */
	@ExceptionHandler(AppServiceException.class)
	@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
	public ResponseEntity<ErrorDetails> handleAppException(final AppServiceException exception,
			final HttpServletRequest request) {
		return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getErrorType(), exception.getMessage()),
				HttpStatus.EXPECTATION_FAILED);
	}

	/**
	 * This method is used to throw any unexpected error within application
	 * 
	 * @param exception
	 *            the hibernate exception object
	 * @param request
	 *            servlet request object
	 * @return the error detail
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
	public ResponseEntity<ErrorDetails> handleHibernateException(final DataIntegrityViolationException exception,
			final HttpServletRequest request) {
		return new ResponseEntity<>(
				new ErrorDetails(new Date(), exception.getCause().getMessage(), "Record already present"),
				HttpStatus.EXPECTATION_FAILED);
	}
}
