package com.example.hospital.dto;

import com.example.hospital.models.Especialidade;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


public record FormMedico(@NotNull(message = "Nome invalido,não pode ser nulo") String nome,
		@NotNull (message = "Email invalido,não pode ser nulo")  String email,
		@NotNull (message = "Telefone invalido,não pode ser nulo")  String telefone,
		@NotNull (message = "Crm invalido,não pode ser nulo")  String crm,
		@Valid @NotNull (message = "Endereço invalido,não pode ser nulo")  FormEndereco endereco, 
		@NotNull (message = "Especialidade invalido,não pode ser nulo")  Especialidade especialidade) {
}


