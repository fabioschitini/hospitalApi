package com.example.hospital.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospital.dto.DadosEndereco;
import com.example.hospital.dto.FormEndereco;
import com.example.hospital.models.Endereco;
import com.example.hospital.service.EnderecoService;

import jakarta.validation.Valid;


@RestController 
@RequestMapping("/medicos")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping("/enderecos")
	public List<DadosEndereco> listarTodosEnderecos(){
		return enderecoService.buscarTodosEnderecos();
	} 
	
	@PostMapping("/enderecos")
	public ResponseEntity<DadosEndereco> cadastrarEndereco(@RequestBody @Valid FormEndereco dados) {
		Endereco endereco;
		endereco = enderecoService.cadastrarEndereco(dados);
		return new ResponseEntity<DadosEndereco>( new DadosEndereco(endereco) ,HttpStatus.CREATED);
	}

}
