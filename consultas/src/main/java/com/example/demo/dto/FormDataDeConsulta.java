package com.example.demo.dto;

import com.example.demo.model.DiaDaSemana;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotNull;

public record FormDataDeConsulta( 
@NotNull(message = "Ano Invalido,não pode ser nulo") @Min(value = 2023, message = "Ano Invalid0: tem que ser maior ou igual a 2023")
@Max(value = 2025, message = "Ano Invalido: Tem que ser menor ou igual a 2025") int ano,
@NotNull(message = "Mes Invalido,não pode ser nulo")  @Min(value = 1, message = "Hora Invalida: tem que ser maior ou igual a 71")
@Max(value = 12, message = "Hora Invalida: Tem que ser menor ou igual a 12") int mes,
@NotNull(message = "Dia Invalido,não pode ser nulo") int dia,
@NotNull(message = "dia_da_semana  Invalido,não pode ser nulo") DiaDaSemana dia_da_semana, 
@NotNull(message = "hora Invalido,não pode ser nulo")     @Min(value = 7, message = "Hora Invalida: tem que ser maior ou igual a 7")
@Max(value = 19, message = "Hora Invalida: Tem que ser menor ou igual a 19") int hora,  
@NotNull(message = "Minuto Invalido,não pode ser nulo")  @Min(value = 0, message = "Minuto Invalido: tem que ser maior ou igual a 0")
@Max(value = 60, message = "Minuto Invalido: Tem que ser menor ou igual a 60") int minuto) {

}  
