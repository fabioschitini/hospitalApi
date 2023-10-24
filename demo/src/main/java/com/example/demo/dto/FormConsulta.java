package com.example.demo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record FormConsulta( @NotNull Long medico, @NotNull Long paciente, @Valid @NotNull FormDataDeConsulta dataConsulta, @NotNull String teste ) {

}
 