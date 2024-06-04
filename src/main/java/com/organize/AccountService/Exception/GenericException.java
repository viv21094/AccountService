package com.organize.AccountService.Exception;

import java.net.http.HttpHeaders;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.organize.AccountService.DTO.ErrorResponseDTO;

@ControllerAdvice
public class GenericException {
	
	   @ExceptionHandler(MethodArgumentNotValidException.class)
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
			Map<String, String> validationErrors = new HashMap<String, String>();
			List<ObjectError> listError = ex.getBindingResult().getAllErrors();
			for (ObjectError li : listError) {
				String fieldName = ((FieldError) li).getField();
				validationErrors.put(fieldName, li.getDefaultMessage());
			}
			return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
	    }
	


	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception exception, WebRequest webRequest) {
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
				webRequest.getDescription(false),
				HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AccountExistException.class)
	public ResponseEntity<ErrorResponseDTO> accountExistException(AccountExistException accountExistException,
			WebRequest webRequest) {
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
				webRequest.getDescription(false),
				HttpStatus.BAD_REQUEST, accountExistException.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDTO> resourceNotFoundException(
			ResourceNotFoundException resourceNotFoundException, WebRequest webRequest) {
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
				webRequest.getDescription(false),
				HttpStatus.BAD_REQUEST, resourceNotFoundException.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
	}
}