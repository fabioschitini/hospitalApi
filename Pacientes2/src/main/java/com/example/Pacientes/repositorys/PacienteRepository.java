package com.example.Pacientes.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Pacientes.models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
	
	
}
