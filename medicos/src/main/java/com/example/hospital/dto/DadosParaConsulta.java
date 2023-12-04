package com.example.hospital.dto;

import com.example.hospital.models.Especialidade;
import com.example.hospital.models.Medico;

public record DadosParaConsulta(Long id,String nome, String email,String telefone,String crm, Especialidade especialidade) {
	
	public DadosParaConsulta(Medico medico) {
		this(medico.getId(),medico.getNome(),medico.getEmail(), medico.getTelefone(),medico.getCrm(),medico.getEspecialidade());
	}

}
