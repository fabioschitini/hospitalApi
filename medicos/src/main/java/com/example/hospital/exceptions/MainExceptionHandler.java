package com.example.hospital.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice 

public class MainExceptionHandler {
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
	        List<String> errors = ex.getBindingResult().getFieldErrors()
	                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
	        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	    }

	    private Map<String, List<String>> getErrorsMap(List<String> errors) {
	        Map<String, List<String>> errorResponse = new HashMap<>();
	        errorResponse.put("errors", errors);
	        return errorResponse;
	    }
	    
	    
	    @ExceptionHandler(HttpMessageNotReadableException.class)
	    public ResponseEntity<HashMap<String, String>> handleException(HttpMessageNotReadableException exception, HttpServletRequest request) {
	    	HashMap<String, String> errors=new HashMap<String,String>();
	    	String str=exception.getMessage();
	    	str.split("(?=Enum:|rk)");
			errors.put("mensagem",str);
	        return new ResponseEntity<HashMap<String,String>>(errors, HttpStatus.BAD_REQUEST);
	    }
	    
		@ExceptionHandler(MedicoNaoEstaNoSistemaException.class)
		public ResponseEntity<?> handleMedicoNaoEstaNoSistemaException(){
			Map<String,String> errors=new HashMap<String,String>();
			errors.put("mensagem","Medico nao encontrado");
			System.err.println("Medico nao encontrado");
			return new ResponseEntity<>( errors,HttpStatus.BAD_REQUEST);
		}

}
