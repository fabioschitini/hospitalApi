package com.example.hospital.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospital.dto.DadosMedicos;
import com.example.hospital.dto.DadosParaConsulta;
import com.example.hospital.dto.DadosListadosDeMedico;

import com.example.hospital.dto.FormMedico;
import com.example.hospital.dto.FormMedicoUpdate;
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
		return lista.stream().sorted((object1, object2) -> object1.getNome().compareTo(object2.getNome()))
				.map(DadosListadosDeMedico::new).collect(Collectors.toList());
	}
	
	public List<DadosMedicos> buscarTodos(){
		return  this.converterDadosMedicos(this.medicoRepository.findAll());
	}
	
	public List<DadosListadosDeMedico> buscarOrdenado(){
		return  this.converterOrdenado(this.medicoRepository.findAll());
	}
	

	public DadosParaConsulta getPelaId(Long id){
		return  new DadosParaConsulta(this.medicoRepository.getById(id));
	}
	
	public Medico cadastrar(FormMedico dados) {
		Medico medico= new Medico(dados);
		Endereco endereco=new Endereco(dados.endereco());
		endereco.setComplemento(dados.endereco().complemento());
		endereco.setNumero(dados.endereco().numero());
		medico.setEndereco(endereco);
		enderecoRepository.save(endereco);
		medicoRepository.save(medico);

		//Optional<Endereco> op=enderecoRepository.findById(dados.endereco());
		//if(op.isPresent()) {
			//medico.setEndereco(op.get());
			//medicoRepository.save(medico);
			//return ; 
		//}
		return medico; 
	}
	
	public Medico atualizar(FormMedicoUpdate dados,Long id) {
		@SuppressWarnings("deprecation")
		Medico medico=medicoRepository.getById(id);
		@SuppressWarnings("deprecation")
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
	
	
	public Medico deletar(Long id) {
		@SuppressWarnings("deprecation")
		Medico medico=medicoRepository.getById(id);
		@SuppressWarnings("deprecation")
		Endereco endereco=enderecoRepository.getById(id);
		medico.setApagado(true);
		endereco.setApagado(true);
		enderecoRepository.save(endereco);
		medicoRepository.save(medico);
		return medico;
	}


}
