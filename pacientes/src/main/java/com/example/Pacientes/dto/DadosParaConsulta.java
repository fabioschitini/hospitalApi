package com.example.Pacientes.dto;

import com.example.Pacientes.models.Paciente;

public record DadosParaConsulta(Long id,String nome, String email,String telefone,String cpf) {
	
	public DadosParaConsulta(Paciente paciente) {
		this(paciente.getId(),paciente.getNome(),paciente.getEmail(), paciente.getTelefone(),paciente.getCpf());
	}

}
