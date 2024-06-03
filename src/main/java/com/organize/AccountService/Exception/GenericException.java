package com.organize.AccountService.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.organize.AccountService.DTO.ErrorResponseDTO;

@ControllerAdvice
public class GenericException {
  
@ExceptionHandler(AccountExistException.class)
public ResponseEntity<ErrorResponseDTO>	accountExistException(AccountExistException accountExistException, WebRequest webRequest) {
	ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
			webRequest.getDescription(false), 
			HttpStatus.BAD_REQUEST, accountExistException.getMessage(), LocalDateTime.now());
	return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
}

@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ErrorResponseDTO>	resourceNotFoundException(ResourceNotFoundException resourceNotFoundException, WebRequest webRequest) {
	ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
			webRequest.getDescription(false), 
			HttpStatus.BAD_REQUEST, resourceNotFoundException.getMessage(), LocalDateTime.now());
	return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
}
}
