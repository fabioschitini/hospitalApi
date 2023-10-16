package com.example.hospital.dto;

import com.example.hospital.models.Especialidade;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


public record FormMedico(@NotNull String nome,@NotNull String email,@NotNull String telefone,@NotNull String crm,@Valid @NotNull FormEndereco endereco , @NotNull Especialidade especialidade) {
	
		

}


