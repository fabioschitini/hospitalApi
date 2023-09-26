package com.example.hospital.dto;

import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record FormEndereco( @RequestBody @Valid  @NotNull String logradouro, String numero,
		String complemento,@NotNull String bairro,@NotNull String cidade, @NotNull String UF,
		 @NotNull String cep
		) {

}
