package com.example.hospital.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record FormMedicoUpdate(@NotNull(message = "Nome invalido,não pode ser nulo") String nome,
		@NotNull (message = "Telefone invalido,não pode ser nulo") String telefone,
		@Valid @NotNull (message = "Endereco invalido,não pode ser nulo") FormEndereco endereco) {

}
