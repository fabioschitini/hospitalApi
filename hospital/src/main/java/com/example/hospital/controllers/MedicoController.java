package com.example.hospital.controllers;

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

import com.example.hospital.dto.DadosMedicos;
import com.example.hospital.dto.DadosEndereco;
import com.example.hospital.dto.DadosListadosDeMedico;
import com.example.hospital.dto.FormMedico;
import com.example.hospital.dto.FormMedicoUpdate;
import com.example.hospital.models.Medico;
import com.example.hospital.service.EnderecoService;
import com.example.hospital.service.MedicoService;

import jakarta.validation.Valid;


@RestController 
@RequestMapping("/medicos")
public class MedicoController { 
	 
	@Autowired
	private MedicoService medicoService;
	@Autowired
	private EnderecoService enderecoService;

	
	@GetMapping
	public List<DadosMedicos> listarTodos(){
		return medicoService.buscarTodos();
	} 
	
	@GetMapping("/{id}")
	public DadosMedicos getPelaId(@PathVariable Long id) {
		return medicoService.getPelaId(id);
	}
	
	
	@GetMapping("/enderecos")
	public List<DadosEndereco> listarTodosEnderecos(){
		return enderecoService.buscarTodosEnderecos();
	} 
	
	@GetMapping("/ordenado")
	public List<DadosListadosDeMedico> listarTodosOrdenado(){
		return medicoService.buscarOrdenado();
	} 
	
	@PostMapping
	public ResponseEntity<DadosMedicos> cadastrar(@RequestBody @Valid FormMedico dados) {
		Medico medico;
		medico = medicoService.cadastrar(dados);
		return new ResponseEntity<DadosMedicos>( new DadosMedicos(medico) ,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DadosMedicos> atualizar(@RequestBody @Valid FormMedicoUpdate dados,@PathVariable Long id) {
		Medico medico;
		medico = medicoService.atualizar(dados,id);
		return new ResponseEntity<DadosMedicos>( new DadosMedicos(medico) ,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) {
		Medico medico;
		medico = medicoService.deletar(id);
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
