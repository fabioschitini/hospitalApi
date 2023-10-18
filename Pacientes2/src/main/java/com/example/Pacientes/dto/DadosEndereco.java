package com.example.Pacientes.dto;

import com.example.Pacientes.models.Endereco;

public record DadosEndereco(Long id, String logradouro, String numero, String uf, String cep, String cidade) {
	
	public DadosEndereco(Endereco endereco) {
		this(endereco.getId(),endereco.getLogradouro(),endereco.getNumero(), endereco.getUF(),endereco.getCep(),endereco.getCidade());
	}


}
