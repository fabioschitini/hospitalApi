package com.example.Pacientes.dto;

import com.example.Pacientes.models.Endereco;
import com.example.Pacientes.models.Paciente;

public record DadosPacientes(Long id,  String nome, String email,String telefone,String cpf,Endereco endereco) {
	
	public DadosPacientes(Paciente paciente) {
		this(paciente.getId(),paciente.getNome(),paciente.getEmail(), paciente.getTelefone(),paciente.getCpf(),paciente.getEndereco());
	}

}
