package com.ifba.consultas.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class DataConsulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String dia;
	private String mes;
	private String ano;
	private String hora;
	
	public Long getId() {
		return id;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getMinuto() {
		return minuto;
	}
	public void setMinuto(String minuto) {
		this.minuto = minuto;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private String minuto;
	
	
	/* public Endereco(FormEndereco formEndereco) {
		this.bairro=formEndereco.bairro();
		this.cidade=formEndereco.cidade();
		this.UF=formEndereco.UF();
		this.cep=formEndereco.cep();
		this.logradouro=formEndereco.logradouro();
		this.setApagado(false);
	} */
	
}
