package com.example.demo.dto;

import com.example.demo.model.MotivoCancelamento;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record FormDesmacar(@Min(value = 1, message = "Id da Consulta invalida:Tem que ser maior que zero") @NotNull Long consulta  ,@NotNull MotivoCancelamento motivo
		) {

} 
