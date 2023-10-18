package com.ifba.consultas.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifba.consultas.model.Consulta;


public interface ConsultaRepository extends JpaRepository<Consulta,Long> {

}
