package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.dto.ConsultaDto;
import com.example.demo.dto.FormConsulta;
import com.example.demo.dto.MedicoDto;
import com.example.demo.dto.PacienteDto;
import com.example.demo.exception.CancelarCom1DiaDeAntecedenciaException;
import com.example.demo.exception.ConsultaNaoEncontradaException;
import com.example.demo.exception.MarcouConsultaNoPassadoException;
import com.example.demo.exception.MedicoEstaEmConsultaException;
import com.example.demo.exception.MedicoNaoEstaNoSistemaException;
import com.example.demo.exception.MenosDe30MinutosException;
import com.example.demo.exception.PacienteJaMarcouNoDiaException;
import com.example.demo.exception.PacienteNaoEstaNoSistemaException;
import com.example.demo.model.Consulta;
import com.example.demo.model.DataConsulta;
import com.example.demo.model.MotivoCancelamento;
import com.example.demo.repositories.ConsultaRepository;
import com.example.demo.repositories.DataConsultaRepository;



//import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ConsultaService<R> {
	
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
	
	
	public List<MedicoDto> fetchAllMedico() {
		List<MedicoDto> medicos=medicoFeign.getAllMedicos();
		return medicos;
	}
	
	public PacienteDto fetchPaciente(Long id) {
		PacienteDto paciente=pacienteFeign.getPaciente(id);
		//System.out.println(paciente+"\n\n *********************************************");
		//System.out.println("\n\n ************************************************************");
		return paciente;
	}
	
	public ConsultaDto pegarConsultaPelaId(Long id) {
		@SuppressWarnings("deprecation")
		Consulta consulta=consultaRepository.getById(id);
		ConsultaDto consultaDados=new ConsultaDto(consulta,this.fetchMedico(consulta.getMedico()),this.fetchPaciente(consulta.getPaciente() ) );
		return consultaDados;
	}

	public Consulta cadastrar(FormConsulta dados) throws MedicoNaoEstaNoSistemaException, PacienteNaoEstaNoSistemaException, MenosDe30MinutosException, PacienteJaMarcouNoDiaException, MedicoEstaEmConsultaException, MarcouConsultaNoPassadoException {
		Consulta consulta= new Consulta(dados);
		DataConsulta data=new DataConsulta(dados.dataConsulta());
		consulta.setData(data);
		MedicoDto medico=null;
		if(dados.medico()==null)medico=this.pegarMedicoAleatorio(this.fetchAllMedico());
		else medico=this.fetchMedico(consulta.getMedico());
		PacienteDto paciente=this.fetchPaciente(consulta.getPaciente());
		if(medico==null) throw new MedicoNaoEstaNoSistemaException("Medico nao esta cadastrado no sistema");
		if(paciente==null) throw new PacienteNaoEstaNoSistemaException("Paciente nao esta cadastrado no sistema");
		ChecarAntecendenciaDe30Minutos(data);
		ChecarPacienteJaMarcouNoDia(paciente,data);
		ChecarSeMedicoEstaDisponivel(medico,data);
		ChecarSeConsultaNaoFoiFeitaNoPassado(data);
		consulta.setMedico(medico.id());
		dataRepository.save(data);
		consultaRepository.save(consulta);
		return consulta; 
	}
	
	
	public void ChecarPacienteJaMarcouNoDia(PacienteDto paciente,DataConsulta data) throws PacienteJaMarcouNoDiaException  {
		List<ConsultaDto> consultas=this.buscarTodos();
		List<ConsultaDto> consultaFiltradas=consultas.stream().filter( c->c.paciente().id()==paciente.id()&& 
				data.getAno()==c.data().getAno()&& data.getMes()==c.data().getMes()&& c.data().getDia()==data.getDia() ).collect(Collectors.toList());
		if(consultaFiltradas.size()>0)  throw new PacienteJaMarcouNoDiaException("Paciente já tem uma consulta marcada para esse dia");
		
	}
	
	public void ChecarSeMedicoEstaDisponivel(MedicoDto medico,DataConsulta data) throws MedicoEstaEmConsultaException {
		List<ConsultaDto> consultas=this.buscarTodos();
		List<ConsultaDto> consultaFiltradas=consultas.stream().filter( c->c.medico().id()==medico.id()&& 
				data.getAno()==c.data().getAno()&& data.getMes()==c.data().getMes()&& c.data().getDia()==data.getDia() &&
						data.getHora()*60-data.getMinuto()-c.data().getHora()*60+c.data().getMinuto()<31 ).collect(Collectors.toList());
		if(consultaFiltradas.size()>0)  throw new MedicoEstaEmConsultaException("Medico está com consulta marcada nesse horário");

	}
	
	public void ChecarSeConsultaNaoFoiFeitaNoPassado(DataConsulta data) throws MarcouConsultaNoPassadoException {
		LocalDateTime now = LocalDateTime.now(); 
		if(data.getAno()<now.getYear() || data.getMes()<now.getMonthValue()|| data.getDia()<now.getDayOfMonth()||
		(	now.getYear()==data.getAno()&& now.getMonthValue()==data.getMes()&& now.getDayOfMonth()==data.getDia() && data.getHora()<now.getHour()		 ) ||
		( now.getYear()==data.getAno()&& now.getMonthValue()==data.getMes()&& now.getDayOfMonth()==data.getDia() && data.getHora()==now.getHour() && data.getHora()<now.getMinute() 	   )
				) {
					throw new MarcouConsultaNoPassadoException("Marcou consulta no passado");
		}
	}
	
	public void ChecarAntecendenciaDe30Minutos(DataConsulta data) throws MenosDe30MinutosException, MarcouConsultaNoPassadoException {
		ChecarSeConsultaNaoFoiFeitaNoPassado(data);
		LocalDateTime now = LocalDateTime.now(); 
		if(now.getYear()==data.getAno()&& now.getMonthValue()==data.getMes()&& now.getDayOfMonth()==data.getDia()) {
			if(data.getHora()*60+data.getMinuto()-now.getHour()*60-now.getMinute()<31) {
				throw new MenosDe30MinutosException("As consulta tem que ser marcadas com 30 minuto de antecendencia");
			} 
		}
	}  
	
	
	public MedicoDto pegarMedicoAleatorio(List<MedicoDto> medicos) {
		if(medicos.size()==0) return null;
		 Random rndm = new Random(); 
	     // creating object
	     MedicoDto rndmElem = medicos.get(rndm.nextInt(medicos.size()));
		return rndmElem;
	}
	
	public Consulta desmarcar(Long id,MotivoCancelamento motivo) throws ConsultaNaoEncontradaException, CancelarCom1DiaDeAntecedenciaException {
		@SuppressWarnings("deprecation")
		Consulta consulta=consultaRepository.getById(id);
		Optional<Consulta> op=consultaRepository.findById(id);
		if(op.isPresent()) {
			checarSeCancelamentoTemMaisDe24Horas(consulta);
			consulta.setCancelado();
			consulta.setMotivo(motivo);
			consultaRepository.save(consulta);
			return consulta; 
		} 
		else throw new ConsultaNaoEncontradaException("Consulta Nao encontrada");
	}
	
	public void checarSeCancelamentoTemMaisDe24Horas(Consulta consulta) throws CancelarCom1DiaDeAntecedenciaException {
		LocalDateTime now = LocalDateTime.now(); 
		DataConsulta data=consulta.getData();
		if(now.getYear()==data.getAno()&& now.getMonthValue()==data.getMes()&& now.getDayOfMonth()==data.getDia()) {
			throw new CancelarCom1DiaDeAntecedenciaException("Voce so pode cancelar uma consulta com um dia de antecedencia");
		}
	}
	

	/*
	 */

}
