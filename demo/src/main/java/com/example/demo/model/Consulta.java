package com.example.demo.model;



import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

public class Consulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private DataConsulta data;
	private boolean cancelado;
	private Long Medico;
	private Long Paciente;
	@Enumerated(EnumType.STRING)
	private MotivoCancelamento motivo;


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
	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}
	public Long getMedico() {
		return Medico;
	}
	public void setMedico(Long medico) {
		Medico = medico;
	}
	public Long getPaciente() {
		return Paciente;
	}
	public void setPaciente(Long paciente) {
		Paciente = paciente;
	}
	public MotivoCancelamento getMotivo() {
		return motivo;
	}
	public void setMotivo(MotivoCancelamento motivo) {
		this.motivo = motivo;
	}


	

}