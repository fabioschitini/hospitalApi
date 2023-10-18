package com.ifba.consultas.repositorys;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ifba.consultas.model.DataConsulta;



public interface DataConsultaRepository extends JpaRepository<DataConsulta,Long>  {

}
