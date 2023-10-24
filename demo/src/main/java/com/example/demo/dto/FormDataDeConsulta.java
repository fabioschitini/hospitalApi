package com.example.demo.dto;

//import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotNull;

public record FormDataDeConsulta( @NotNull String ano, @NotNull String mes,
		@NotNull int dia, @NotNull String dia_da_semana,  @NotNull int hora,  @NotNull int minuto) {

}
