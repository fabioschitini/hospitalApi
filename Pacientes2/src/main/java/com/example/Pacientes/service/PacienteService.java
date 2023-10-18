package com.example.Pacientes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pacientes.dto.DadosListadosDePacientes;
import com.example.Pacientes.dto.DadosPacientes;
import com.example.Pacientes.dto.FormPaciente;
import com.example.Pacientes.dto.FormPacienteUpdate;
import com.example.Pacientes.models.Endereco;
import com.example.Pacientes.models.Paciente;
import com.example.Pacientes.repositorys.EnderecoRepository;
import com.example.Pacientes.repositorys.PacienteRepository;


@Service
public class PacienteService {
	
	@Autowired
	private PacienteRepository pacienteRepository; 
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public List<DadosPacientes> converterDadosPacientes(List<Paciente> lista){
		if(lista.isEmpty()) return null;
		return lista.stream()
				.filter(c -> c.isApagado()==false)
				.map(DadosPacientes::new).collect(Collectors.toList());
	}
	
	public List<DadosListadosDePacientes> converterOrdenado(List<Paciente> lista){
		if(lista.isEmpty()) return null;
		return lista.stream().sorted((object1, object2) -> object1.getNome().compareTo(object2.getNome()))
				.map(DadosListadosDePacientes::new).collect(Collectors.toList());
	}
	
	public List<DadosPacientes> buscarTodos(){
		return  this.converterDadosPacientes(this.pacienteRepository.findAll());
	}
	
	public List<DadosListadosDePacientes> buscarOrdenado(){
		return  this.converterOrdenado(this.pacienteRepository.findAll());
	}
	
	public DadosPacientes getPelaId(Long id){
		return  new DadosPacientes(this.pacienteRepository.getById(id));
	}
	
	public Paciente cadastrar(FormPaciente dados) {
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
	
	public Paciente atualizar(FormPacienteUpdate dados,Long id) {
		@SuppressWarnings("deprecation")
		Paciente paciente=pacienteRepository.getById(id);
		@SuppressWarnings("deprecation")
		Endereco endereco=new Endereco(dados.endereco());
		
		paciente.setNome(dados.nome());
		paciente.setTelefone(dados.telefone());
		endereco.setComplemento(dados.endereco().complemento());
		endereco.setNumero(dados.endereco().numero());
		paciente.setEndereco(endereco);
		enderecoRepository.save(endereco);
		pacienteRepository.save(paciente);
		return paciente;
	}
	
	
	public Paciente deletar(Long id) {
		@SuppressWarnings("deprecation")
		Paciente paciente=pacienteRepository.getById(id);
		@SuppressWarnings("deprecation")
		Endereco endereco=enderecoRepository.getById(id);
		paciente.setApagado(true);
		endereco.setApagado(true);
		enderecoRepository.save(endereco);
		pacienteRepository.save(paciente);
		return paciente;
	}


}
