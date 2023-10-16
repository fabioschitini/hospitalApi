package com.example.Pacientes.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record FormEndereco( @NotNull String logradouro, String numero,
		String complemento, @NotNull String bairro,  @NotNull String cidade,  @NotNull String UF,
		 @NotNull String cep
		) {

}
