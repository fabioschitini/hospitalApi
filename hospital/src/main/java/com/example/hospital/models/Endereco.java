package com.example.hospital.models;

import com.example.hospital.dto.FormEndereco;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name="enderecos")
@NoArgsConstructor
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	public Long getId() {
		return id;
	}
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String UF;
	private String cep;
	

	
	public Endereco(FormEndereco endereco) {
		this.bairro=endereco.bairro();
		this.cidade=endereco.cidade();
		this.UF=endereco.UF();
		this.cep=endereco.cep();
		this.logradouro=endereco.logradouro();
	}
	
	public String toString() {
		return "Endedereço: "+this.logradouro+" "+this.numero+" "+ this.bairro+", "+this.complemento+"n/"+this.cidade+" "+this.UF+" "+this.cep;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		if(this.numero==null) this.numero="";
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		if(this.complemento==null) this.complemento="";
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUF() {
		return UF;
	}
	public void setUF(String uF) {
		UF = uF;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	
}
