package com.example.demo.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Nonnull;

public record FormConsulta( @Nonnull Long medico, Long paciente, @Validated @Nonnull FormDataDeConsulta dataConsulta ) {

}
