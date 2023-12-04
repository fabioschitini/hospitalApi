package com.example.hospital.dto;

import com.example.hospital.models.Especialidade;
import com.example.hospital.models.Medico;

public record DadosListadosDeMedico(String nome, String email,String crm,Especialidade especialidade) {

	
	public DadosListadosDeMedico(Medico medico) {
		this(medico.getNome(),medico.getEmail(),medico.getCrm(),medico.getEspecialidade());
	}
	
}
