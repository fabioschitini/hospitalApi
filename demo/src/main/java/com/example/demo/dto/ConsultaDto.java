package com.example.demo.dto;

import com.example.demo.model.DataConsulta;
import com.example.demo.model.MotivoCancelamento;

public record ConsultaDto(Long id,  MedicoDto medico, PacienteDto paciente,MotivoCancelamento motivo,boolean cancelado,DataConsulta data) {

}
