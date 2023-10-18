package com.example.Pacientes.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


public record FormPaciente(@NotNull String nome,@NotNull String email,@NotNull String telefone,@NotNull String cpf,@Valid @NotNull FormEndereco endereco ) {
		
}


