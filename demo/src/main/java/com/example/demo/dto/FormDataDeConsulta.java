package com.example.demo.dto;

import jakarta.annotation.Nonnull;
//import jakarta.validation.constraints.NotNull;

public record FormDataDeConsulta( @Nonnull String ano, @Nonnull String mes,
		@Nonnull int dia, @Nonnull String dia_da_semana,  @Nonnull int hora,  @Nonnull int minuto) {

}
