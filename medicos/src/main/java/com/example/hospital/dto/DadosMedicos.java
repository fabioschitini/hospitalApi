package com.example.hospital.dto;


import com.example.hospital.models.Endereco;
import com.example.hospital.models.Especialidade;
import com.example.hospital.models.Medico;



public record DadosMedicos(Long id,  String nome, String email,String telefone,String crm,Endereco endereco, Especialidade especialidade) {
	
	public DadosMedicos(Medico medico) {
		this(medico.getId(),medico.getNome(),medico.getEmail(), medico.getTelefone(),medico.getCrm(),medico.getEndereco(),medico.getEspecialidade());
	}

} 
