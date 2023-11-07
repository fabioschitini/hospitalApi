package com.example.hospital.dto;

import jakarta.validation.constraints.NotNull;

public record FormEndereco( @NotNull(message = "Logradouro invalido,não pode ser nulo") String logradouro,
		String numero,
		String complemento,
		@NotNull(message = "Bairro invalido,não pode ser nulo") String bairro,
		@NotNull(message = "Cidade invalido,não pode ser nulo") String cidade, 
		@NotNull(message = "UF invalido,não pode ser nulo") String UF,
		@NotNull(message = "Cep invalido,não pode ser nulo") String cep
		) {

}
