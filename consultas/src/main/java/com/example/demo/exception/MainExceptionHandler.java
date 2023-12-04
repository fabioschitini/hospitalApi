package com.example.demo.exception;

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
public class MainExceptionHandler  {
	
	
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
	
	@ExceptionHandler(PacienteNaoEstaNoSistemaException.class)
	public ResponseEntity<?> handlePacienteNaoEstaNoSistemaException(){
		Map<String,String> errors=new HashMap<String,String>();
		errors.put("mensagem","Paciente nao encontrado");
		System.err.println("Paciente nao encontrado");
		return new ResponseEntity<>( errors,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MenosDe30MinutosException.class)
	public ResponseEntity<?> handleMenosDe30MinutosException(){
		Map<String,String> errors=new HashMap<String,String>();
		errors.put("mensagem","As consulta tem que ser marcadas com no minimo 30 minuto de antecendencia");
		System.err.println("As consulta tem que ser marcadas com no minimo 30 minuto de antecendencia");
		return new ResponseEntity<>( errors,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(PacienteJaMarcouNoDiaException.class)
	public ResponseEntity<?> handlePacienteJaMarcouNoDiaException(){
		Map<String,String> errors=new HashMap<String,String>();
		errors.put("mensagem","Paciente já tem uma consulta marcada para esse dia");
		System.err.println("Paciente já tem uma consulta marcada para esse dia");
		return new ResponseEntity<>( errors,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MedicoEstaEmConsultaException.class)
	public ResponseEntity<?> handleMedicoEstaEmConsultaException(){
		Map<String,String> errors=new HashMap<String,String>();
		errors.put("mensagem","Medico está em consulta nesse horario");
		System.err.println("Medico está em consulta nesse horario");
		return new ResponseEntity<>( errors,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MarcouConsultaNoPassadoException.class)
	public ResponseEntity<?> handleMarcouConsultaNoPassadoException(){
		Map<String,String> errors=new HashMap<String,String>();
		errors.put("mensagem","Marcou consulta no passado");
		System.err.println("Marcou consulta no passado");
		return new ResponseEntity<>( errors,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CancelarCom1DiaDeAntecedenciaException.class)
	public ResponseEntity<?> handleCancelarCom1DiaDeAntecedenciaException(){
		Map<String,String> errors=new HashMap<String,String>();
		errors.put("mensagem","Voce so pode cancelar uma consulta com um dia de antecedencia");
		System.err.println("Voce so pode cancelar uma consulta com um dia de antecedencia");
		return new ResponseEntity<>( errors,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConsultaNaoEncontradaException.class)
	public ResponseEntity<?> handleConsultaNaoEncontradaException(){
		Map<String,String> errors=new HashMap<String,String>();
		errors.put("mensagem","Consulta nao encontrada");
		System.err.println("Consulta nao encontrada");
		return new ResponseEntity<>( errors,HttpStatus.BAD_REQUEST);
	}
	
	

}
