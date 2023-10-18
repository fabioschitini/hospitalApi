package com.example.Pacientes.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record FormPacienteUpdate(@NotNull String nome,@NotNull String telefone,@Valid @NotNull FormEndereco endereco) {

}
