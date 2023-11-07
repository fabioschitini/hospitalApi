package com.example.demo.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.MedicoDto;


@FeignClient(value="medico",url="http://10.0.0.5:8082/medico-ms/medicos/consulta")
@Service
public interface MedicoFeignService {
    @RequestMapping(method = RequestMethod.GET, value = "")
    List<MedicoDto> getAllMedicos(); 
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    MedicoDto getMedicos(@PathVariable Long id);


}
 