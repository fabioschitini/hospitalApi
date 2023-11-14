package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.PacienteDto;

@FeignClient(value="paciente",url="http://localhost/paciente-ms/pacientes")
@Service
public interface PacienteFeignService {
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
    PacienteDto getPaciente(@PathVariable Long id);

}
