package com.example.demo.dto;

public record ConsultaDto(Long id,  MedicoDto medico, PacienteDto paciente,MotivoCancelamento motivo,boolean cancelado,DataConsulta data) {

}
