package com.example.demo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FormConsulta(   @Min(value = 1, message = "Invalid Age: Equals to zero or Less than zero")
@NotNull Long medico,@Min(value = 1, message = "Invalid Age: Equals to zero or Less than zero") @NotNull Long paciente, @Valid @NotNull FormDataDeConsulta dataConsulta ) {

}  
 