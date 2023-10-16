package com.example.hospital.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospital.models.Medico;

public interface MedicoRepository extends JpaRepository<Medico,Long> {
	
	
}
