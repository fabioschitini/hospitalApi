package com.example.demo.dto;

import jakarta.annotation.Nonnull;
//import jakarta.validation.constraints.NotNull;

public record FormDataDeConsulta( @Nonnull String ano, String mes,
		String dia, @Nonnull String dia_da_semana,  @Nonnull String hora,  @Nonnull String minuto) {

}
