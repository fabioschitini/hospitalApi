package com.example.hospital.dto;

import com.example.hospital.models.Especialidade;
import com.example.hospital.models.Medico;

public record DadosListaPacientes(String nome, String email,String crm,Especialidade especialidade) {

	
	public DadosListaPacientes(Medico medico) {
		this(medico.getNome(),medico.getEmail(),medico.getCrm(),medico.getEspecialidade());
	}
	
}