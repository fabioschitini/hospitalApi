package com.example.hospital.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record FormMedicoUpdate(@NotNull String nome,@NotNull String telefone,@Valid @NotNull FormEndereco endereco) {

}
