package com.example.demo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FormConsulta(   @Min(value = 1, message = "Id do Medico invalida:Tem que ser maior que zero")
@NotNull Long medico,@Min(value = 1, message = "Id do Paciente invalida:Tem que ser maior que zero") @NotNull Long paciente, @Valid @NotNull FormDataDeConsulta dataConsulta
		
		) {

}  
 