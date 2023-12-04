package com.example.hospital.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospital.dto.DadosMedicos;
import com.example.hospital.dto.DadosParaConsulta;
import com.example.hospital.dto.DadosListadosDeMedico;

import com.example.hospital.dto.FormMedico;
import com.example.hospital.dto.FormMedicoUpdate;
import com.example.hospital.exceptions.MedicoNaoEstaNoSistemaException;
import com.example.hospital.models.Medico;
import com.example.hospital.models.Endereco;
import com.example.hospital.repositorys.EnderecoRepository;
import com.example.hospital.repositorys.MedicoRepository;


@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository; 
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public List<DadosMedicos> converterDadosMedicos(List<Medico> lista){ 
		if(lista.isEmpty()) return null;
		return lista.stream()
				.filter(c -> c.isApagado()==false)
				.map(DadosMedicos::new).collect(Collectors.toList());
	}
	
	public List<DadosListadosDeMedico> converterOrdenado(List<Medico> lista){
		if(lista.isEmpty()) return null;
		return lista.stream()
				.filter(c -> c.isApagado()==false) 
				.sorted((object1, object2) -> object1.getNome().compareTo(object2.getNome()))
				.map(DadosListadosDeMedico::new).collect(Collectors.toList());
	}
	
	public List<DadosParaConsulta> converterParaConsulta(List<Medico> lista){
		if(lista.isEmpty()) return null;
		return lista.stream()
				.filter(c -> c.isApagado()==false)
				.sorted((object1, object2) -> object1.getNome().compareTo(object2.getNome()))
				.map(DadosParaConsulta::new).collect(Collectors.toList());
	}
	
	
	
	public List<DadosMedicos> buscarTodos(){
		return  this.converterDadosMedicos(this.medicoRepository.findAll());
	}
	
	public List<DadosListadosDeMedico> buscarOrdenado(){
		return  this.converterOrdenado(this.medicoRepository.findAll());
	}
	
	public List<DadosParaConsulta> buscarTodosParaConsulta(){
		return  this.converterParaConsulta(this.medicoRepository.findAll());
	}
	

	@SuppressWarnings("deprecation")
	public DadosMedicos getPelaId(Long id){
		
		java.util.Optional<Medico> op=medicoRepository.findById(id);
		if(op.isPresent() && op.get().isApagado()==false) {
			return new DadosMedicos(this.medicoRepository.getById(id));
		}
		else return null;
	}
	
	public Medico cadastrar(FormMedico dados) {
		Medico medico= new Medico(dados);
		Endereco endereco=new Endereco(dados.endereco());
		endereco.setComplemento(dados.endereco().complemento());
		endereco.setNumero(dados.endereco().numero());
		medico.setEndereco(endereco);
		enderecoRepository.save(endereco);
		medicoRepository.save(medico);
		return medico; 
	}
	
	public Medico atualizar(FormMedicoUpdate dados,Long id) throws MedicoNaoEstaNoSistemaException {
		@SuppressWarnings("deprecation")
		Medico medico=medicoRepository.getById(id);
		Optional<Medico> op=medicoRepository.findById(id);
		if(op.isPresent()) {
			Endereco endereco=new Endereco(dados.endereco());
			medico.setNome(dados.nome());
			medico.setTelefone(dados.telefone());
			endereco.setComplemento(dados.endereco().complemento());
			endereco.setNumero(dados.endereco().numero());
			medico.setEndereco(endereco);
			enderecoRepository.save(endereco);
			medicoRepository.save(medico);
			return medico;
		} 
		else throw new MedicoNaoEstaNoSistemaException("Medico Nao está no sistema");
	}
	
	
	public void deletar(Long id) throws MedicoNaoEstaNoSistemaException {
		@SuppressWarnings("deprecation")
		Medico medico=medicoRepository.getById(id);
		Optional<Medico> op=medicoRepository.findById(id);
		if(op.isPresent()) {
			@SuppressWarnings("deprecation")
			Endereco endereco=enderecoRepository.getById(id);
			medico.setApagado(true);
			endereco.setApagado(true);
			enderecoRepository.save(endereco); 
			medicoRepository.save(medico);
		} 
		else throw new MedicoNaoEstaNoSistemaException("Medico Nao está no sistema");
	}


}
