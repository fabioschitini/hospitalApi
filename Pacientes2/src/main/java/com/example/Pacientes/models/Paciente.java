package com.example.Pacientes.models;

import com.example.Pacientes.dto.FormPaciente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name="pacientes")
@NoArgsConstructor

public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String cpf;
	@OneToOne
	private Endereco endereco;
	private boolean apagado;

	public Paciente() {
		
	}
	
	public Paciente(FormPaciente dados) {
		this.nome=dados.nome();
		this.email=dados.email();
		this.telefone=dados.telefone();
		this.cpf=dados.cpf();
		this.setApagado(false);
	}
	
	public Endereco getEndereco() {
		return this.endereco;
	}
	public void setEndereco(Endereco endereco2) {
		this.endereco = endereco2;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public boolean isApagado() {
		return apagado;
	}

	public void setApagado(boolean apagado) {
		this.apagado = apagado;
	}
	

}
