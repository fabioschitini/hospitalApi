package com.example.Pacientes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Pacientes.dto.DadosPacientes;
import com.example.Pacientes.dto.DadosParaConsulta;
import com.example.Pacientes.dto.DadosEndereco;
import com.example.Pacientes.dto.DadosListadosDePacientes;
import com.example.Pacientes.dto.FormPaciente;
import com.example.Pacientes.dto.FormPacienteUpdate;
import com.example.Pacientes.exceptions.PacienteNaoEstaNoSistemaException;
import com.example.Pacientes.models.Paciente;
import com.example.Pacientes.service.EnderecoService;
import com.example.Pacientes.service.PacienteService;

import jakarta.validation.Valid;


@RestController 
@RequestMapping("/pacientes")
public class PacienteController { 
	 
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private EnderecoService enderecoService;

	
	@GetMapping
	public List<DadosListadosDePacientes> listarTodos(){
		return pacienteService.buscarOrdenado();
	} 
	
	 
	@GetMapping("/{id}")
	public DadosPacientes getPelaId(@PathVariable Long id) {
		return pacienteService.getPelaId(id);
	}
	 
	@GetMapping("/enderecos")
	public List<DadosEndereco> listarTodosEnderecos(){
		return enderecoService.buscarTodosEnderecos();
	} 
	
	@GetMapping("/api")
	public List<DadosPacientes> listarTodosOrdenado(){
		return pacienteService.buscarTodos();
	} 
	 
	@PostMapping
	public ResponseEntity<DadosPacientes> cadastrar(@RequestBody @Valid FormPaciente dados)  throws  MethodArgumentNotValidException,HttpMessageNotReadableException {
		Paciente paciente; 
		paciente = pacienteService.cadastrar(dados);
		return new ResponseEntity<DadosPacientes>( new DadosPacientes(paciente) ,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DadosPacientes> atualizar(@RequestBody @Valid FormPacienteUpdate dados,@PathVariable Long id) throws PacienteNaoEstaNoSistemaException {
		Paciente paciente;
		paciente = pacienteService.atualizar(dados,id); 
		return new ResponseEntity<DadosPacientes>( new DadosPacientes(paciente) ,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) throws PacienteNaoEstaNoSistemaException {
		pacienteService.deletar(id);
		return "Deletado com Sucesso";
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
