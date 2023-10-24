package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.dto.ConsultaDto;
import com.example.demo.dto.MedicoDto;
import com.example.demo.dto.PacienteDto;
import com.example.demo.model.Consulta;
import com.example.demo.repositories.ConsultaRepository;
import com.example.demo.repositories.DataConsultaRepository;


//import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ConsultaService {
	
	@Autowired
	private ConsultaRepository consultaRepository; 
	
	@Autowired
	private DataConsultaRepository enderecoRepository;
	
	@Autowired
	private MedicoFeignService medicoFeign;
	
	@Autowired
	private PacienteFeignService pacienteFeign;
	
	
	public List<ConsultaDto> converterDadosConsulta(List<Consulta> lista){
		if(lista.isEmpty()) return null;
		return lista.stream()
                .map( emp -> new ConsultaDto(emp, this.fetchMedico(emp.getMedico()), this.fetchPaciente(emp.getPaciente() )) )
                .collect(Collectors.toList());
	}
	
	public List<ConsultaDto> buscarTodos(){
		return  this.converterDadosConsulta(this.consultaRepository.findAll());
	}
	
	
	public MedicoDto fetchMedico(Long id) {
		MedicoDto medico=medicoFeign.getMedicos(id);
		System.out.println(medico+"\n\n *********************************************");
		System.out.println("\n\n ************************************************************");
		return medico;
	}
	
	public PacienteDto fetchPaciente(Long id) {
		PacienteDto paciente=pacienteFeign.getPaciente(id);
		System.out.println(paciente+"\n\n *********************************************");
		System.out.println("\n\n ************************************************************");
		return paciente;
	}
	
	public ConsultaDto pegarConsultaPelaId(Long id) {
		Consulta consulta=consultaRepository.getById(id);
		ConsultaDto consultaDados=new ConsultaDto(consulta,this.fetchMedico(consulta.getMedico()),this.fetchPaciente(consulta.getPaciente() ) );
		return consultaDados;
	}

	/*public Consulta cadastrar(FormPaciente dados) {
		Paciente paciente= new Paciente(dados);
		Endereco endereco=new Endereco(dados.endereco());
		endereco.setComplemento(dados.endereco().complemento());
		endereco.setNumero(dados.endereco().numero());
		paciente.setEndereco(endereco);
		enderecoRepository.save(endereco);
		pacienteRepository.save(paciente);

		//Optional<Endereco> op=enderecoRepository.findById(dados.endereco());
		//if(op.isPresent()) {
			//medico.setEndereco(op.get());
			//medicoRepository.save(medico);
			//return ; 
		//}
		return paciente; 
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
