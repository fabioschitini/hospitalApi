package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity(name="datas")
@NoArgsConstructor


public class DataConsulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String dia;
	private String mes;
	private String ano;
	private int hora;
	private int minuto;


	
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
	public int getHora() {
		return hora;
	}
	public void setHora(int hora) {
		this.hora = hora;
	}
	public int getMinuto() {
		return minuto;
	}
	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/* public Endereco(FormEndereco formEndereco) {
		this.bairro=formEndereco.bairro();
		this.cidade=formEndereco.cidade();
		this.UF=formEndereco.UF();
		this.cep=formEndereco.cep();
		this.logradouro=formEndereco.logradouro();
		this.setApagado(false);
	} */
	
}
