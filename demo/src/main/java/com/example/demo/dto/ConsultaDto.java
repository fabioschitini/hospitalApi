package com.example.demo.dto;

import com.example.demo.model.Consulta;
import com.example.demo.model.DataConsulta;
import com.example.demo.model.MotivoCancelamento;

public record ConsultaDto(Long id,  MedicoDto medico, PacienteDto paciente,MotivoCancelamento motivo,boolean cancelado,DataConsulta data) {

	public ConsultaDto(Consulta consulta,MedicoDto medico,PacienteDto paciente) {
		this(consulta.getId(),medico,paciente, consulta.getMotivo(),consulta.isCancelado(),consulta.getData());
	}

	
}
