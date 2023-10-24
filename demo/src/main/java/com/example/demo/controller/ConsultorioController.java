package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.dto.ConsultaDto;
import com.example.demo.dto.FormConsulta;
import com.example.demo.dto.MedicoDto;
import com.example.demo.dto.PacienteDto;
import com.example.demo.model.Consulta;
import com.example.demo.model.MotivoCancelamento;
import com.example.demo.service.ConsultaService;
import com.example.demo.service.MedicoFeignService;

import jakarta.validation.Valid;





@RestController 
@RequestMapping("/consultas")
@Component
public class ConsultorioController { 
	
	@Autowired
	private ConsultaService consultaService;
	
	
	@GetMapping
	public List<ConsultaDto> listarTodo(){
		return consultaService.buscarTodos();

	}
	
	
	@GetMapping("/{id}")
	public ConsultaDto listarTodos(@PathVariable Long id){
		return consultaService.pegarConsultaPelaId(id);

	}
	 
	@PostMapping
	public ResponseEntity<ConsultaDto> cadastrar(@RequestBody @Valid FormConsulta dados) {
		Consulta consulta; 
		consulta = consultaService.cadastrar(dados);
		return new ResponseEntity<ConsultaDto>( new ConsultaDto(consulta,consultaService.fetchMedico(consulta.getMedico()),consultaService.fetchPaciente(consulta.getPaciente())) ,HttpStatus.CREATED);
	}
	/*
	@GetMapping("/buscarPorNome")
	public List<DadosPost> listarPorTitulo(String titulo){
		return postService.buscarPorTitulo(titulo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> apagar(@PathVariable Long id) {
		postService.apagar(id);
		return  new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	*/  

}
