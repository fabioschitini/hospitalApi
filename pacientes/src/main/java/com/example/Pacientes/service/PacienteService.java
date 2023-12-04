package com.example.Pacientes.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pacientes.dto.DadosListadosDePacientes;
import com.example.Pacientes.dto.DadosPacientes;
import com.example.Pacientes.dto.DadosParaConsulta;
import com.example.Pacientes.dto.FormPaciente;
import com.example.Pacientes.dto.FormPacienteUpdate;
import com.example.Pacientes.exceptions.PacienteNaoEstaNoSistemaException;
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
				.sorted((object1, object2) -> object1.getNome().compareTo(object2.getNome()))
				.map(DadosPacientes::new).collect(Collectors.toList());
	}
	
	public List<DadosListadosDePacientes> converterOrdenado(List<Paciente> lista){
		if(lista.isEmpty()) return null;
		return lista.stream()
				.filter(c -> c.isApagado()==false)
				.sorted((object1, object2) -> object1.getNome().compareTo(object2.getNome()))
				.map(DadosListadosDePacientes::new).collect(Collectors.toList());
	}
	
	public List<DadosPacientes> buscarTodos(){
		return  this.converterDadosPacientes(this.pacienteRepository.findAll());
	}
	
	public List<DadosListadosDePacientes> buscarOrdenado(){
		return  this.converterOrdenado(this.pacienteRepository.findAll());
	}
	

	@SuppressWarnings("deprecation")
	public DadosPacientes getPelaId(Long id){
		java.util.Optional<Paciente> op=pacienteRepository.findById(id);
		if(op.isPresent() && op.get().isApagado()==false) {
			return new DadosPacientes(this.pacienteRepository.getById(id));
		}
		else return null;
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
	
	public Paciente atualizar(FormPacienteUpdate dados,Long id) throws PacienteNaoEstaNoSistemaException  {
		@SuppressWarnings("deprecation")
		Paciente paciente=pacienteRepository.getById(id);
		Endereco endereco=new Endereco(dados.endereco());
		Optional<Paciente> op=pacienteRepository.findById(id);
		if(op.isPresent()) {
			paciente.setNome(dados.nome());
			paciente.setTelefone(dados.telefone());
			endereco.setComplemento(dados.endereco().complemento());
			endereco.setNumero(dados.endereco().numero());
			paciente.setEndereco(endereco);
			enderecoRepository.save(endereco);
			pacienteRepository.save(paciente);
			return paciente;
		} 

		else throw new PacienteNaoEstaNoSistemaException("Paciente Nao está no sistema");
	}
	
	
	public void deletar(Long id) throws PacienteNaoEstaNoSistemaException {
		@SuppressWarnings("deprecation")
		Paciente paciente=pacienteRepository.getById(id);
		Optional<Paciente> op=pacienteRepository.findById(id);
		if(op.isPresent()) {
			@SuppressWarnings("deprecation")
			Endereco endereco=enderecoRepository.getById(id);
			paciente.setApagado(true);
			endereco.setApagado(true);
			enderecoRepository.save(endereco);
			pacienteRepository.save(paciente);
		} 
		else  throw new PacienteNaoEstaNoSistemaException("Paciente Nao está no sistema");
		}


}
