package com.example.Pacientes.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


public record FormPaciente(@NotNull(message = "Nome invalido,não pode ser nulo") String nome
		,@NotNull (message = "Email invalido,não pode ser nulo")String email,
		@NotNull (message = "Telefone invalido,não pode ser nulo") String telefone,
		@NotNull (message = "Cpf invalido,não pode ser nulo") String cpf,
		@Valid @NotNull (message = "Endereco invalido,não pode ser nulo")  FormEndereco endereco ) {
		
}


