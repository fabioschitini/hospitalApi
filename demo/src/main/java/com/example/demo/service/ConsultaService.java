package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.dto.ConsultaDto;
import com.example.demo.dto.FormConsulta;
import com.example.demo.dto.MedicoDto;
import com.example.demo.dto.PacienteDto;
import com.example.demo.exception.MedicoNaoEstaNoSistemaException;
import com.example.demo.exception.PacienteNaoEstaNoSistemaException;
import com.example.demo.model.Consulta;
import com.example.demo.model.DataConsulta;
import com.example.demo.repositories.ConsultaRepository;
import com.example.demo.repositories.DataConsultaRepository;


//import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ConsultaService {
	
	@Autowired
	private ConsultaRepository consultaRepository; 
	
	@Autowired
	private DataConsultaRepository dataRepository;
	
	@Autowired
	private MedicoFeignService medicoFeign;
	
	@Autowired
	private PacienteFeignService pacienteFeign;
	
	
	public List<ConsultaDto> converterDadosConsulta(List<Consulta> lista) {
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
		if(medico==null) {
			System.out.println("nuloooooooooooooooooooooooooooooooooooooooooooooooooooo \n \n"+"\n\n *********************************************");
			System.out.println("\n\n ************************************************************");
		}
		return medico;
	}
	
	public PacienteDto fetchPaciente(Long id) {
		PacienteDto paciente=pacienteFeign.getPaciente(id);
		//System.out.println(paciente+"\n\n *********************************************");
		//System.out.println("\n\n ************************************************************");
		return paciente;
	}
	
	public ConsultaDto pegarConsultaPelaId(Long id) {
		Consulta consulta=consultaRepository.getById(id);
		ConsultaDto consultaDados=new ConsultaDto(consulta,this.fetchMedico(consulta.getMedico()),this.fetchPaciente(consulta.getPaciente() ) );
		return consultaDados;
	}

	public Consulta cadastrar(FormConsulta dados) throws MedicoNaoEstaNoSistemaException, PacienteNaoEstaNoSistemaException {
		Consulta consulta= new Consulta(dados);
		DataConsulta data=new DataConsulta(dados.dataConsulta());
		consulta.setData(data);
		MedicoDto medico;
		//if(dados.medico()==null) medico=
		 medico=this.fetchMedico(consulta.getMedico());
		PacienteDto paciente=this.fetchPaciente(consulta.getPaciente());
		if(medico==null) throw new MedicoNaoEstaNoSistemaException("Medico nao esta cadastrado no sistema");
		if(paciente==null) throw new PacienteNaoEstaNoSistemaException("Paciente nao esta cadastrado no sistema");
		dataRepository.save(data);
		consultaRepository.save(consulta);

		//Optional<Endereco> op=enderecoRepository.findById(dados.endereco());
		//if(op.isPresent()) {
			//medico.setEndereco(op.get());
			//medicoRepository.save(medico);
			//return ; 
		//}
		return consulta; 
	}
	
	public boolean ChecarAntecendenciaDe30Minutos() {
		return true;
	}
	
	public boolean ChecarPacienteJaMarcouNoDia() {
		return true;
	}
	
	public boolean ChecarSeMedicoEstaDisponivel() {
		return true;
	}
	
	public String pegarMedicoAleatorio(List<MedicoDto> medicos) {
		return "yo";
	}
	/*
	 */

}
