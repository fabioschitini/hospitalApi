package com.example.Pacientes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pacientes.dto.DadosEndereco;
import com.example.Pacientes.dto.FormEndereco;
import com.example.Pacientes.models.Endereco;
import com.example.Pacientes.repositorys.EnderecoRepository;


@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	
	public List<DadosEndereco> converterListaEnderecos(List<Endereco> lista){
		if(lista.isEmpty()) return null;
		return lista.stream().map(DadosEndereco::new).collect(Collectors.toList());
	}
	
	public List<DadosEndereco> buscarTodosEnderecos(){
		return  this.converterListaEnderecos(this.enderecoRepository.findAll());
	}
	
	
	public Endereco cadastrarEndereco(FormEndereco FormEndereco) {
		Endereco endereco=new Endereco(FormEndereco); 
		endereco.setNumero(FormEndereco.numero());
		endereco.setComplemento(FormEndereco.complemento());
		enderecoRepository.save(endereco);
		return endereco;
	}

}
