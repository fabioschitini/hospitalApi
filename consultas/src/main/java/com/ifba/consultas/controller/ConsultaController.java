package com.ifba.consultas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import jakarta.validation.Valid;

@RestController 
@RequestMapping("/consultas")
public class ConsultaController {
	/*
	@Autowired
	private PacienteService pacienteService;

	
	@GetMapping
	public List<DadosPacientes> listarTodos(){
		return pacienteService.buscarTodos();
	} 
	 */

}
