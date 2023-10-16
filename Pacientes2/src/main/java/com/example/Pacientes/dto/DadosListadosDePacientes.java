package com.example.Pacientes.dto;

import com.example.Pacientes.models.Paciente;

public record DadosListadosDePacientes(String nome, String email,String cpf) {

	
	public DadosListadosDePacientes(Paciente paciente) {
		this(paciente.getNome(),paciente.getEmail(),paciente.getCpf());
	}
	
}
