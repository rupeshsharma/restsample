package com.my.sample.config.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> handleDracoChangePasswordException(AccessDeniedException e, HttpServletRequest request) {
		ErrorDetail errorDetail = createErrorDetail(e.getStatus(), "Authentication Exception", e);
		return new ResponseEntity<>(errorDetail, null, HttpStatus.UNAUTHORIZED);
	}

	// If no handler is found for an exception then this will get called.
	// Important : Keep this handler last in order else it will override a
	// specific exception handler defined after it.
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e, HttpServletRequest request) {
		ErrorDetail errorDetail = createErrorDetail(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Exception", e);
		return new ResponseEntity<>(errorDetail, null, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Override
	protected ResponseEntity<Object> handleNoSuchRequestHandlingMethod(NoSuchRequestHandlingMethodException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		pageNotFoundLogger.warn(ex.getMessage());
		ErrorDetail errorDetail = this.createErrorDetail(status, "No Such Request Handling Method found", ex);
		return handleExceptionInternal(ex, errorDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		pageNotFoundLogger.warn(ex.getMessage());
		ErrorDetail errorDetail = this.createErrorDetail(status, "Http Request Method Not Supported", ex);
		return handleExceptionInternal(ex, errorDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetail errorDetail = this.createErrorDetail(status, "Http Media Type Not Supported", ex);
		return handleExceptionInternal(ex, errorDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetail errorDetail = this.createErrorDetail(status, "Http Media Type Not Acceptable", ex);
		return handleExceptionInternal(ex, errorDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetail errorDetail = this.createErrorDetail(status, "Missing Servlet Request Parameter", ex);
		return handleExceptionInternal(ex, errorDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetail errorDetail = this.createErrorDetail(status, "Servlet Request Binding Exception", ex);
		return handleExceptionInternal(ex, errorDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetail errorDetail = this.createErrorDetail(status, "Conversion Not Supported", ex);
		return handleExceptionInternal(ex, errorDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDetail errorDetail = this.createErrorDetail(status, "Type Mismatch", ex);
		return handleExceptionInternal(ex, errorDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetail errorDetail = this.createErrorDetail(status, "Http Message No tReadable", ex);
		return handleExceptionInternal(ex, errorDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetail errorDetail = this.createErrorDetail(status, "Http Message Not Writable", ex);
		return handleExceptionInternal(ex, errorDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetail errorDetail = this.createErrorDetail(status, "Missing Servlet Request Part", ex);
		return handleExceptionInternal(ex, errorDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		ErrorDetail errorDetail = this.createErrorDetail(status, "Bind Exception", ex);
		return handleExceptionInternal(ex, errorDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDetail errorDetail = this.createErrorDetail(status, "No Handler Found Exception", ex);
		return handleExceptionInternal(ex, errorDetail, headers, status, request);
	}

	private ErrorDetail createErrorDetail(HttpStatus status, String title, Exception ex) {
		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(status.value());
		errorDetail.setTitle(title);
		errorDetail.setDetail(ex.getMessage());
		errorDetail.setDeveloperMessage(ex.getClass().getName());
		return errorDetail;
	}

	private ErrorDetail createErrorDetail(int status, String title, Exception ex) {
		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(status);
		errorDetail.setTitle(title);
		errorDetail.setDetail(ex.getMessage());
		errorDetail.setDeveloperMessage(ex.getClass().getName());
		return errorDetail;
	}

}
