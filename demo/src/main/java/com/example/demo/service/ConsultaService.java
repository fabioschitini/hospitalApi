package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ConsultaService {
	
	

	
	public String yolo() {
		return "medicoService.getMedicos(1).toString()";
	}
	/*
	 * 
	 * 
	public DadosMedico getDadosMedicos() {
		
		WebClient.Builder builder=WebClient.builder();
		String url="http://10.0.0.5:8082/medico-ms/medicos/1";
		DadosMedico dados=builder.build()
				.get()
				.uri(url)
				.retrieve()
				.bodyToMono(DadosMedico.class)
				.block();
		
		return dados;
	} */

}
