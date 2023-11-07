package com.example.demo.model;
 


import com.example.demo.dto.FormConsulta;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.NoArgsConstructor;

@Entity(name="consultas")
@NoArgsConstructor
	
public class Consulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private DataConsulta data;
	private boolean cancelado;
	private Long medico;
	private Long paciente;
	@Enumerated(EnumType.STRING)
	private MotivoCancelamento motivo;
	
	
	public Consulta(){}
	
	
	public Consulta(FormConsulta dados) {
		this.medico=dados.medico();
		this.paciente=dados.paciente();
		this.cancelado=false;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DataConsulta getData() {
		return data;
	}
	public void setData(DataConsulta data) {
		this.data = data;
	}
	public boolean isCancelado() {
		return cancelado;
	}
	public void setCancelado() {
		this.cancelado = true;
	}
	public Long getMedico() {
		return medico;
	}
	public void setMedico(Long medico) {
		this.medico = medico;
	}
	public Long getPaciente() {
		return paciente;
	}
	public void setPaciente(Long paciente) {
		this.paciente = paciente;
	}
	public MotivoCancelamento getMotivo() {
		return motivo;
	}
	public void setMotivo(MotivoCancelamento motivo) {
		this.motivo = motivo;
	}


	

}