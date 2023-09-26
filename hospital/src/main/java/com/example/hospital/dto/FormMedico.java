package com.example.hospital.dto;

import org.springframework.web.bind.annotation.RequestBody;
import com.example.hospital.models.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


public record FormMedico( @RequestBody @Valid  @NotNull String nome,@NotNull String email,@NotNull String telefone,@NotNull String crm,@NotNull Long endereco, @NotNull Especialidade especialidade) {

}
